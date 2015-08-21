package thecrafterl.mods.heroes.antman.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class EntityAIRideAnt extends EntityAIBase {
	
	private static final float PLAYER_SPEED = 0.98f;
	private final double speed;
	protected final EntityAnt ant;
	protected EntityPlayer rider;

	public EntityAIRideAnt(EntityAnt ant, double speed) {
		this.ant = ant;
		this.speed = speed;
	}

	public static boolean hasEquipped(EntityPlayer player, Item item) {
		if (player == null)
			return false;

		ItemStack itemStack = player.getCurrentEquippedItem();

		if (itemStack == null) {
			return false;
		}

		return itemStack.getItem() == item;
	}

	public boolean shouldExecute() {
		this.rider = ant.getRidingPlayer();
		return rider != null;

	}

	@Override
	public void startExecuting() {
		ant.getNavigator().clearPathEntity();
	}

	@Override
	public void resetTask() {
	}

	@Override
	public void updateTask() {
		super.updateTask();
		if (rider != null) {
			float speedX = rider.moveForward / PLAYER_SPEED;
			float speedY = rider.moveStrafing / PLAYER_SPEED;

			float speedPlayer = Math.max(Math.abs(speedX), Math.abs(speedY));
			Vec3 look = rider.getLookVec();
			float dir = Math.min(speedX, 0) * -1;
			dir += speedY / (speedX * 2 + (speedX < 0 ? -2 : 2));
			if (dir != 0) {
				look.rotateAroundY((float) Math.PI * dir);
			}

			if (speedPlayer > 0) {
				ant.getMoveHelper().setMoveTo(ant.posX + look.xCoord, ant.posY, ant.posZ + look.zCoord, speed * speedPlayer);

				if (!ant.shouldDismountInWater(rider) && ant.isInWater()) {
					if (Math.abs(look.yCoord) > 0.4) {
						ant.motionY = Math.max(-0.15, Math.min(0.15, look.yCoord));
					}
				}
			}

		}
	}
}