package thecrafterl.mods.heroes.antman.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thecrafterl.mods.heroes.antman.client.models.ModelAdvancedArmor;
import thecrafterl.mods.heroes.antman.client.models.ModelAntManHelmet;
import thecrafterl.mods.heroes.antman.client.models.ModelWaspWings;
import thecrafterl.mods.heroes.antman.client.models.ModelYellowjacketChestplate;
import thecrafterl.mods.heroes.antman.client.models.ModelYellowjacketHelmet;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;

public class ShrinkerTypesHandlerClient {
	
	public static void init() {

	}
	
	@SideOnly(Side.CLIENT)
	public static ModelAdvancedArmor getChestplateModel(ShrinkerTypes type) {
		switch (type) {
		case MCU_ANTMAN:
			return new ModelAdvancedArmor(0.25F, 64, 64);
		case MCU_YELLOWJACKET:
			return new ModelYellowjacketChestplate(0.25F, 128, 64);
		case MCU_WASP:
			return new ModelWaspWings(0.25F, 128, 64);
		default:
			return new ModelAdvancedArmor(0.25F, 64, 64);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static ModelBiped getHelmetModel(ShrinkerTypes type) {
		switch (type) {
		case MCU_ANTMAN:
			return new ModelAntManHelmet(0.5F);
		case COMIC_ANTMAN:
			return new ModelAntManHelmet(0.5F);
		case MCU_YELLOWJACKET:
			return new ModelYellowjacketHelmet(0.5F);
		default:
			return new ModelBiped(0.5F);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static ChatFormatting getChatColor(ShrinkerTypes type) {
		switch (type) {
		case MCU_ANTMAN:
			return ChatFormatting.RED;
		case COMIC_ANTMAN:
			return ChatFormatting.RED;
		case MCU_YELLOWJACKET:
			return ChatFormatting.YELLOW;
		case MCU_WASP:
			return ChatFormatting.YELLOW;
		default:
			return null;
		}
	}
	
}
