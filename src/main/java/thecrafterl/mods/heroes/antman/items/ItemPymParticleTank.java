package thecrafterl.mods.heroes.antman.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemPymParticleTank extends Item implements IPymParticleContainer {

	public int tier;
	IIcon emptyIcon;
	IIcon fullIcon;
	
	public ItemPymParticleTank(int tier) {
		
		this.tier = tier;
		this.setUnlocalizedName(AntMan.MODID.toLowerCase() + ".tank");
		GameRegistry.registerItem(this, AntMan.MODID.toLowerCase() + ".tank" + tier);
		this.setMaxStackSize(1);
		
		this.setCreativeTab(AntMan.tabAntMan);
		AMItems.items.add(this);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		setDefaultTags(stack);
	}
	
	public void setDefaultTags(ItemStack stack) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger(pymParticlesTAG, 0);
			stack.stackTagCompound.setInteger(maxPymParticlesTAG, tier);
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		list.add("Tier " + tier);
		list.add("");
		list.add(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.pymparticles") + ChatFormatting.DARK_GRAY + ":");
		list.add("   " + ChatFormatting.RED + PymParticleHandler.getPymParticles(stack) + ChatFormatting.DARK_GRAY + "/" + ChatFormatting.RED + PymParticleHandler.getMaxPymParticles(stack));
	}
	
	public ItemStack setEnergyDefaultTags(ItemStack stack, int pymParticles) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger(maxPymParticlesTAG, 1);
			stack.stackTagCompound.setInteger(pymParticlesTAG, pymParticles);	
		}
		return stack;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(this.setEnergyDefaultTags(new ItemStack(item), 0));
		list.add(this.setEnergyDefaultTags(new ItemStack(item), this.getMaxPymParticles(tier)));
	}
	
	@Override
	public void registerIcons(IIconRegister ir) {
		this.itemIcon = ir.registerIcon(AntMan.ASSETDIR + "tank" + tier);
		this.emptyIcon = ir.registerIcon(AntMan.ASSETDIR + "tank" + tier);
		this.fullIcon = ir.registerIcon(AntMan.ASSETDIR + "tank" + tier + "_full");
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return this.isEmpty(stack) ? emptyIcon : fullIcon;
	}
	
	@Override
	public IIcon getIconIndex(ItemStack stack) {
		return getIcon(stack, 0);
	}
	
	@Override
	public int getMaxPymParticles(ItemStack stack) {
		setDefaultTags(stack);
		return getMaxPymParticles(getTankTier(stack));
	}
	
	public int getMaxPymParticles(int i) {		
		if(i == 1)
			return amountTier1;
		else if(i == 2)
			return amountTier2;
		else
			return amountTier3;
	}

	@Override
	public int getTankTier(ItemStack stack) {
		return tier;
	}

	@Override
	public boolean isEmpty(ItemStack stack) {
		return PymParticleHandler.getPymParticles(stack) == 0;
	}

	@Override
	public void addPymParticles(ItemStack stack, int i) {
		if(getPymParticles(stack) < getMaxPymParticles(stack)) {
			stack.stackTagCompound.setInteger(pymParticlesTAG, stack.stackTagCompound.getInteger(pymParticlesTAG) + i);
			
			if(stack.stackTagCompound.getInteger(pymParticlesTAG) > getMaxPymParticles(stack))
				stack.stackTagCompound.setInteger(pymParticlesTAG, getMaxPymParticles(stack));
		}
		
	}

	@Override
	public void removePymParticles(ItemStack stack, int i) {
		if(getPymParticles(stack) >= i) {
			stack.stackTagCompound.setInteger(pymParticlesTAG, stack.stackTagCompound.getInteger(pymParticlesTAG) - i);
			
			if(stack.stackTagCompound.getInteger(pymParticlesTAG) < 0)
				stack.stackTagCompound.setInteger(pymParticlesTAG, 0);
		}
	}

	@Override
	public int getPymParticles(ItemStack stack) {
		if(stack.getItem() instanceof IPymParticleContainer && stack.stackTagCompound != null && stack.stackTagCompound.hasKey(pymParticlesTAG))
			return stack.stackTagCompound.getInteger(pymParticlesTAG);
		return 0;
	}

	@Override
	public boolean hasEnoughPymParticles(ItemStack stack, int i) {
		return stack.stackTagCompound.getInteger(pymParticlesTAG) >= i;
	}

	@Override
	public boolean canAddPymParticles(ItemStack stack, int i) {
		IPymParticleContainer container = (IPymParticleContainer) stack.getItem();
		return stack.stackTagCompound.getInteger(pymParticlesTAG) + i <= getMaxPymParticles(stack);
	}

}
