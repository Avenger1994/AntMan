package thecrafterl.mods.heroes.antman.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.ShrinkerTypesHandlerClient;
import thecrafterl.mods.heroes.antman.client.models.ModelAdvancedArmor;
import thecrafterl.mods.heroes.antman.client.models.ModelYellowjacketChestplate;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemAntManArmorChestplate extends ItemAntManArmor implements IPymParticleContainer {
	
	public static int maxEnergy = 16000;
	public int maxTransfer = 100;
	
	public ItemAntManArmorChestplate(String name, int i, ShrinkerTypes type) {
		super(name, i, type);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		setDefaultTags(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		setDefaultTags(stack);
		list.add(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.pymparticles") + ChatFormatting.DARK_GRAY + ":");
		list.add("   " + ShrinkerTypesHandlerClient.getChatColor(getShrinkerType()) + PymParticleHandler.getPymParticles(stack) + ChatFormatting.DARK_GRAY + "/" + ShrinkerTypesHandlerClient.getChatColor(getShrinkerType()) + PymParticleHandler.getMaxPymParticles(stack));
	}
	
	public static void setDefaultTags(ItemStack stack) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger(pymParticlesTAG, 0);
			stack.stackTagCompound.setInteger(maxPymParticlesTAG, 1);
			stack.stackTagCompound.setInteger("Energy", 0);
		}
	}
	
	public static ItemStack setEnergyDefaultTags(ItemStack stack, int energy, int pymParticles) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger(pymParticlesTAG, pymParticles);
			stack.stackTagCompound.setInteger(maxPymParticlesTAG, 1);
			stack.stackTagCompound.setInteger("Energy", energy);
		}
		return stack;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(this.setEnergyDefaultTags(new ItemStack(item), 0, 0));
		list.add(this.setEnergyDefaultTags(new ItemStack(item), maxEnergy, amountTier1));
	}
	
	public int getEnergy(ItemStack stack) {
		return maxEnergy;
	}
	
	public void removeEnergy(ItemStack stack) {
		// Nothing happens
	}
	
	public void removeEnergy(ItemStack stack, int i) {
		// Nothing happens
	}
	
	public boolean hasEnoughEnergy(ItemStack stack) {
		return true;
	}
	
	public boolean hasEnoughEnergy(ItemStack stack, int i) {
		return true;
	}
	
	@Override
	public int getTankTier(ItemStack stack) {
		setDefaultTags(stack);
		return stack.stackTagCompound.getInteger(maxPymParticlesTAG);
	}

	@Override
	public int getMaxPymParticles(ItemStack stack) {
		setDefaultTags(stack);
		
		if(getTankTier(stack) == 1)
			return amountTier1;
		else if(getTankTier(stack) == 2)
			return amountTier2;
		else
			return amountTier3;
	}

	@Override
	public boolean isEmpty(ItemStack stack) {
		return PymParticleHandler.getPymParticles(stack) == 0;
	}

	@Override
	public void addPymParticles(ItemStack stack, int i) {
		if(stack.stackTagCompound != null && stack.stackTagCompound.hasKey(pymParticlesTAG) && getPymParticles(stack) < getMaxPymParticles(stack)) {
			stack.stackTagCompound.setInteger(pymParticlesTAG, stack.stackTagCompound.getInteger(pymParticlesTAG) + i);
			
			if(stack.stackTagCompound.getInteger(pymParticlesTAG) > getMaxPymParticles(stack))
				stack.stackTagCompound.setInteger(pymParticlesTAG, getMaxPymParticles(stack));
		}
		
	}

	@Override
	public void removePymParticles(ItemStack stack, int i) {
		if(stack.stackTagCompound != null && stack.stackTagCompound.hasKey(pymParticlesTAG) && getPymParticles(stack) >= i) {
			stack.stackTagCompound.setInteger(pymParticlesTAG, stack.stackTagCompound.getInteger(pymParticlesTAG) - i);
			
			if(stack.stackTagCompound.getInteger(pymParticlesTAG) < 0)
				stack.stackTagCompound.setInteger(pymParticlesTAG, 0);
		}
	}

	@Override
	public int getPymParticles(ItemStack stack) {
		if(stack.stackTagCompound != null && stack.stackTagCompound.hasKey(pymParticlesTAG))
			return stack.stackTagCompound.getInteger(pymParticlesTAG);
		return 0;
	}

	@Override
	public boolean hasEnoughPymParticles(ItemStack stack, int i) {
		if(stack.stackTagCompound != null && stack.stackTagCompound.hasKey(pymParticlesTAG))
			return stack.stackTagCompound.getInteger(pymParticlesTAG) >= i;
		return false;
	}

	@Override
	public boolean canAddPymParticles(ItemStack stack, int i) {
		IPymParticleContainer container = (IPymParticleContainer) stack.getItem();
		return stack.stackTagCompound.getInteger(pymParticlesTAG) + i <= getMaxPymParticles(stack);
	}

}
