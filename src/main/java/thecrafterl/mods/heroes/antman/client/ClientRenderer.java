package thecrafterl.mods.heroes.antman.client;

import org.lwjgl.opengl.GL11;

import com.mojang.realmsclient.gui.ChatFormatting;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.entity.AntManPlayerData;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmor;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorChestplate;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorHelmet;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientRenderer {
	private final Minecraft mc;

	private EntityRenderer renderer, prevRenderer;
	private ResourceLocation textureAntMan;
	private ResourceLocation textureYellowjacket;
	
	public ClientRenderer() {
		this.mc = Minecraft.getMinecraft();
		textureAntMan = new ResourceLocation(AntMan.ASSETDIR + "textures/gui/hud.png");
		textureYellowjacket = new ResourceLocation(AntMan.ASSETDIR + "textures/gui/hudYellowjacket.png");
	}
	
	@SubscribeEvent
	public void renderEntity(RenderLivingEvent.Specials.Pre event) {
		if(AMConfig.hideName && event.entity instanceof EntityPlayer) {
			if(AntMan.isSmall((EntityPlayer) event.entity)) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		if (event.phase == Phase.START) {
			if (mc.thePlayer != null) {
				updateRenderer();
			}
		}
		
		if(event.phase == Phase.END)
			tickRendererHUD();
	}

	// Credits: coolAlias
	
	private void updateRenderer() {
		if (AntMan.isSmall(mc.thePlayer)) {
			if (renderer == null) {
				renderer = new EntityRendererAlt(mc);
			}
			if (mc.entityRenderer != renderer) {
				prevRenderer = mc.entityRenderer;
				mc.entityRenderer = renderer;
			}
		} else if (prevRenderer != null && mc.entityRenderer != prevRenderer) {
			mc.entityRenderer = prevRenderer;
		}
	}
	
	boolean hasShown = false;
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(!hasShown && !event.world.isRemote && !Loader.isModLoaded("RenderPlayerAPI") && event.entity instanceof EntityPlayer) {
			((EntityPlayer)event.entity).addChatMessage(new ChatComponentText("AntMan Mod requires RenderPlayerAPI!"));
			hasShown = true;
		}
	}
	
	private void tickRendererHUD() {
		if (mc.thePlayer != null) {
			if ((mc.currentScreen == null) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
				if(AntMan.hasArmorOn(mc.thePlayer) && AMConfig.showHUD) {
					
					GL11.glPushMatrix();
					GL11.glScalef(AMConfig.hudScale, AMConfig.hudScale, AMConfig.hudScale);
					ShrinkerTypes type = ((ItemAntManArmor)mc.thePlayer.getCurrentArmor(2).getItem()).getShrinkerType();
					switch (type) {
					case ANTMAN:
						mc.renderEngine.bindTexture(textureAntMan);
						break;
					case YELLOWJACKET:
						mc.renderEngine.bindTexture(textureYellowjacket);
						break;
					default:
						mc.renderEngine.bindTexture(textureAntMan);
						break;
					}
					
					GL11.glScaled(2.0D, 2.0D, 2.0D);
					
					if(AntMan.isSmall(mc.thePlayer))
						mc.ingameGUI.drawTexturedModalRect(5, 5, 0, 28 * 2, 14 * 2, 16 * 2);
					else
						mc.ingameGUI.drawTexturedModalRect(5, 5, 14 * 2, 28 * 2, 14 * 2, 16 * 2);
					
					ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
					
					GL11.glScaled(0.5D, 0.5D, 0.5D);
					
					int pp = PymParticleHandler.getPymParticles(chestplate);
					int maxPP = PymParticleHandler.getMaxPymParticles(chestplate);
					int percent = (100 * pp) / maxPP;
					
					mc.ingameGUI.drawTexturedModalRect(70, 25, 0, 0, 204, 16);
					mc.ingameGUI.drawTexturedModalRect(70 + 2, 25 + 2, 0, 14 * 2, percent * 2, 12);
					
					if(AntMan.isRFModActive()) {
						int energy = ((ItemAntManArmorChestplate)chestplate.getItem()).getEnergy(chestplate);
						int maxEnergy = ((ItemAntManArmorChestplate)chestplate.getItem()).maxEnergy;
						
						mc.ingameGUI.drawTexturedModalRect(70, 45, 0, 0, 204, 16);
						mc.ingameGUI.drawTexturedModalRect(70 + 2, 45 + 2, 0, 16, energy / 80, 12);
						
						mc.fontRenderer.drawStringWithShadow(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.energy") + ChatFormatting.DARK_GRAY + ": "
								+ ChatFormatting.AQUA + energy + ChatFormatting.DARK_GRAY + "/" + ChatFormatting.AQUA + maxEnergy + ChatFormatting.DARK_GRAY + " RF", 70, 63, 1);
						mc.fontRenderer.drawStringWithShadow(ChatFormatting.AQUA + Integer.valueOf(energy / 160).toString() + ChatFormatting.DARK_GRAY + "%", 245, 63, 1);
					}
					
					mc.fontRenderer.drawStringWithShadow(ChatFormatting.GRAY + StatCollector.translateToLocal("antman.info.pymparticles") + ChatFormatting.DARK_GRAY + ": "
							+ ShrinkerTypesHandlerClient.getChatColor(type) + pp + ChatFormatting.DARK_GRAY + "/" + ShrinkerTypesHandlerClient.getChatColor(type) + maxPP, 70, 15, 1);
					mc.fontRenderer.drawStringWithShadow(ShrinkerTypesHandlerClient.getChatColor(type) + Integer.valueOf(percent).toString() + ChatFormatting.DARK_GRAY + "%", 245, 15, 1);
					
//					mc.fontRenderer.drawStringWithShadow(Boolean.valueOf(AntManPlayerData.get(mc.thePlayer).isFast()).toString(), 25, 35, 1);
					
					GL11.glPopMatrix();
				}
			}
		}
	}
}
