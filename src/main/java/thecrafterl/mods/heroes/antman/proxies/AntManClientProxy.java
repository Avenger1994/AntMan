package thecrafterl.mods.heroes.antman.proxies;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.client.ClientKeyHandler;
import thecrafterl.mods.heroes.antman.client.ShrinkerTypesHandlerClient;
import thecrafterl.mods.heroes.antman.client.models.ModelCrazyAnt;
import thecrafterl.mods.heroes.antman.client.render.AntManPlayerRenderer;
import thecrafterl.mods.heroes.antman.client.render.ClientRenderer;
import thecrafterl.mods.heroes.antman.client.render.ItemRendererPymWorkbench;
import thecrafterl.mods.heroes.antman.client.render.RenderPymWorkbench;
import thecrafterl.mods.heroes.antman.entity.EntityCrazyAnt;
import thecrafterl.mods.heroes.antman.entity.render.RenderCrazyAnt;
import thecrafterl.mods.heroes.antman.recipe.AMNotEnoughItemsHandler;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymWorkbench;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class AntManClientProxy extends AntManProxy {

	public void registerHandlers() {
		FMLCommonHandler.instance().bus().register(new ClientKeyHandler());
		ShrinkerTypesHandlerClient.init();
		
		if(Loader.isModLoaded("NotEnoughItems")) {
			AMNotEnoughItemsHandler.init();
		}
	}
	
	public void registerRenderer() {
		RenderPlayerAPI.register("AntMan", AntManPlayerRenderer.class);
		FMLCommonHandler.instance().bus().register(new ClientRenderer());
		MinecraftForge.EVENT_BUS.register(new ClientRenderer());
		TileEntitySpecialRenderer renderWorkbench = new RenderPymWorkbench();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPymWorkbench.class, renderWorkbench);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AMBlocks.pymWorkbench), new ItemRendererPymWorkbench(renderWorkbench, new TileEntityPymWorkbench()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrazyAnt.class, new RenderCrazyAnt(new ModelCrazyAnt(), 0f));
	}
	
    @Override
    public EntityPlayer getPlayerFromPacket(ChannelHandlerContext ctx) {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            return Minecraft.getMinecraft().thePlayer;
        }

        return super.getPlayerFromPacket(ctx);
    }
	
}