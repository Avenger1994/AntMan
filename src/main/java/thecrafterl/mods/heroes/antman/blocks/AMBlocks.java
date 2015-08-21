package thecrafterl.mods.heroes.antman.blocks;

import thecrafterl.mods.heroes.antman.AntMan;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;

public class AMBlocks {
	
	public static Block pymParticleProducer;
	public static int pymParticleProducerGuiId = 0;
	
	public static Block pymWorkbench;
	public static int pymWorkbenchGuiId = 1;
	
	public static Fluid pymParticles;
	public static BlockFluidClassic blockFluidPymParticles;
	
	public static void init() {
		
		pymParticles = new Fluid("pymParticles").setUnlocalizedName("pymParticles");
		FluidRegistry.registerFluid(pymParticles);
		blockFluidPymParticles = new BlockPymParticlesFluid("pymParticles", pymParticles);
		pymParticleProducer = new BlockPymParticleProducer();
		pymWorkbench = new BlockPymWorkbench();
		
	}
	
}
