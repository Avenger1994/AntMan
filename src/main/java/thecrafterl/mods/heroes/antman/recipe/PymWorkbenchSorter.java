package thecrafterl.mods.heroes.antman.recipe;

import java.util.Comparator;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class PymWorkbenchSorter implements Comparator {

	final PymWorkbenchCraftingManager armorTable;
	
	public PymWorkbenchSorter(PymWorkbenchCraftingManager craftingManager) {
		this.armorTable = craftingManager;
	}
	
	public int compareRecipes(IRecipe irecipe1, IRecipe irecipe2) {
		return irecipe1 instanceof ShapedRecipes && irecipe2 instanceof ShapedRecipes ? 1: (irecipe2 instanceof ShapedRecipes && irecipe1 instanceof ShapedRecipes ? -1 : (irecipe2.getRecipeSize() < irecipe1.getRecipeSize() ? -1 : (irecipe2.getRecipeSize() > irecipe1.getRecipeSize() ? 1 : 0)));
	}

	@Override
	public int compare(Object o1, Object o2) {
		return this.compareRecipes((IRecipe)o1, (IRecipe)o2);
	}

}
