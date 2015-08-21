package net.minecraft.entity;

import thecrafterl.mods.heroes.antman.entity.AntManSizes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;

public class AntManHelper {

	public static final float defaultWidth = 0.6F;
	public static final float defaultHeight = 1.8F;
	public static final float eyeHeightMultiplier = 0.85F;
	
	public static void setSize(EntityLivingBase entity, AntManSizes size) {
		entity.setSize(defaultWidth * size.getMultiplier(), defaultHeight * size.getMultiplier());
		entity.getEntityData().setFloat("smallWidth", entity.width);
		entity.getEntityData().setFloat("smallHeight", entity.height);
		
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if(size != AntManSizes.NORMAL)
				player.eyeHeight = player.height * eyeHeightMultiplier;
			else {
				player.eyeHeight = player.getDefaultEyeHeight();
			}
		}
	}
	
//	public static void setAntSize(Entity entity) {
//		entity.setSize(0.6F * 0.1F, 1.8F * 0.1F);
//		if (entity instanceof EntityPlayer) {
//			((EntityPlayer) entity).eyeHeight = 0.85F * entity.height;
//		}
//	}
//	
//	public static void resetSize(Entity entity) {
//		entity.setSize(0.6F, 1.8F);
//		if (entity instanceof EntityPlayer) {
//			((EntityPlayer) entity).eyeHeight = ((EntityPlayer) entity).getDefaultEyeHeight();
//		}
//	}
}