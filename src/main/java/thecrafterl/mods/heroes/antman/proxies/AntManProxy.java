package thecrafterl.mods.heroes.antman.proxies;

import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.common.MinecraftForge;

public class AntManProxy {

	public void registerHandlers() {
		
	}

	public void registerRenderer() {
		
	}
	
	public EntityPlayer getPlayerFromPacket(ChannelHandlerContext ctx) {
        NetHandlerPlayServer handler = (NetHandlerPlayServer)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        return handler.playerEntity;
	}

}
