package thecrafterl.mods.heroes.antman.blocks;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymWorkbench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPymWorkbench extends BlockContainer {

	private String name = AntMan.ASSETDIR + "pymWorkbench";

	public BlockPymWorkbench() {
		super(Material.rock);

		float pixel = 1 / 16;
		
		this.setBlockName("pymWorkbench");
		this.setCreativeTab(AntMan.tabAntMan);
		this.setBlockTextureName("cauldron_inner");
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F - pixel, 1F);

		setHardness(5F);
		setResistance(5F);
		setHarvestLevel("pickaxe", 1);
		
		GameRegistry.registerBlock(this, "pymWorkbench");
		GameRegistry.registerTileEntity(TileEntityPymWorkbench.class, "TileEntityPymWorkbench");
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int q, float a, float b, float c) {

		if (!player.isSneaking()) {
			player.openGui(AntMan.instance, AMBlocks.pymWorkbenchGuiId, world, x, y, z);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPymWorkbench();
	}

}
