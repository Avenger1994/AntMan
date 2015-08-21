package thecrafterl.mods.heroes.antman.items;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class AMItems {

	public static Item helmet;
	public static Item chestplate;
	public static Item legs;
	public static Item boots;
	public static Item creativeChestplate;
	
	public static Item yjHelmet;
	public static Item yjChestplate;
	public static Item yjLegs;
	public static Item yjBoots;
	public static Item yjCreativeChestplate;
	
	public static Item tank1;
	public static Item tank2;
	public static Item tank3;
	
	public static Item filter;
	public static Item antAntenna;
	public static Item yjArmorPlating;
	public static Item yjLaserArm;
	
	public static ArrayList<Item> items;
	
	public static void init() {
		items = new ArrayList<Item>();
		
		helmet = new ItemAntManArmorHelmet("helmet", 0, ShrinkerTypes.ANTMAN);
		
		if(AntMan.isRFModActive())
			chestplate = new ItemAntManArmorChestplateRF("chestplate", 1, ShrinkerTypes.ANTMAN);
		else
			chestplate = new ItemAntManArmorChestplate("chestplate", 1, ShrinkerTypes.ANTMAN);
		
		legs = new ItemAntManArmor("legs", 2, ShrinkerTypes.ANTMAN);
		boots = new ItemAntManArmor("boots", 3, ShrinkerTypes.ANTMAN);
		creativeChestplate = new ItemAntManArmorChestplateCreative("creativeChestplate", 1, ShrinkerTypes.ANTMAN);
		
		//-------------
		
		yjHelmet = new ItemYellowjacketHelmet("yjHelmet", 0, ShrinkerTypes.YELLOWJACKET);
		
		if(AntMan.isRFModActive())
			yjChestplate = new ItemAntManArmorChestplateRF("yjChestplate", 1, ShrinkerTypes.YELLOWJACKET);
		else
			yjChestplate = new ItemAntManArmorChestplate("yjChestplate", 1, ShrinkerTypes.YELLOWJACKET);
		
		yjLegs = new ItemAntManArmor("yjLegs", 2, ShrinkerTypes.YELLOWJACKET);
		yjBoots = new ItemAntManArmor("yjBoots", 3, ShrinkerTypes.YELLOWJACKET);
		yjCreativeChestplate = new ItemAntManArmorChestplateCreative("yjCreativeChestplate", 1, ShrinkerTypes.YELLOWJACKET);
		
		//-------------
		
		tank1 = new ItemPymParticleTank(1);
		tank2 = new ItemPymParticleTank(2);
		tank3 = new ItemPymParticleTank(3);
		
		filter = new ItemBase("filter");
		antAntenna = new ItemBase("antAntenna");
		yjArmorPlating = new ItemBase("yjArmorPlating");
		yjLaserArm = new ItemBase("yjLaserArm");
	}
	
	public static enum ShrinkerTypes {
		ANTMAN,
		YELLOWJACKET,
		WASP;
	}

}
