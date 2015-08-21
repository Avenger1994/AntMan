package thecrafterl.mods.heroes.antman.client;

import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.models.ModelAdvancedArmor;
import thecrafterl.mods.heroes.antman.items.AMItems;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({ @Optional.Interface(iface = "api.player.render.RenderPlayerAPI", modid = "RenderPlayerAPI", striprefs = true), @Optional.Interface(iface = "api.player.render.RenderPlayerBase", modid = "RenderPlayerAPI", striprefs = true) })
public class AntManPlayerRenderer extends RenderPlayerBase {

	public AntManPlayerRenderer(RenderPlayerAPI arg0) {
		super(arg0);
	}
	
	@Override
	public void renderPlayerScale(AbstractClientPlayer player, float arg1) {
		if (AntMan.isSmall(player)) {
			GL11.glDisable(GL11.GL_LIGHTING);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			GL11.glTranslatef(0F, -1.458F, 0F);
			GL11.glScalef(0.1F, 0.1F, 0.1F);
			if (player.isSneaking())
				GL11.glTranslatef(0F, -0.7F, 0F);
			if(player != Minecraft.getMinecraft().thePlayer)
				GL11.glTranslatef(0F, 14.5F, 0F);
			GL11.glEnable(GL11.GL_LIGHTING);
		} else {
			super.renderPlayerScale(player, arg1);
		}
	}
	
	@Override
	public void renderFirstPersonArm(EntityPlayer player) {
		ItemStack armor = player.getCurrentArmor(2);

		if (armor != null && armor.getItem() instanceof ItemAntManArmor && AMConfig.showFirstPersonArmor) {

			ModelBiped player_model = new ModelBiped();
			ModelBiped armor_model = ShrinkerTypesHandlerClient.getModel(((ItemAntManArmor)armor.getItem()).getShrinkerType());
			ResourceLocation skin = null;
			
            Minecraft minecraft = Minecraft.getMinecraft();
            Map map = minecraft.func_152342_ad().func_152788_a(player.getGameProfile());

            if (map.containsKey(Type.SKIN))
            {
            	skin = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
            }
			
            if(skin != null)
            	Minecraft.getMinecraft().renderEngine.bindTexture(skin);
			
			player_model.onGround = 0.0F;
			player_model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
			player_model.bipedRightArm.showModel = true;
			player_model.bipedRightArm.rotateAngleY = (float) Math.PI / 2F;
			player_model.bipedRightArm.render(0.0625F);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(armor.getItem().getArmorTexture(armor, player, 1, null)));
			
			armor_model.onGround = 0.0F;
			armor_model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
			armor_model.bipedRightArm.showModel = true;
			armor_model.bipedRightArm.rotateAngleY = (float) Math.PI / 2F;
			armor_model.bipedRightArm.render(0.0625F);
			
		} else {
			super.renderFirstPersonArm(player);
		}
	}
	
//	@Override
//	public void renderLivingLabel(Entity entity, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
//		
//		if(entity instanceof EntityPlayer) {
//			if(AntMan.hasArmorOn((EntityPlayer) entity) && AntMan.isSmall(((EntityPlayer) entity).getCurrentArmor(3))) {
//				GL11.glScalef(0.1F, 0.1F, 0.1F);
//			} else
//				super.renderLivingLabel(entity, paramString, paramDouble1, paramDouble2, paramDouble3, paramInt);
//		} else {
//			super.renderLivingLabel(entity, paramString, paramDouble1, paramDouble2, paramDouble3, paramInt);
//		}
//	}

}
