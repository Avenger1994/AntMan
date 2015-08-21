package thecrafterl.mods.heroes.antman.blocks;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.entity.AntManPlayerData;
import thecrafterl.mods.heroes.antman.entity.AntManSizes;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.AntManHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPymParticlesFluid extends BlockFluidClassic {
	
	public IIcon _iconFlowing;
	public IIcon _iconStill;
	
	private String name;
	
	private Fluid fluid;
	
	public BlockPymParticlesFluid(String name, Fluid fluid) {
		super(fluid, Material.lava);
		setBlockName("fluid." + name);
		this.name = name;
		this.fluid = fluid;
		GameRegistry.registerBlock(this, "fluidBlock." + name.toLowerCase());
	}
	
	public Fluid getFluid() {
		return fluid;
	}
	
	public IIcon getStillIcon() {
		return _iconStill;
	}
	
	public IIcon getFlowingIcon() {
		return _iconFlowing;
	}
	
	public String getName() {
		return name;
	}
	
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	if(entity instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) entity;
    		if (AntManPlayerData.get(player) == null) {
    			player.registerExtendedProperties(AntManPlayerData.EXT_PROP_NAME, new AntManPlayerData(player));
    		}
    		AntManPlayerData.get(player).setSize(AntManSizes.SMALL);
    	}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegistry) {
		_iconStill   = iconRegistry.registerIcon(AntMan.ASSETDIR + "fluids/" + name + ".still");
		_iconFlowing = iconRegistry.registerIcon(AntMan.ASSETDIR + "fluids/" + name + ".flowing");

		this.stack.getFluid().setIcons(_iconStill, _iconFlowing);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		return side <= 1 ? _iconStill : _iconFlowing;
	}
	
}
