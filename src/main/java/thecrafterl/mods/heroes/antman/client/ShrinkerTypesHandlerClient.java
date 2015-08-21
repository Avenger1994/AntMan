package thecrafterl.mods.heroes.antman.client;

import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thecrafterl.mods.heroes.antman.client.models.ModelAdvancedArmor;
import thecrafterl.mods.heroes.antman.client.models.ModelYellowjacketChestplate;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;

public class ShrinkerTypesHandlerClient {
	
	public static void init() {

	}
	
	@SideOnly(Side.CLIENT)
	public static ModelAdvancedArmor getModel(ShrinkerTypes type) {
		switch (type) {
		case ANTMAN:
			return new ModelAdvancedArmor(0.25F, 64, 64);
		case YELLOWJACKET:
			return new ModelYellowjacketChestplate(0.25F, 128, 64);
		default:
			return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static ChatFormatting getChatColor(ShrinkerTypes type) {
		switch (type) {
		case ANTMAN:
			return ChatFormatting.RED;
		case YELLOWJACKET:
			return ChatFormatting.YELLOW;
		default:
			return null;
		}
	}
	
}
