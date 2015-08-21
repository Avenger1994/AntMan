package thecrafterl.mods.heroes.antman.util;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IWrenchable {

	ItemStack onWrenched(ItemStack stack, TileEntity tileEntity);
	
}
