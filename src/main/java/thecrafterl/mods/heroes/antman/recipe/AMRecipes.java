package thecrafterl.mods.heroes.antman.recipe;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.items.AMItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class AMRecipes {
	
	public static ItemStack capacitorBasic;
	public static ItemStack basicCapacitor;
	
	public static void init() {
		
		capacitorBasic = GameRegistry.findItemStack("ThermalExpansion", "capacitorBasic", 1);
		Item capacitor = GameRegistry.findItem("EnderIO", "itemBasicCapacitor");
		basicCapacitor = new ItemStack(capacitor, 1, 0);
				
		if(AMConfig.vanillaRecipes) {
			GameRegistry.addRecipe(new ShapedOreRecipe(AMBlocks.pymWorkbench, new Object[] {"BCB", "I I", "IPI", 'C', Blocks.crafting_table, 'I', "ingotIron", 'P', Items.paper, 'B', "blockIron"}));
			
			if(AMConfig.recipesTank1) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank1), new Object[] {"XGX", "XGX", 'X', Items.iron_ingot, 'G', Blocks.glass_pane}));
			if(AMConfig.recipesTank2) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank2), new Object[] {"XTX", "XGX", "XTX", 'X', Items.iron_ingot, 'G', Blocks.glass_pane, 'T', AMItems.tank1}));
			if(AMConfig.recipesTank3) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank3), new Object[] {"X1X", "BGB", "X2X", 'X', Items.iron_ingot, 'G', Blocks.glass, '1', AMItems.tank1, '2', AMItems.tank2, 'B', Blocks.iron_block}));
			
			if(AMConfig.recipesFilter) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.filter), new Object[] {"IGI", "GRG", "IGI", 'I', Items.iron_ingot, 'G', Blocks.iron_bars, 'R', Items.redstone}));
			if(AMConfig.recipesAntAntenna) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.antAntenna), new Object[] {"  I", " I ", "R  ", 'I', "ingotIron", 'R', "dustRedstone"}));
			if(AMConfig.recipesYJArmorPlating) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjArmorPlating), new Object[] {"III", "WGW", "III", 'I', "ingotIron", 'G', "ingotGold", 'W', new ItemStack(Blocks.wool, 1, 15)}));
			if(AMConfig.recipesYJLaserArm) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLaserArm), new Object[] {"R ", " P", "P ", 'P', AMItems.yjArmorPlating, 'R', "blockRedstone"}));
			
			if(AMConfig.recipesPymParticleProducer) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMBlocks.pymParticleProducer), new Object[] {"CIC", "2R1", "CCC", 'I', Items.iron_ingot, 'C', Blocks.cobblestone, '1', AMItems.tank1, '2', AMItems.tank2, 'R', Items.redstone}));
			
			if(AMConfig.recipesHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.helmet), new Object[] {"AIA", "GIG", "IFI", 'I', "ingotIron", 'A', AMItems.antAntenna, 'G', "paneGlassRed", 'F', AMItems.filter}));
			if(AMConfig.recipesChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.chestplate), new Object[] {"I I", "WTW", "IBI", 'I', "ingotIron", 'W', new ItemStack(Blocks.wool, 1, 14), 'T', AMItems.tank1, 'B', "blockIron"}));
			if(AMConfig.recipesLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.legs), new Object[] {"WBW", "I I", "I I", 'I', "ingotIron", 'W', new ItemStack(Blocks.wool, 1, 14), 'B', "blockIron"}));
			if(AMConfig.recipesBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.boots), new Object[] {"I I", "W W", 'I', "ingotIron", 'W', new ItemStack(Blocks.wool, 1, 7)}));
			
			if(AMConfig.recipesYJHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjHelmet), new Object[] {"PIP", "GPG", "BFB", 'I', "ingotIron", 'P', AMItems.yjArmorPlating, 'G', "paneGlassYellow", 'F', AMItems.filter, 'B', "blockIron"}));
			if(AMConfig.recipesYJChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjChestplate), new Object[] {"A A", "PTP", "PBP", 'T', AMItems.tank1, 'B', "blockIron", 'A', AMItems.yjLaserArm, 'P', AMItems.yjArmorPlating}));
			if(AMConfig.recipesYJLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLegs), new Object[] {"BPB", "P P", "I I", 'I', "ingotIron", 'B', "blockIron", 'P', AMItems.yjArmorPlating}));
			if(AMConfig.recipesYJBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjBoots), new Object[] {"I I", "P P", 'I', "ingotIron", 'P', AMItems.yjArmorPlating}));
		}
		
		//----------------
		
		if(AMConfig.TERecipes && Loader.isModLoaded("ThermalExpansion")) {
			GameRegistry.addRecipe(new ShapedOreRecipe(AMBlocks.pymWorkbench, new Object[] {"BCB", "I I", "IPI", 'C', Blocks.crafting_table, 'I', "ingotInvar", 'P', Items.paper, 'B', "blockInvar"}));
			
			if(AMConfig.recipesTank1) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank1), new Object[] {"XGX", "XGX", 'X', "ingotInvar", 'G', Blocks.glass_pane}));
			if(AMConfig.recipesTank2) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank2), new Object[] {"XTX", "XGX", "XTX", 'X', "ingotInvar", 'G', Blocks.glass_pane, 'T', AMItems.tank1}));
			if(AMConfig.recipesTank3) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank3), new Object[] {"X1X", "BGB", "X2X", 'X', "ingotInvar", 'G', "blockGlassHardened", '1', AMItems.tank1, '2', AMItems.tank2, 'B', "blockInvar"}));
		
			if(AMConfig.recipesFilter) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.filter), new Object[] {"IGI", "GRG", "IGI", 'I', "ingotInvar", 'G', Blocks.iron_bars, 'R', Items.redstone}));
			if(AMConfig.recipesAntAntenna) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.antAntenna), new Object[] {"  I", " I ", "R  ", 'I', "ingotInvar", 'R', "dustRedstone"}));
			if(AMConfig.recipesYJArmorPlating) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjArmorPlating), new Object[] {"III", "WGW", "III", 'I', "ingotInvar", 'G', "ingotGold", 'W', new ItemStack(Blocks.wool, 1, 15)}));
			if(AMConfig.recipesYJLaserArm) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLaserArm), new Object[] {"R ", " P", "P ", 'P', AMItems.yjArmorPlating, 'R', capacitorBasic}));
			
			if(AMConfig.recipesPymParticleProducer) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMBlocks.pymParticleProducer), new Object[] {"CIC", "2R1", "CCC", 'I', "ingotInvar", 'C', Blocks.cobblestone, '1', AMItems.tank1, '2', AMItems.tank2, 'R', Items.redstone}));
		
			if(AMConfig.recipesHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.helmet), new Object[] {"AIA", "GIG", "IFI", 'I', "ingotInvar", 'A', AMItems.antAntenna, 'G', "paneGlassRed", 'F', AMItems.filter}));
			if(AMConfig.recipesChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.chestplate), new Object[] {"IEI", "WTW", "IBI", 'I', "ingotInvar", 'W', new ItemStack(Blocks.wool, 1, 14), 'T', AMItems.tank1, 'B', "blockInvar", 'E', capacitorBasic}));
			if(AMConfig.recipesLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.legs), new Object[] {"WBW", "I I", "I I", 'I', "ingotInvar", 'W', new ItemStack(Blocks.wool, 1, 14), 'B', "blockInvar"}));
			if(AMConfig.recipesBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.boots), new Object[] {"I I", "W W", 'I', "ingotInvar", 'W', new ItemStack(Blocks.wool, 1, 7)}));
			
			if(AMConfig.recipesYJHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjHelmet), new Object[] {"PIP", "GPG", "BFB", 'I', "ingotInvar", 'P', AMItems.yjArmorPlating, 'G', "paneGlassYellow", 'F', AMItems.filter, 'B', "blockInvar"}));
			if(AMConfig.recipesYJChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjChestplate), new Object[] {"ACA", "PTP", "PBP", 'T', AMItems.tank1, 'B', "blockInvar", 'A', AMItems.yjLaserArm, 'P', AMItems.yjArmorPlating, 'C', capacitorBasic}));
			if(AMConfig.recipesYJLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLegs), new Object[] {"BPB", "P P", "I I", 'I', "ingotInvar", 'B', "blockInvar", 'P', AMItems.yjArmorPlating}));
			if(AMConfig.recipesYJBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjBoots), new Object[] {"I I", "P P", 'I', "ingotInvar", 'P', AMItems.yjArmorPlating}));
		}
		
		//----------------
		
		if(AMConfig.enderIORecipes && Loader.isModLoaded("EnderIO")) {
			GameRegistry.addRecipe(new ShapedOreRecipe(AMBlocks.pymWorkbench, new Object[] {"BCB", "I I", "IPI", 'C', Blocks.crafting_table, 'I', "ingotElectricalSteel", 'P', Items.paper, 'B', "blockElectricalSteel"}));
			
			if(AMConfig.recipesTank1) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank1), new Object[] {"XGX", "XGX", 'X', "ingotElectricalSteel", 'G', Blocks.glass_pane}));
			if(AMConfig.recipesTank2) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank2), new Object[] {"XTX", "XGX", "XTX", 'X', "ingotElectricalSteel", 'G', Blocks.glass_pane, 'T', AMItems.tank1}));
			if(AMConfig.recipesTank3) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.tank3), new Object[] {"X1X", "BGB", "X2X", 'X', "ingotElectricalSteel", 'G', "blockGlassHardened", '1', AMItems.tank1, '2', AMItems.tank2, 'B', "blockElectricalSteel"}));
		
			if(AMConfig.recipesFilter) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.filter), new Object[] {"IGI", "GRG", "IGI", 'I', "ingotElectricalSteel", 'G', Blocks.iron_bars, 'R', Items.redstone}));
			if(AMConfig.recipesAntAntenna) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.antAntenna), new Object[] {"  I", " I ", "R  ", 'I', "ingotElectricalSteel", 'R', "dustRedstone"}));
			if(AMConfig.recipesYJArmorPlating) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjArmorPlating), new Object[] {"III", "WGW", "III", 'I', "ingotElectricalSteel", 'G', "ingotGold", 'W', new ItemStack(Blocks.wool, 1, 15)}));
			if(AMConfig.recipesYJLaserArm) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLaserArm), new Object[] {"R ", " P", "P ", 'P', AMItems.yjArmorPlating, 'R', capacitor}));
			
			if(AMConfig.recipesPymParticleProducer) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMBlocks.pymParticleProducer), new Object[] {"CIC", "2R1", "CCC", 'I', "ingotElectricalSteel", 'C', Blocks.cobblestone, '1', AMItems.tank1, '2', AMItems.tank2, 'R', Items.redstone}));
		
			if(AMConfig.recipesHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.helmet), new Object[] {"AIA", "GIG", "IFI", 'I', "ingotElectricalSteel", 'A', AMItems.antAntenna, 'G', "paneGlassRed", 'F', AMItems.filter}));
			if(AMConfig.recipesChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.chestplate), new Object[] {"RCR", "WTW", "IRI", 'I', "ingotElectricalSteel", 'W', new ItemStack(Blocks.wool, 1, 14), 'T', AMItems.tank1, 'C', capacitor, 'R', "ingotRedstoneAlloy"}));
			if(AMConfig.recipesLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.legs), new Object[] {"RIR", "I I", "I I", 'I', "ingotElectricalSteel", 'W', new ItemStack(Blocks.wool, 1, 14), 'R', "ingotRedstoneAlloy"}));
			if(AMConfig.recipesBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.boots), new Object[] {"I I", "W W", 'I', "ingotElectricalSteel", 'W', new ItemStack(Blocks.wool, 1, 7)}));
			
			if(AMConfig.recipesYJHelmet) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjHelmet), new Object[] {"PIP", "GPG", "BFB", 'I', "ingotElectricalSteel", 'P', AMItems.yjArmorPlating, 'G', "paneGlassYellow", 'F', AMItems.filter, 'B', "blockElectricalSteel"}));
			if(AMConfig.recipesYJChestplate) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjChestplate), new Object[] {"ACA", "PTP", "PBP", 'T', AMItems.tank1, 'B', "blockElectricalSteel", 'A', AMItems.yjLaserArm, 'P', AMItems.yjArmorPlating, 'C', capacitor}));
			if(AMConfig.recipesYJLegs) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjLegs), new Object[] {"BPB", "P P", "I I", 'I', "ingotElectricalSteel", 'B', "blockElectricalSteel", 'P', AMItems.yjArmorPlating}));
			if(AMConfig.recipesYJBoots) PymWorkbenchCraftingManager.recipes.add(new ShapedOreRecipe(new ItemStack(AMItems.yjBoots), new Object[] {"I I", "P P", 'I', "ingotElectricalSteel", 'P', AMItems.yjArmorPlating}));
		}
		
		//----------------
		
		if(AMConfig.ironManAdditionalRecipes && Loader.isModLoaded("IronMan")) {
			
		}
		
	}

}
