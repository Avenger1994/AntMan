package thecrafterl.mods.heroes.antman;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.entity.AMEntities;
import thecrafterl.mods.heroes.antman.entity.AMEntityEventHandler;
import thecrafterl.mods.heroes.antman.entity.AntManPlayerData;
import thecrafterl.mods.heroes.antman.entity.AntManSizes;
import thecrafterl.mods.heroes.antman.entity.EntityRepulsor;
import thecrafterl.mods.heroes.antman.gui.AMGuiHandler;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.items.IPymParticleContainer;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmor;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorChestplate;
import thecrafterl.mods.heroes.antman.network.MessageFlyKeys;
import thecrafterl.mods.heroes.antman.network.MessageToggleHelmet;
import thecrafterl.mods.heroes.antman.network.MessageToggleSize;
import thecrafterl.mods.heroes.antman.network.MessageToggleSpeed;
import thecrafterl.mods.heroes.antman.network.MessageYJShoot;
import thecrafterl.mods.heroes.antman.proxies.AntManProxy;
import thecrafterl.mods.heroes.antman.recipe.AMRecipes;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid = AntMan.MODID, version = AntMan.VERSION, name = AntMan.NAME)
public class AntMan {
	
	public static final String MODID = "AntMan";
	public static final String NAME = "AntMan";
	public static final String VERSION = "Beta-1.7.10-1.4.0";
	public static final String ASSETDIR = MODID + ":";

	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	protected static Random rand;
	
	@Instance(value = AntMan.MODID)
	public static AntMan instance;
	
	@SidedProxy(clientSide = "thecrafterl.mods.heroes.antman.proxies.AntManClientProxy", serverSide = "thecrafterl.mods.heroes.antman.proxies.AntManProxy")
	public static AntManProxy proxy;
	
	// ----------------------------------------------------------------------------------------
	
	public static CreativeTabs tabAntMan = new CreativeTabs("tabAntMan") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return AMItems.helmet;
		}
	};
	
	// ----------------------------------------------------------------------------------------
	
	public static DamageSource dmgSuffocate = new DamageSourcesSuffocate("suffocate");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		rand = new Random();
		AMConfig.init(event);
		AMItems.init();
		AMBlocks.init();
		AMEntities.init();
	}
	
	// ----------------------------------------------------------------------------------------
	
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("AntMan");
	public AMGuiHandler guiHandler = new AMGuiHandler();
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		network.registerMessage(MessageToggleSize.class, MessageToggleSize.class, 0, Side.SERVER);
		network.registerMessage(MessageToggleHelmet.class, MessageToggleHelmet.class, 1, Side.SERVER);
		network.registerMessage(MessageFlyKeys.class, MessageFlyKeys.class, 2, Side.SERVER);
		network.registerMessage(MessageToggleSpeed.class, MessageToggleSpeed.class, 3, Side.SERVER);
		network.registerMessage(MessageYJShoot.class, MessageYJShoot.class, 4, Side.SERVER);
		
		proxy.registerHandlers();
		proxy.registerRenderer();
		
		FMLCommonHandler.instance().bus().register(new AMEntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new AMEntityEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
	}
	
	// ----------------------------------------------------------------------------------------
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		AMRecipes.init();
	}
	
	// ----------------------------------------------------------------------------------------
	
	public static boolean isRFModActive() {
		return (Loader.isModLoaded("ThermalExpansion") && AMConfig.modSupportThermalExpansion) || (Loader.isModLoaded("EnderIO") && AMConfig.modSupportEnderIO);
	}
	
	public static void setDefaultHelmetTags(ItemStack stack) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setBoolean("helmetOpen", false);
		}
	}
	
	public static boolean isSmall(EntityPlayer player) {
		return AntManPlayerData.get(player).isSmall();
	}
	
	public static AntManSizes getSize(EntityPlayer player) {
		return AntManPlayerData.get(player).getSize();
	}
	
	public static void switchSize(EntityPlayer player) {
		
		ItemStack chestplate = player.getCurrentArmor(2);
		
		if (((ItemAntManArmorChestplate) chestplate.getItem()).hasEnoughEnergy(chestplate, AMConfig.energyPerChange) && PymParticleHandler.hasEnoughPymParticles(chestplate, AMConfig.particlesPerChange)) {
			((ItemAntManArmorChestplate) chestplate.getItem()).removeEnergy(chestplate, AMConfig.energyPerChange);
			PymParticleHandler.removePymParticles(chestplate, AMConfig.particlesPerChange);

			for (int i = 0; i < 20; ++i) {
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				double d3 = 10.0D;
				player.worldObj.spawnParticle("explode", player.posX + (double) (rand.nextFloat() * player.width * 2.0F) - (double) player.width - d0 * d3, player.posY + (double) (rand.nextFloat() * player.height) - d1 * d3, player.posZ + (double) (rand.nextFloat() * player.width * 2.0F) - (double) player.width - d2 * d3, d0, d1, d2);
			}
			
			switch (AntManPlayerData.get(player).getSize()) {
			case SMALL:
				AntManPlayerData.get(player).setSize(AntManSizes.NORMAL);
				player.worldObj.playSoundAtEntity(player, ASSETDIR + "toggle_normal", 1.0F, 1.0F);
				break;
			case NORMAL:
				AntManPlayerData.get(player).setSize(AntManSizes.SMALL);
				player.worldObj.playSoundAtEntity(player, ASSETDIR + "toggle_small", 1.0F, 1.0F);
				setHelmetOpen(player, false);
//				player.setPositionAndUpdate(player.posX - 0.27D, player.posY, player.posZ - 0.73D);
			default:
				break;
			}
		}
	}
	
	public static void toggleHelmet(EntityPlayer player) {
		setDefaultHelmetTags(player.getCurrentArmor(3));
		setHelmetOpen(player, !player.getCurrentArmor(3).stackTagCompound.getBoolean("helmetOpen"));
	}
	
	public static void toggleSpeed(EntityPlayer player) {
		AntManPlayerData.get(player).setFast(!AntManPlayerData.get(player).isFast());
	}
	
	public static boolean isClosed(ItemStack stack) {
		setDefaultHelmetTags(stack);
		return !stack.stackTagCompound.getBoolean("helmetOpen");
	}
	
	public static ShrinkerTypes getShrinkerTypeFromHelmet(EntityPlayer player) {
		ItemStack helmet = player.getCurrentArmor(3);
		
		if(helmet != null && helmet.getItem() instanceof ItemAntManArmor) {
			return ((ItemAntManArmor)helmet.getItem()).getShrinkerType();
		}
		
		return null;
	}
	
	public static void setHelmetOpen(EntityPlayer player, boolean b) {
		ItemStack stack = player.getCurrentArmor(3);
		setDefaultHelmetTags(stack);
		if(b != stack.stackTagCompound.getBoolean("helmetOpen"))
			player.worldObj.playSoundAtEntity(player, getShrinkerTypeFromHelmet(player).getHelmetSound(), 1.0F, 1.0F);
		stack.stackTagCompound.setBoolean("helmetOpen", b);
	}
	
	public static boolean hasArmorOn(EntityPlayer player) {
		if(player.getCurrentArmor(3) != null 
				&& player.getCurrentArmor(2) != null
				&& player.getCurrentArmor(1) != null
				&& player.getCurrentArmor(0) != null
				&& player.getCurrentArmor(3).getItem() instanceof ItemAntManArmor
				&& player.getCurrentArmor(2).getItem() instanceof ItemAntManArmor
				&& player.getCurrentArmor(1).getItem() instanceof ItemAntManArmor
				&& player.getCurrentArmor(0).getItem() instanceof ItemAntManArmor
				&& ((ItemAntManArmor)player.getCurrentArmor(0).getItem()).getShrinkerType() == ((ItemAntManArmor)player.getCurrentArmor(1).getItem()).getShrinkerType()
				&& ((ItemAntManArmor)player.getCurrentArmor(1).getItem()).getShrinkerType() == ((ItemAntManArmor)player.getCurrentArmor(2).getItem()).getShrinkerType()
				&& ((ItemAntManArmor)player.getCurrentArmor(2).getItem()).getShrinkerType() == ((ItemAntManArmor)player.getCurrentArmor(3).getItem()).getShrinkerType()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean hasArmorOn(EntityPlayer player, ShrinkerTypes type) {
		return hasArmorOn(player) && ((ItemAntManArmor)player.getCurrentArmor(2).getItem()).getShrinkerType() == type;
	}
	
	public static boolean hasHelmetOn(EntityPlayer player) {
		return player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() instanceof ItemAntManArmor;
	}
	
	public static boolean hasChestplateOn(EntityPlayer player) {
		return player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() instanceof ItemAntManArmor;
	}
	
	public static void shootLaser(EntityPlayer player) {
		if(hasArmorOn(player, ShrinkerTypes.MCU_YELLOWJACKET)) {
			ItemAntManArmorChestplate chestplate = (ItemAntManArmorChestplate) player.getCurrentArmor(2).getItem();
			
			if(chestplate.hasEnoughEnergy(player.getCurrentArmor(2), 160)) {
				chestplate.removeEnergy(player.getCurrentArmor(2), 160);
				player.worldObj.spawnEntityInWorld(new EntityRepulsor(player.worldObj, player));
			}
		}
	}
	
}
