package thecrafterl.mods.heroes.antman.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import thecrafterl.mods.heroes.antman.AntMan;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class AntManUtils {

	public static AntManEnergyHandler packetHandler;
	private static ArrayList<Item> wrenchItems;

	public static void init() {
		wrenchItems = new ArrayList<Item>();

		addWrench("ThermalExpansion", "wrench");
		addWrench("EnderIO", "itemYetaWrench");
		addWrench("BuildCraft|Core", "wrenchItem");
		addWrench("funkylocomotion", "wrench");
	}

	public static void addWrench(String mod, String id) {
		Item wrench = GameRegistry.findItem(mod, id);
		if (wrench != null) {
			wrenchItems.add(wrench);
		}
	}

	public static void setTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}

	public static void drawTexturedModalRectWithIcon(IIcon icon, int x, int y) {
		drawTexturedModalRectWithIcon(icon, x, y, icon.getIconWidth(), icon.getIconHeight(), false);
	}

	public static void drawTexturedModalRectWithIcon(IIcon icon, int x, int y, int width, int height, boolean crop) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float minU = icon.getMinU();
		float minV = icon.getMinV();
		float maxU = icon.getMaxU();
		float maxV = icon.getMaxV();

		if (crop) {
			maxU = minU + (maxU - minU) / (float) icon.getIconWidth() * (float) width;
			maxV = minV + (maxV - minV) / (float) icon.getIconHeight() * (float) height;
		}

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, minU, maxV);
		tessellator.addVertexWithUV(x + width, y + height, 0, maxU, maxV);
		tessellator.addVertexWithUV(x + width, y, 0, maxU, minV);
		tessellator.addVertexWithUV(x, y, 0, minU, minV);
		tessellator.draw();
	}

	public static void drawTexturedModalRect(int x, int y, float u, float v, int width, int height) {
		drawTexturedModalRect(x, y, u, v, width, height, 256, 256);
	}

	public static void drawTexturedModalRect(int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 1F / (float) textureWidth;
		float f1 = 1F / (float) textureHeight;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, u * f, (v + height) * f1);
		tessellator.addVertexWithUV(x + width, y + height, 0, (u + width) * f, (v + height) * f1);
		tessellator.addVertexWithUV(x + width, y, 0, (u + width) * f, v * f1);
		tessellator.addVertexWithUV(x, y, 0, u * f, v * f1);
		tessellator.draw();
	}

	public static void syncEnergy(World world, int x, int y, int z, int energy, int maxEnergy) {
		if (world.getTotalWorldTime() % 10 == 0 || energy == 0 || energy == maxEnergy) {
			packetHandler.sendPacketToDimension(world.provider.dimensionId, new PacketEnergy(x, y, z, energy));
		}
	}

	public static boolean areOresIdentical(ItemStack stack1, ItemStack stack2) {
		if (areStacksEqual(stack1, stack2)) {
			return true;
		}

		int[] ores1 = OreDictionary.getOreIDs(stack1);
		int[] ores2 = OreDictionary.getOreIDs(stack2);

		if (ores1.length == 0 || ores2.length == 0) {
			return false;
		}

		if (ores1.length != ores2.length) {
			return false;
		}

		for (int i = 0; i < ores1.length; i++) {
			if (ores1[i] != ores2[i]) {
				return false;
			}
		}

		return true;
	}

	public static boolean areOresIdentical(ItemStack stack1, String ore) {
		int[] ores1 = OreDictionary.getOreIDs(stack1);
		int ore2 = OreDictionary.getOreID(ore);

		if (ores1.length == 0) {
			return false;
		}

		for (int i = 0; i < ores1.length; i++) {
			if (ores1[i] == ore2) {
				return true;
			}
		}

		return false;
	}

	public static ItemStack getPrefferedOre(String name) {

		List<ItemStack> oreDictEntries = OreDictionary.getOres(name);

		ItemStack ret = null;

		if (oreDictEntries.size() > 0) {

			ret = oreDictEntries.get(0).copy();

			for (int i = 1; i < oreDictEntries.size(); i++) {
				String modid = GameRegistry.findUniqueIdentifierFor(oreDictEntries.get(i).getItem()).modId;
				if (modid.equals("Minecraft") || modid.equals("ThermalFoundation")) {
					ret = oreDictEntries.get(i).copy();
					break;
				}
			}

		}

		return ret;
	}

	public static ItemStack getStack(Object output) {

		if (output instanceof Item) {
			output = new ItemStack((Item) output);
		} else if (output instanceof Block) {
			output = new ItemStack((Block) output);
		}

		if (output instanceof ItemStack) {
			return ((ItemStack) output).copy();
		} else if (output instanceof String) {
			String[] splits = ((String) output).split(",");

			ItemStack stack = getPrefferedOre(splits[0]);

			if (stack != null && splits.length > 1) {
				stack.stackSize = Integer.parseInt(splits[1]);
				return stack;
			}

			return stack;
		}

		return null;
	}

	public static ItemStack[] readStacksFromNBT(int length, NBTTagCompound compound, String tag) {
		ItemStack[] stacks = new ItemStack[length];

		NBTTagList slotsList = compound.getTagList(tag, Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < slotsList.tagCount(); i++) {
			NBTTagCompound slotTagCompound = slotsList.getCompoundTagAt(i);

			int slot = (int) slotTagCompound.getByte("slot");
			stacks[slot] = ItemStack.loadItemStackFromNBT(slotTagCompound);
		}

		return stacks;
	}

	public static void writeStacksToNBT(ItemStack[] stacks, NBTTagCompound compound, String tag) {
		NBTTagList stacksList = new NBTTagList();

		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i] != null) {
				NBTTagCompound slotTagCompound = new NBTTagCompound();
				slotTagCompound.setByte("slot", (byte) i);
				stacks[i].writeToNBT(slotTagCompound);
				stacksList.appendTag(slotTagCompound);
			}
		}

		compound.setTag("slots", stacksList);
	}

	public static void dropBlockContents(World world, int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IContentDropper)) {
			return;
		}

		ArrayList<ItemStack> drops = ((IContentDropper) tileEntity).getDrops();

		for (int i = 0; i < drops.size(); i++) {
			ItemStack itemStack = drops.get(i);

			if (itemStack != null) {
				float f = world.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

				while (itemStack.stackSize > 0) {
					int j1 = world.rand.nextInt(21) + 10;

					if (j1 > itemStack.stackSize) {
						j1 = itemStack.stackSize;
					}

					itemStack.stackSize -= j1;
					EntityItem entityItem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemStack.getItem(), j1, itemStack.getItemDamage()));

					if (itemStack.hasTagCompound()) {
						entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
					}

					float f3 = 0.05F;
					entityItem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
					entityItem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
					entityItem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
					world.spawnEntityInWorld(entityItem);
				}
			}
		}
	}

	public static int getIndexOfItem(Object item, ItemStack[] stacks) {
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i] == null) {
				continue;
			}

			if (item instanceof Item) {
				item = new ItemStack((Item) item);
			} else if (item instanceof Block) {
				item = new ItemStack((Block) item);
			}

			if (item instanceof ItemStack) {
				if (areStacksEqual(stacks[i], (ItemStack) item)) {
					return i;
				}
			} else if (item instanceof String) {
				List<ItemStack> oreDictEntries = OreDictionary.getOres(((String) item).split(",")[0]);

				for (int j = 0; j < oreDictEntries.size(); j++) {
					if (areStacksEqual(stacks[i], oreDictEntries.get(j))) {
						return i;
					}
				}
			}
		}

		return -1;
	}

	public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
		return areStacksEqual(stack1, stack2, false);
	}

	public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2, boolean ignoreNBT) {
		return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage() && (ignoreNBT || (!stack1.hasTagCompound() && !stack2.hasTagCompound()));
	}

	public static boolean isItemWrench(Item item) {
		for (int i = 0; i < wrenchItems.size(); i++) {
			if (wrenchItems.get(i) == item) {
				return true;
			}
		}
		return false;
	}

	public static void wrenchBlock(World world, EntityPlayer player, int x, int y, int z) {
		wrenchBlock(world, player, x, y, z, null);
	}

	public static void wrenchBlock(World world, EntityPlayer player, int x, int y, int z, IWrenchable wrenchable) {
		Block block = world.getBlock(x, y, z);

		if (world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) {
			float f = 0.7F;
			double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;

			ItemStack stack = new ItemStack(block, 1, block.damageDropped(world.getBlockMetadata(x, y, z)));
			if (wrenchable != null) {
				stack = wrenchable.onWrenched(stack, world.getTileEntity(x, y, z));
			}

			if (player.inventory.addItemStackToInventory(stack)) {
				player.inventoryContainer.detectAndSendChanges();
			} else {
				EntityItem itemEntity = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, stack);
				itemEntity.delayBeforeCanPickup = 0;
				world.spawnEntityInWorld(itemEntity);
			}

			block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
		}

		world.setBlockToAir(x, y, z);
	}

	@ChannelHandler.Sharable
	public class AntManEnergyHandler extends MessageToMessageCodec<FMLProxyPacket, IAntManPacket> {

		private EnumMap<Side, FMLEmbeddedChannel> channels;
		private LinkedList<Class<? extends IAntManPacket>> packets = new LinkedList<Class<? extends IAntManPacket>>();

		public AntManEnergyHandler() {
			channels = NetworkRegistry.INSTANCE.newChannel(AntMan.MODID + "_Energy", this);

			packets.add(PacketEnergy.class);

			Collections.sort(packets, new Comparator<Class<? extends IAntManPacket>>() {
				@Override
				public int compare(Class<? extends IAntManPacket> o1, Class<? extends IAntManPacket> o2) {
					int comp = String.CASE_INSENSITIVE_ORDER.compare(o1.getCanonicalName(), o2.getCanonicalName());

					if (comp == 0) {
						comp = o1.getCanonicalName().compareTo(o2.getCanonicalName());
					}

					return comp;
				}
			});
		}

		@Override
		protected void encode(ChannelHandlerContext ctx, IAntManPacket msg, List<Object> out) throws Exception {
			ByteBuf buffer = Unpooled.buffer();

			Class<? extends IAntManPacket> packetClass = msg.getClass();

			byte packetID = (byte) packets.indexOf(packetClass);
			buffer.writeByte(packetID);

			msg.write(ctx, buffer);

			out.add(new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get()));
		}

		@Override
		protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
			ByteBuf buffer = msg.payload();

			byte packetID = buffer.readByte();

			IAntManPacket packet = packets.get(packetID).newInstance();
			packet.read(ctx, buffer.slice());

			EntityPlayer player = AntMan.proxy.getPlayerFromPacket(ctx);

			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				packet.executeClientSide(player);
			} else {
				packet.executeServerSide(player);
			}
		}

		public void sendPacketToServer(IAntManPacket packet) {
			channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
			channels.get(Side.CLIENT).writeAndFlush(packet);
		}

		public void sendPacketToAll(IAntManPacket packet) {
			channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
			channels.get(Side.SERVER).writeAndFlush(packet);
		}

		public void sendPacketToDimension(int dimension, IAntManPacket packet) {
			channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
			channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimension);
			channels.get(Side.SERVER).writeAndFlush(packet);
		}

		public void sendPacketAroundPoint(NetworkRegistry.TargetPoint point, IAntManPacket packet) {
			channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
			channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
			channels.get(Side.SERVER).writeAndFlush(packet);
		}
	}

	public interface IAntManPacket {
		public void write(ChannelHandlerContext ctx, ByteBuf buffer);

		public void read(ChannelHandlerContext ctx, ByteBuf buffer);

		public void executeClientSide(EntityPlayer player);

		public void executeServerSide(EntityPlayer player);
	}
}
