package thecrafterl.mods.heroes.antman.network;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class SyncTracker {
	
    private static Map<EntityPlayer, Boolean> flyKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> descendKeyState = new HashMap<EntityPlayer, Boolean>();
    
    private static Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> leftKeyState = new HashMap<EntityPlayer, Boolean>();
    private static Map<EntityPlayer, Boolean> rightKeyState = new HashMap<EntityPlayer, Boolean>();
    
    public static boolean isFlyKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return flyKeyState.containsKey(user) && flyKeyState.get(user);
        }
        return true;
    }
    
    public static boolean isDescendKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return descendKeyState.containsKey(user) && descendKeyState.get(user);
        }
        return false;
    }
    
    public static boolean isForwardKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return forwardKeyState.containsKey(user) && forwardKeyState.get(user);
        }
        return true;
    }
    
    public static boolean isBackwardKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return backwardKeyState.containsKey(user) && backwardKeyState.get(user);
        }
        return false;
    }
    
    public static boolean isLeftKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return leftKeyState.containsKey(user) && leftKeyState.get(user);
        }
        return false;
    }
    
    public static boolean isRightKeyDown(EntityLivingBase user) {
        if (user instanceof EntityPlayer) {
            return rightKeyState.containsKey(user) && rightKeyState.get(user);
        }
        return false;
    }
    
    public static void processKeyUpdate(EntityPlayer player, boolean keyFly, boolean keyDescend, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        flyKeyState.put(player, keyFly);
        descendKeyState.put(player, keyDescend);
        
        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }
    
    public static void clearAll() {
        flyKeyState.clear();
        forwardKeyState.clear();
        backwardKeyState.clear();
        leftKeyState.clear();
        rightKeyState.clear();
    }
    
    public static void removeFromAll(EntityPlayer player) {
        flyKeyState.remove(player);
        forwardKeyState.remove(player);
        backwardKeyState.remove(player);
        leftKeyState.remove(player);
        rightKeyState.remove(player);
    }
    
    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerLoggedOutEvent evt) {
        removeFromAll(evt.player);
    }
    
    @SubscribeEvent
    public void onDimChanged(PlayerChangedDimensionEvent evt) {
        removeFromAll(evt.player);
    }

}
