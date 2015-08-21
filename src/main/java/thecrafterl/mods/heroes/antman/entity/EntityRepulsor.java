package thecrafterl.mods.heroes.antman.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityRepulsor extends EntityThrowable {

	public EntityRepulsor(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityRepulsor(World world) {
		super(world);
	}

	public EntityRepulsor(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {

		if (mop.entityHit != null) {
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4);
		}

		if(!this.worldObj.isRemote) {
			this.worldObj.newExplosion((Entity) null, this.posX, this.posY, this.posZ, (float) 1, false, false);
		}
		
		this.worldObj.spawnParticle("flame", posX, posY, posZ, 0.0D, 0.0D, 0.0D);;

		this.setDead();

	}

	@Override
	protected float getGravityVelocity() {
		return 0.001F;
	}

	@Override
	protected float func_70182_d() {
		return 5F;
	}

}
