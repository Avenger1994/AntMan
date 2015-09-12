package thecrafterl.mods.heroes.antman;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class AMConfig {

	public static int usedEnergy;
	public static int energyPerChange;
	public static int particlesPerChange;
	public static boolean damageWhenSmallWithoutArmor;
	public static boolean showHUD;
	public static boolean slowWhenSmall;
	public static boolean strengthWhenSmall;
	public static int sizeDataWatcherId;
	public static boolean hideName;
	public static float hudScale;
	public static boolean showFirstPersonArmor;
	
	//Mod Support
	public static boolean modSupportThermalExpansion;
	public static boolean modSupportEnderIO;
	
	//Crafting
	public static boolean vanillaRecipes;
	public static boolean TERecipes;
	public static boolean enderIORecipes;
	public static boolean ironManAdditionalRecipes;
	
	//Recipes
	public static boolean tank1Recipe;
	public static boolean tank2Recipe;
	public static boolean tank3Recipe;
	
	public static boolean recipesHelmet;
	public static boolean recipesChestplate;
	public static boolean recipesLegs;
	public static boolean recipesBoots;
	
	public static boolean recipesYJHelmet;
	public static boolean recipesYJChestplate;
	public static boolean recipesYJLegs;
	public static boolean recipesYJBoots;
	
	public static boolean recipesMCUWaspHelmet;
	public static boolean recipesMCUWaspChestplate;
	public static boolean recipesMCUWaspLegs;
	public static boolean recipesMCUWaspBoots;
	
	public static boolean recipesTank1;
	public static boolean recipesTank2;
	public static boolean recipesTank3;
	public static boolean recipesFilter;
	public static boolean recipesAntAntenna;
	public static boolean recipesPymParticleProducer;
	public static boolean recipesYJArmorPlating;
	public static boolean recipesYJLaserArm;
	public static boolean recipesWaspArmorPlating;
	public static boolean recipesWaspWing;
	
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		usedEnergy = config.getInt("RF/t", "General", 1, 0, 250, "Energy which get lost when you're small");
		energyPerChange = config.getInt("RF/change", "General", 500, 0, 16000, "Energy which get lost when you change your size");
		particlesPerChange = config.getInt("PP/change", "General", 70, 0, 300, "PymParticles which get lost when you change your size");
		damageWhenSmallWithoutArmor = config.getBoolean("No Armor Damage", "General", true, "Damage when you're small without armor");
		showHUD = config.getBoolean("Show HUD", "General", true, "Show Energy HUD");
		slowWhenSmall = config.getBoolean("Increased Speed", "General", true, "When enabled you are slower.");
		strengthWhenSmall = config.getBoolean("Increased Strength", "General", true, "When enabled you get a strength buff.");
		sizeDataWatcherId = config.getInt("Datawatcher Size Id", "General", 21, 2, 31, "Use this if you have trouble with other mods. Useable IDs: 2-5; 10-15; 19-31");
		hideName = config.getBoolean("Hide Name", "General", false, "When enabled, you won't see the names of other players when they are small");
		hudScale = config.getFloat("HUD Scale", "General", 1.0F, 0.7F, 3.0F, "The scale of the HUD");
		showFirstPersonArmor = config.getBoolean("Show First Person Arm", "General", false, "When enabled, you will see the armor (buggy)");
		
		modSupportThermalExpansion = config.getBoolean("ThermalExpansion", "Mod Support", true, "The suit uses RF when ThermalExpansion is installed");
		modSupportEnderIO = config.getBoolean("EnderIO", "Mod Support", true, "The suit uses RF when EnderIO is installed");
		
		vanillaRecipes = config.getBoolean("Vanilla Recipes", "Recipe Sets", true, "Enables crafting recipes with vanilla items");
		TERecipes = config.getBoolean("ThermalExpansion Recipes", "Recipe Sets", true, "Enables crafting recipes with ThermalExpansion items");
		enderIORecipes = config.getBoolean("EnderIO Recipes", "Recipe Sets", true, "Enables crafting recipes with EnderIO items");
//		ironManAdditionalRecipes = config.getBoolean("IronMan Recipes", "Recipe Sets", true, "Adds additional recipes with my IronMan mod");
		
		recipesHelmet = config.get("Recipes", "Helmet", true).getBoolean();
		recipesChestplate = config.get("Recipes", "Chestplate", true).getBoolean();
		recipesLegs = config.get("Recipes", "Legs", true).getBoolean();
		recipesBoots = config.get("Recipes", "Boots", true).getBoolean();
		
		recipesYJHelmet = config.get("Recipes", "Yellowjacket Helmet", true).getBoolean();
		recipesYJChestplate = config.get("Recipes", "Yellowjacket Chestplate", true).getBoolean();
		recipesYJLegs = config.get("Recipes", "Yellowjacket Legs", true).getBoolean();
		recipesYJBoots = config.get("Recipes", "Yellowjacket Boots", true).getBoolean();
		
		recipesMCUWaspHelmet = config.get("Recipes", "MCU Wasp Helmet", true).getBoolean();
		recipesMCUWaspChestplate = config.get("Recipes", "MCU Wasp Chestplate", true).getBoolean();
		recipesMCUWaspLegs = config.get("Recipes", "MCU Wasp Legs", true).getBoolean();
		recipesMCUWaspBoots = config.get("Recipes", "MCU Wasp Boots", true).getBoolean();
		
		recipesTank1 = config.get("Recipes", "Tank Tier 1", true).getBoolean();
		recipesTank2 = config.get("Recipes", "Tank Tier 2", true).getBoolean();
		recipesTank3 = config.get("Recipes", "Tank Tier 3", true).getBoolean();
		recipesFilter = config.get("Recipes", "Filter", true).getBoolean();
		recipesAntAntenna = config.get("Recipes", "Ant Antenna", true).getBoolean();
		recipesPymParticleProducer = config.get("Recipes", "Pym-Particle Producer", true).getBoolean();
		recipesYJArmorPlating = config.get("Recipes", "Yellowjacket Armor Plating", true).getBoolean();
		recipesYJLaserArm = config.get("Recipes", "Yellowjacket Laser Arm", true).getBoolean();
		recipesWaspArmorPlating = config.get("Recipes", "Wasp Armor Plating", true).getBoolean();
		recipesWaspWing = config.get("Recipes", "Wasp Wing", true).getBoolean();
		
		config.save();
	}

}
