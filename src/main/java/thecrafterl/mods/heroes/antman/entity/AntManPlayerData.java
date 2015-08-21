package thecrafterl.mods.heroes.antman.entity;

import thecrafterl.mods.heroes.antman.AMConfig;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.items.ItemAntManArmorChestplate;
import tv.twitch.chat.Chat;
import net.minecraft.entity.AntManHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class AntManPlayerData implements IExtendedEntityProperties {

	public final static String EXT_PROP_NAME = "AntMan";
	
	private final EntityPlayer player;
	
	private boolean fast;
	
	public static final int SIZE_WATCHER = AMConfig.sizeDataWatcherId;
	
	int damageTimer = 0;
	
	public AntManPlayerData(EntityPlayer player) {
		this.player = player;
		this.fast = false;
		this.player.getDataWatcher().addObject(SIZE_WATCHER, AntManSizes.NORMAL.ordinal());
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(AntManPlayerData.EXT_PROP_NAME, new AntManPlayerData(player));
	}
	
	public static final AntManPlayerData get(EntityPlayer player) {
		return (AntManPlayerData) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	public void onUpdate() {
		updateSize();
		
		if(!isNormal()) {
			
			if(AMConfig.strengthWhenSmall)
				player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20, 2));
			if(AMConfig.slowWhenSmall && !fast)
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 2));
			ItemStack chestplate = player.getCurrentArmor(2);
			
			if((!AntMan.hasArmorOn(player) || !((ItemAntManArmorChestplate)chestplate.getItem()).hasEnoughEnergy(chestplate)) && AMConfig.damageWhenSmallWithoutArmor) {
				if(damageTimer == 15)
					damageTimer = 0;
				else {
					player.attackEntityFrom(AntMan.dmgSuffocate, 5.0F);
					damageTimer++;
				}
			} else {
				((ItemAntManArmorChestplate)chestplate.getItem()).removeEnergy(chestplate);
			}
		}
		
	}
	
	public void onWorldJoin() {
		updateSize();
	}
	
	public void updateSize() {
		AntManHelper.setSize(player, getSize());
	}
	
	public void setSize(AntManSizes size) {
		player.getDataWatcher().updateObject(SIZE_WATCHER, size.ordinal());
	}
	
	public boolean isSmall() {
		return getSize() == AntManSizes.SMALL;
	}
	
	public boolean isNormal() {
		return getSize() == AntManSizes.NORMAL;
	}
	
	public boolean isGiant() {
		return getSize() == AntManSizes.GIANT;
	}
	
	public AntManSizes getSize() {
		return AntManSizes.getSizeFromOrdinal(player.getDataWatcher().getWatchableObjectInt(SIZE_WATCHER));
	}
	
	public void setFast(boolean b) {
		fast = b;
	}
	
	public boolean isFast() {
		return fast;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setBoolean("fast", fast);
		properties.setInteger("size", player.getDataWatcher().getWatchableObjectInt(SIZE_WATCHER));
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		fast = properties.getBoolean("fast");
		player.getDataWatcher().updateObject(SIZE_WATCHER, properties.getInteger("size"));
	}

	@Override
	public void init(Entity entity, World world) {
		
	}


}