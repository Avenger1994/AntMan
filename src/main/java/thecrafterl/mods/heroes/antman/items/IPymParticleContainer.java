package thecrafterl.mods.heroes.antman.items;

import net.minecraft.item.ItemStack;

public interface IPymParticleContainer {
	
	public int amountTier1 = 1000;
	public int amountTier2 = 2000;
	public int amountTier3 = 3000;
	
	public String pymParticlesTAG = "PymParticles";
	public String maxPymParticlesTAG = "MaxPymParticles";
	
	public int getPymParticles(ItemStack stack);
	
	public int getMaxPymParticles(ItemStack stack);
	
	public int getTankTier(ItemStack stack);
	
	public boolean isEmpty(ItemStack stack);
	
	public void addPymParticles(ItemStack stack, int i);
	
	public void removePymParticles(ItemStack stack, int i);
	
	public boolean hasEnoughPymParticles(ItemStack stack, int i);
	
	public boolean canAddPymParticles(ItemStack stack, int i);
	
}
