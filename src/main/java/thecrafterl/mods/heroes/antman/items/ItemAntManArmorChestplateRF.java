package thecrafterl.mods.heroes.antman.items;

import java.util.List;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;

import com.mojang.realmsclient.gui.ChatFormatting;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemAntManArmorChestplateRF extends ItemAntManArmorChestplate implements IEnergyContainerItem {

	public ItemAntManArmorChestplateRF(String name, int i, ShrinkerTypes type) {
		super(name, i, type);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		super.onCreated(stack, world, player);
	}

	@Override
	public int getEnergy(ItemStack stack) {
		return this.getEnergyStored(stack);
	}

	@Override
	public void removeEnergy(ItemStack stack) {
		this.removeEnergy(stack, AMConfig.usedEnergy);
	}
	
	@Override
	public void removeEnergy(ItemStack stack, int i) {
		this.extractEnergy(stack, i, false);
	}
	
	@Override
	public boolean hasEnoughEnergy(ItemStack stack) {
		return this.hasEnoughEnergy(stack, AMConfig.usedEnergy);
	}
	
	@Override
	public boolean hasEnoughEnergy(ItemStack stack, int i) {
		return this.getEnergy(stack) >= i;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		super.addInformation(stack, player, list, b);
		list.add("");
		list.add(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.energy") + ChatFormatting.DARK_GRAY + ":");
		list.add("   " + ChatFormatting.AQUA + this.getEnergyStored(stack) + ChatFormatting.DARK_GRAY + "/" + ChatFormatting.AQUA + this.getMaxEnergyStored(stack) + ChatFormatting.DARK_GRAY + " RF");
	}
	
	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int extract = Math.min(maxExtract, stored);

		if (!simulate) {
			stored -= extract;
			container.stackTagCompound.setInteger("Energy", stored);
		}
		return extract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
			container.stackTagCompound.setInteger("Timer", 0);
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return maxEnergy;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

		if (!simulate) {
			stored += receive;
			container.stackTagCompound.setInteger("Energy", stored);
		}
		return receive;
	}	
	
}
