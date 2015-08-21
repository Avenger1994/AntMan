package thecrafterl.mods.heroes.antman.network;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorHelmet;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.AntManHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageToggleHelmet implements IMessage, IMessageHandler<MessageToggleHelmet, IMessage> {
    
    public MessageToggleHelmet() {
    	
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
    	
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
    	
    }
    
    @Override
    public IMessage onMessage(MessageToggleHelmet msg, MessageContext ctx) {
        EntityPlayer player = getPlayerEntity(ctx);
        
        if (player != null) {
    		if(!AntMan.isSmall(player)) {
    			AntMan.toggleHelmet(player);
    		}
        }
        return null;
    }
    
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
    
}