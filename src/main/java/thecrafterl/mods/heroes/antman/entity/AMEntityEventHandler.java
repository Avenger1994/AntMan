package thecrafterl.mods.heroes.antman.entity;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorHelmet;
import net.minecraft.entity.AntManHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class AMEntityEventHandler {

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			AntManPlayerData.get(player).onUpdate();
		}
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && AntManPlayerData.get((EntityPlayer) event.entity) == null) {
			event.entity.registerExtendedProperties(AntManPlayerData.EXT_PROP_NAME, new AntManPlayerData((EntityPlayer) event.entity));
		}
	}
	
	@SubscribeEvent
	public void onWorldJoin(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			AntManPlayerData.get(player).onWorldJoin();
		}
	}

}
