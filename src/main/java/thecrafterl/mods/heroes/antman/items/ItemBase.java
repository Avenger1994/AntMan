package thecrafterl.mods.heroes.antman.items;

import cpw.mods.fml.common.registry.GameRegistry;
import thecrafterl.mods.heroes.antman.AntMan;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	private String customUnlocalizedName;

	public ItemBase(String name) {
		this.setUnlocalizedName(AntMan.MODID.toLowerCase() + "." + name);
		this.setTextureName(AntMan.ASSETDIR + name);
		this.setCreativeTab(AntMan.tabAntMan);
		
		GameRegistry.registerItem(this, AntMan.MODID.toLowerCase() + "." + name);
		
		this.customUnlocalizedName = name;
		AMItems.items.add(this);
	}
	
	public String getCustomUnlocalizedName() {
		return customUnlocalizedName;
	}

}
