package thecrafterl.mods.heroes.antman.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorHelmet;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.network.MessageFlyKeys;
import thecrafterl.mods.heroes.antman.network.MessageToggleHelmet;
import thecrafterl.mods.heroes.antman.network.MessageToggleSize;
import thecrafterl.mods.heroes.antman.network.MessageToggleSpeed;
import thecrafterl.mods.heroes.antman.network.MessageYJShoot;
import thecrafterl.mods.heroes.antman.network.SyncTracker;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ClientKeyHandler {

	private static Minecraft mc;
    private static final KeyBinding keyToggleSize = new KeyBinding(StatCollector.translateToLocal("antman.keybinding.changesize"), Keyboard.KEY_F, "Ant-Man");
    private static final KeyBinding keyToggleHelmet = new KeyBinding(StatCollector.translateToLocal("antman.keybinding.togglehelmet"), Keyboard.KEY_C, "Ant-Man");
    private static final KeyBinding keyToggleHUD = new KeyBinding(StatCollector.translateToLocal("antman.keybinding.togglehud"), Keyboard.KEY_U, "Ant-Man");
    private static final KeyBinding keyToggleSpeed = new KeyBinding(StatCollector.translateToLocal("antman.keybinding.togglespeed"), Keyboard.KEY_N, "Ant-Man");
    private static final KeyBinding keyShootRepulsor = new KeyBinding(StatCollector.translateToLocal("antman.keybinding.shootrepulsor"), Keyboard.KEY_V, "Ant-Man");

    
    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
 
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;
    
    public ClientKeyHandler() {
        this.mc = Minecraft.getMinecraft();
		ClientRegistry.registerKeyBinding(keyToggleSize);
		ClientRegistry.registerKeyBinding(keyToggleHelmet);
		ClientRegistry.registerKeyBinding(keyToggleHUD);
		ClientRegistry.registerKeyBinding(keyToggleSpeed);
		ClientRegistry.registerKeyBinding(keyShootRepulsor);
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        if (evt.phase == Phase.START)
            tickStart();
    }
    
    private static void tickStart() {
        if (mc.thePlayer != null) {
            boolean flyState;
            boolean descendState;
            flyState = mc.gameSettings.keyBindJump.getIsKeyPressed();
            descendState = mc.gameSettings.keyBindSneak.getIsKeyPressed();
            
            boolean forwardState = mc.gameSettings.keyBindForward.getIsKeyPressed();
            boolean backwardState = mc.gameSettings.keyBindBack.getIsKeyPressed();
            boolean leftState = mc.gameSettings.keyBindLeft.getIsKeyPressed();
            boolean rightState = mc.gameSettings.keyBindRight.getIsKeyPressed();
            
            if (flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastDescendState = descendState;
                
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                AntMan.network.sendToServer(new MessageFlyKeys(flyState, descendState, forwardState, backwardState, leftState, rightState));
                SyncTracker.processKeyUpdate(mc.thePlayer, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
        }
    }
    
	@SubscribeEvent
	public void onKey(KeyInputEvent evt) {
		boolean toggle = keyToggleSize.isPressed();
		boolean helmet = keyToggleHelmet.isPressed();
		boolean hud = keyToggleHUD.isPressed();
		boolean speed = keyToggleSpeed.isPressed();
		boolean repulsor = keyShootRepulsor.isPressed();
		
		if (toggle || helmet || hud || speed || repulsor) {
			if (mc.inGameHasFocus) {
					if(toggle && AntMan.hasArmorOn(mc.thePlayer))
						AntMan.network.sendToServer(new MessageToggleSize());
					if(helmet && AntMan.hasHelmetOn(mc.thePlayer))
						AntMan.network.sendToServer(new MessageToggleHelmet());
					if(hud && AntMan.hasHelmetOn(mc.thePlayer))
						AMConfig.showHUD = !AMConfig.showHUD;
					if(speed && AntMan.isSmall(mc.thePlayer))
						AntMan.network.sendToServer(new MessageToggleSpeed());
					if(repulsor && AntMan.hasArmorOn(mc.thePlayer, ShrinkerTypes.YELLOWJACKET))
						AntMan.network.sendToServer(new MessageYJShoot());
			}
		}
	}
    
}
