package thecrafterl.mods.heroes.antman.recipe;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thecrafterl.mods.heroes.antman.gui.GuiPymWorkbench;
import thecrafterl.mods.heroes.antman.items.AMItems;
import codechicken.nei.api.API;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class AMNotEnoughItemsHandler {

	public static void init() {
		RecipeHandlerPymWorkbench rhpw = new RecipeHandlerPymWorkbench();
		
		API.registerRecipeHandler(rhpw);
		API.registerUsageHandler(rhpw);
	}
	
}
