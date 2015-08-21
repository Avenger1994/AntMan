package thecrafterl.mods.heroes.antman.util;

import net.minecraft.item.ItemStack;
import thecrafterl.mods.heroes.antman.items.IPymParticleContainer;

public class PymParticleHandler {

	public static int getPymParticles(ItemStack stack) {
		if(stack.getItem() instanceof IPymParticleContainer)
			return ((IPymParticleContainer)stack.getItem()).getPymParticles(stack);
		return 0;
	}
	
	public static int getMaxPymParticles(ItemStack stack) {
		if(stack.getItem() instanceof IPymParticleContainer)
			return ((IPymParticleContainer)stack.getItem()).getMaxPymParticles(stack);
		return 0;
	}

	public static void addPymParticles(ItemStack stack, int i) {
		if (stack.getItem() instanceof IPymParticleContainer)
			((IPymParticleContainer) stack.getItem()).addPymParticles(stack, i);
	}
	
	public static void removePymParticles(ItemStack stack, int i) {
		if (stack.getItem() instanceof IPymParticleContainer)
			((IPymParticleContainer) stack.getItem()).removePymParticles(stack, i);
	}
	
	public static boolean hasEnoughPymParticles(ItemStack stack, int i) {
		if(stack.getItem() instanceof IPymParticleContainer) {
			return ((IPymParticleContainer)stack.getItem()).hasEnoughPymParticles(stack, i);
		}
		return false;
	}
	
	public static boolean canAddPymParticles(ItemStack stack, int i) {
		if(stack.getItem() instanceof IPymParticleContainer) {
			return ((IPymParticleContainer)stack.getItem()).canAddPymParticles(stack, i);
		}
		return false;
	}
	
}
