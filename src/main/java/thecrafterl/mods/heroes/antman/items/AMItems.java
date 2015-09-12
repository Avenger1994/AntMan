package thecrafterl.mods.heroes.antman.items;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

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
	
	public static Item mcuWaspHelmet;
	public static Item mcuWaspChestplate;
	public static Item mcuWaspLegs;
	public static Item mcuWaspBoots;
	public static Item mcuWaspCreativeChestplate;
	
	public static Item comicAntManHelmet;
	public static Item comicAntManChestplate;
	public static Item comicAntManLegs;
	public static Item comicAntManBoots;
	public static Item comicAntManCreativeChestplate;
	
	public static Item tank1;
	public static Item tank2;
	public static Item tank3;
	
	public static Item filter;
	public static Item antAntenna;
	public static Item yjArmorPlating;
	public static Item yjLaserArm;
	public static Item waspArmorPlating;
	public static Item waspWing;
	
	public static ArrayList<Item> items;
	
	public static void init() {
		items = new ArrayList<Item>();
		
		helmet = new ItemAntManArmorHelmet("helmet", 0, ShrinkerTypes.MCU_ANTMAN);
		
		if(AntMan.isRFModActive())
			chestplate = new ItemAntManArmorChestplateRF("chestplate", 1, ShrinkerTypes.MCU_ANTMAN);
		else
			chestplate = new ItemAntManArmorChestplate("chestplate", 1, ShrinkerTypes.MCU_ANTMAN);
		
		legs = new ItemAntManArmor("legs", 2, ShrinkerTypes.MCU_ANTMAN);
		boots = new ItemAntManArmor("boots", 3, ShrinkerTypes.MCU_ANTMAN);
		creativeChestplate = new ItemAntManArmorChestplateCreative("creativeChestplate", 1, ShrinkerTypes.MCU_ANTMAN);
		
		//-------------
		
		yjHelmet = new ItemAntManArmorHelmet("yjHelmet", 0, ShrinkerTypes.MCU_YELLOWJACKET);
		
		if(AntMan.isRFModActive())
			yjChestplate = new ItemAntManArmorChestplateRF("yjChestplate", 1, ShrinkerTypes.MCU_YELLOWJACKET);
		else
			yjChestplate = new ItemAntManArmorChestplate("yjChestplate", 1, ShrinkerTypes.MCU_YELLOWJACKET);
		
		yjLegs = new ItemAntManArmor("yjLegs", 2, ShrinkerTypes.MCU_YELLOWJACKET);
		yjBoots = new ItemAntManArmor("yjBoots", 3, ShrinkerTypes.MCU_YELLOWJACKET);
		yjCreativeChestplate = new ItemAntManArmorChestplateCreative("yjCreativeChestplate", 1, ShrinkerTypes.MCU_YELLOWJACKET);
		
		//-------------
		
		mcuWaspHelmet = new ItemAntManArmorHelmet("mcuWaspHelmet", 0, ShrinkerTypes.MCU_WASP);
		
		if(AntMan.isRFModActive())
			mcuWaspChestplate = new ItemAntManArmorChestplateRF("mcuWaspChestplate", 1, ShrinkerTypes.MCU_WASP);
		else
			mcuWaspChestplate = new ItemAntManArmorChestplate("mcuWaspChestplate", 1, ShrinkerTypes.MCU_WASP);
		
		mcuWaspLegs = new ItemAntManArmor("mcuWaspLegs", 2, ShrinkerTypes.MCU_WASP);
		mcuWaspBoots = new ItemAntManArmor("mcuWaspBoots", 3, ShrinkerTypes.MCU_WASP);
		mcuWaspCreativeChestplate = new ItemAntManArmorChestplateCreative("mcuWaspCreativeChestplate", 1, ShrinkerTypes.MCU_WASP);
		
		//-------------
		
		comicAntManHelmet = new ItemAntManArmorHelmet("comicAntManHelmet", 0, ShrinkerTypes.COMIC_ANTMAN);
		
		if(AntMan.isRFModActive())
			comicAntManChestplate = new ItemAntManArmorChestplateRF("comicAntManChestplate", 1, ShrinkerTypes.COMIC_ANTMAN);
		else
			comicAntManChestplate = new ItemAntManArmorChestplate("comicAntManChestplate", 1, ShrinkerTypes.COMIC_ANTMAN);
		
		comicAntManLegs = new ItemAntManArmor("comicAntManLegs", 2, ShrinkerTypes.COMIC_ANTMAN);
		comicAntManBoots = new ItemAntManArmor("comicAntManBoots", 3, ShrinkerTypes.COMIC_ANTMAN);
		comicAntManCreativeChestplate = new ItemAntManArmorChestplateCreative("comicAntManCreativeChestplate", 1, ShrinkerTypes.COMIC_ANTMAN);
		
		//-------------
		
		tank1 = new ItemPymParticleTank(1);
		tank2 = new ItemPymParticleTank(2);
		tank3 = new ItemPymParticleTank(3);
		
		filter = new ItemBase("filter");
		antAntenna = new ItemBase("antAntenna");
		yjArmorPlating = new ItemBase("yjArmorPlating");
		yjLaserArm = new ItemBase("yjLaserArm");
		waspArmorPlating = new ItemBase("waspArmorPlating");
		waspWing = new ItemBase("waspWing");
	}
	
	public static final ArmorMaterial antMan = EnumHelper.addArmorMaterial("antMan", 25, new int[]{4, 8, 7, 4}, 9);
	public static final ArmorMaterial yellowJacket = EnumHelper.addArmorMaterial("yellowJacket", 33, new int[]{5, 9, 8, 5}, 9);
	
	public static enum ShrinkerTypes {
		MCU_ANTMAN("MCU", antMan, "random.anvil_land", false, true),
		MCU_YELLOWJACKET("MCU", yellowJacket, "random.anvil_land", true, false),
		MCU_WASP("WIP | MCU", antMan, "random.anvil_land", true, false),
		COMIC_ANTMAN("Comic", antMan, "random.anvil_land", false, true);
		
		private boolean canFly;
		private boolean canControlAnts;
		private String desc;
		private ArmorMaterial material;
		private String helmetSound;
		
		private ShrinkerTypes(String desc, ArmorMaterial material, String helmetSound, boolean canFly, boolean canControlAnts) {
			this.canFly = canFly;
			this.canControlAnts = canControlAnts;
			this.desc = desc;
			this.material = material;
			this.helmetSound = helmetSound;
		}
		
		public boolean canFly() {
			return canFly;
		}
		
		public boolean canControlAnts() {
			return canControlAnts;
		}
		
		public ArmorMaterial getArmorMaterial() {
			return material;
		}
		
		public String getDescription() {
			return desc;
		}
		
		public String getHelmetSound() {
			return helmetSound;
		}
	}

}
