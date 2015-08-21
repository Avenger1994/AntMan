package thecrafterl.mods.heroes.antman.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymParticleProducer;
import thecrafterl.mods.heroes.antman.util.AntManUtils;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockPymParticleProducer extends BlockContainer {

	public Class tileEntityClass = TileEntityPymParticleProducer.class;
	
	public IIcon iconSide;
	public IIcon iconTop;

	public IIcon iconFrontOff;
	public IIcon iconFrontOn;
	
	protected BlockPymParticleProducer() {
		super(Material.iron);
		
		setHardness(12F);
		setResistance(16F);
		setHarvestLevel("pickaxe", 1);
		
		setBlockName("pymParticleProducer");
		setCreativeTab(AntMan.tabAntMan);
		
		GameRegistry.registerBlock(this, "pymParticleProducer");
		GameRegistry.registerTileEntity(tileEntityClass, "ironman.tileentity." + "pymParticleProducer");
	}
	
	public int getCraftingSpeed(int meta) {
		return 2500;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		try {
			return (TileEntityPymParticleProducer) tileEntityClass.newInstance();
		} catch (Exception ex) {
			System.out.println("Could not create TileEntity!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public static final String energyStored = "energyStored";
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int var6 = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TileEntityPymParticleProducer tileEntity = (TileEntityPymParticleProducer) world.getTileEntity(x, y, z);

		byte facing = 3;

		switch (var6) {
		case 0:
			facing = 2;
			break;
		case 1:
			facing = 5;
			break;
		case 3:
			facing = 4;
			break;
		}

		tileEntity.facing = facing;

		world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f1, float f2, float f3) {
		if (world.isRemote) {
			return true;
		}


		if (!(player instanceof FakePlayer)) {
			FMLNetworkHandler.openGui(player, AntMan.instance, AMBlocks.pymParticleProducerGuiId, world, x, y, z);
		}

		return true;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconSide = iconRegister.registerIcon("furnace_side");
		iconTop = iconRegister.registerIcon("furnace_top");
        iconFrontOff = iconRegister.registerIcon(AntMan.ASSETDIR + "pymp-producer_off");
        iconFrontOn = iconRegister.registerIcon(AntMan.ASSETDIR + "pymp-producer_on");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 3) {
			return iconFrontOff;
		}

		return side <= 1 ? iconTop : iconSide;
	}

	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		TileEntityPymParticleProducer machine = (TileEntityPymParticleProducer) blockAccess.getTileEntity(x, y, z);

		if (side == machine.facing) {
			return machine.active ? iconFrontOn : iconFrontOff;
		}

		return side <= 1 ? iconTop : iconSide;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		AntManUtils.dropBlockContents(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

}
