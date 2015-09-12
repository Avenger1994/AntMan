package thecrafterl.mods.heroes.antman.entity;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityAnt extends EntityTameable {

	public boolean canRide;
	
	public EntityAnt(World world, boolean canRide) {
		super(world);
		this.setSize(0.1F, 0.08F);
		this.canRide = canRide;
		this.tasks.addTask(0, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(1, new EntityAIPanic(this, 0.9D));
		this.tasks.addTask(2, new EntityAITempt(this, 0.9D, Items.sugar, true));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		if(canRide)
			this.tasks.addTask(4, new EntityAIRideAnt(this, 0.9D));
		this.setTamed(false);
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		if (super.interact(player)) {
			return true;
		} else if(!isTamed() && AntMan.hasArmorOn(player, ShrinkerTypes.MCU_ANTMAN) && AntMan.isSmall(player)) {
			setTamed(true);
			this.func_152115_b(player.getUniqueID().toString());
			return true;
		} else if(this.func_152114_e(player) && !this.worldObj.isRemote && player.isSneaking()) {
			this.setSitting(!isSitting());
			return true;
		} else if(canRide && this.func_152114_e(player) && !this.worldObj.isRemote && AntMan.hasArmorOn(player, ShrinkerTypes.MCU_ANTMAN) && AntMan.isSmall(player)) {
			setRidingPlayer(player);
			return true;
		}
		return false;
	}
	
	public double getMountedYOffset() {
		return 0.45D;
	}
	
	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
	}
	
	public EntityPlayer getRidingPlayer() {
		if (riddenByEntity instanceof EntityPlayer) {
			return (EntityPlayer) riddenByEntity;
		} else {
			return null;
		}
	}

	public void setRidingPlayer(EntityPlayer player) {
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;
		player.mountEntity(this);
	}
	
}
