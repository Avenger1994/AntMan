package thecrafterl.mods.heroes.antman.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.ShrinkerTypesHandlerClient;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class ItemAntManArmorChestplateCreative extends ItemAntManArmorChestplate {

	public ItemAntManArmorChestplateCreative(String name, int i, ShrinkerTypes type) {
		super(name, i, type);
		this.setTextureName(AntMan.ASSETDIR + name);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(this.setEnergyDefaultTags(new ItemStack(item), maxEnergy, amountTier1));
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		setDefaultTags(stack);
		list.add(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.pymparticles") + ChatFormatting.DARK_GRAY + ":");
		list.add("   " + ShrinkerTypesHandlerClient.getChatColor(getShrinkerType()) + StatCollector.translateToLocal("antman.info.infinite"));
	}

	@Override
	public boolean isEmpty(ItemStack stack) {
		return PymParticleHandler.getPymParticles(stack) == 0;
	}

	@Override
	public void addPymParticles(ItemStack stack, int i) {
		
	}

	@Override
	public void removePymParticles(ItemStack stack, int i) {
		
	}

	@Override
	public int getPymParticles(ItemStack stack) {
		return getMaxPymParticles(stack);
	}

	@Override
	public boolean hasEnoughPymParticles(ItemStack stack, int i) {
		return true;
	}

	@Override
	public boolean canAddPymParticles(ItemStack stack, int i) {
		return false;
	}
	
}
