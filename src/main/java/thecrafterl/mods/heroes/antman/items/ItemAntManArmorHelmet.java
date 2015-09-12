package thecrafterl.mods.heroes.antman.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.ShrinkerTypesHandlerClient;
import thecrafterl.mods.heroes.antman.client.models.ModelAntManHelmet;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import thecrafterl.mods.heroes.antman.network.SyncTracker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAntManArmorHelmet extends ItemAntManArmor {
	
	public ItemAntManArmorHelmet(String name, int i, ShrinkerTypes type) {
		super(name, i, type);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(AntMan.isClosed(stack))
			return AntMan.ASSETDIR + "textures/models/armor/" + this.getShrinkerType().toString().toLowerCase() + "/helmet.png";
		else
			return AntMan.ASSETDIR + "textures/models/armor/" + this.getShrinkerType().toString().toLowerCase() + "/helmet_open.png";
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		AntMan.setDefaultHelmetTags(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		ModelBiped armorModel = null;
		if (stack != null) {
			armorModel = ShrinkerTypesHandlerClient.getHelmetModel(getShrinkerType());
			if (armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.isSneak = entityLiving.isSneaking();
				return armorModel;
			}
		}
		return null;
	}
	
    @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    	if(this.getShrinkerType().canFly())
    		useJetpack(player, stack, false);
	}

	private void useJetpack(EntityLivingBase user, ItemStack armor, boolean force) {
		boolean flyKeyDown = force || SyncTracker.isFlyKeyDown(user);
		boolean descendKeyDown = SyncTracker.isDescendKeyDown(user);
		float speedSideways = (float) (user.isSneaking() ? 0.08F * 0.5F : 0.08F);
		double currentAccel = user.motionY < 0.3D ? 0.12F * 2.5 : 0.12F;

		EntityPlayer player = (EntityPlayer) user;

		if (flyKeyDown) {
			
			ItemAntManArmorChestplate chestplate = (ItemAntManArmorChestplate) player.getCurrentArmor(2).getItem();
			
			if (AntMan.hasArmorOn(player) && AntMan.isSmall(player) && chestplate.hasEnoughEnergy(player.getCurrentArmor(2), 3)) {

				chestplate.removeEnergy(player.getCurrentArmor(2), 3);
				user.motionY = Math.min(user.motionY + currentAccel, 0.3F);

				if (SyncTracker.isForwardKeyDown(user)) {
					user.moveFlying(0, speedSideways, speedSideways);
				}
				if (SyncTracker.isBackwardKeyDown(user)) {
					user.moveFlying(0, -speedSideways, speedSideways * 0.8F);
				}
				if (SyncTracker.isLeftKeyDown(user)) {
					user.moveFlying(speedSideways, 0, speedSideways);
				}
				if (SyncTracker.isRightKeyDown(user)) {
					user.moveFlying(-speedSideways, 0, speedSideways);
				}

				if (!user.worldObj.isRemote) {
					user.fallDistance = 0.0F;
				}

			}
		}

	}
	
}
