package thecrafterl.mods.heroes.antman.client;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.entity.AntManSizes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityRendererAlt extends EntityRenderer
{
	private final Minecraft mc;

	public EntityRendererAlt(Minecraft mc) {
		super(mc, mc.getResourceManager());
		this.mc = mc;
	}

	@Override
	public void updateCameraAndRender(float partialTick) {
		
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()) {
			super.updateCameraAndRender(partialTick);
			return;
		}
		
		AntManSizes size = AntMan.getSize(mc.thePlayer);
		float ySize = -(1.62F - (1.62F * size.getMultiplier()));
		
		mc.thePlayer.yOffset -= ySize;
		mc.thePlayer.eyeHeight = -0.7F;
		super.updateCameraAndRender(partialTick);
		mc.thePlayer.yOffset = 1.62F;
		mc.thePlayer.eyeHeight = mc.thePlayer.getDefaultEyeHeight();
	}
	
	@Override
	public void getMouseOver(float partialTick) {
		
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()) {
			super.getMouseOver(partialTick);
			return;
		}
		
//		AntManSizes size = AntMan.getSize(mc.thePlayer);
		
//		float ySize = 1.62F - (1.62F * size.getMultiplier());
		float offsetY = 0.65000004F;
//		
		mc.thePlayer.posY -= offsetY;
		mc.thePlayer.prevPosY -= offsetY;
		mc.thePlayer.lastTickPosY -= offsetY;
		mc.thePlayer.eyeHeight = -0.7F;
		super.getMouseOver(partialTick);
		mc.thePlayer.posY += offsetY;
		mc.thePlayer.prevPosY += offsetY;
		mc.thePlayer.lastTickPosY += offsetY;
	}
}