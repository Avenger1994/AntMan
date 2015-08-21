package thecrafterl.mods.heroes.antman.gui;

import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.container.ContainerPymParticleProducer;
import thecrafterl.mods.heroes.antman.container.ContainerPymWorkbench;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymParticleProducer;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymWorkbench;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public class AMGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(ID == AMBlocks.pymParticleProducerGuiId && entity instanceof TileEntityPymParticleProducer) {
			return new ContainerPymParticleProducer(player.inventory, (TileEntityPymParticleProducer) entity);
		}
		
		if(ID == AMBlocks.pymWorkbenchGuiId && entity instanceof TileEntityPymWorkbench) {
			return ID == AMBlocks.pymWorkbenchGuiId && world.getBlock(x, y, z) == AMBlocks.pymWorkbench ? new ContainerPymWorkbench(player.inventory, world, x, y, z) : null;
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(ID == AMBlocks.pymParticleProducerGuiId && entity instanceof TileEntityPymParticleProducer) {
			return new GuiPymParticleProducer(player.inventory, (TileEntityPymParticleProducer) entity);
		}
		
		if(ID == AMBlocks.pymWorkbenchGuiId) {
			return ID == AMBlocks.pymWorkbenchGuiId && world.getBlock(x, y, z) == AMBlocks.pymWorkbench ? new GuiPymWorkbench(player.inventory, world, x, y, z) : null;
		}
		
		return null;
	}

}
