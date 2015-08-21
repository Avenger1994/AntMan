package thecrafterl.mods.heroes.antman.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotOneItem extends Slot {

	private ItemStack validItem;
	
	public SlotOneItem(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, ItemStack item) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		validItem = item;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack != null && stack.getItem() == validItem.getItem() && stack.getItemDamage() == validItem.getItemDamage();
	}

}
