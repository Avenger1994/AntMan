package thecrafterl.mods.heroes.antman.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.AntManHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.ShrinkerTypesHandlerClient;
import thecrafterl.mods.heroes.antman.client.models.ModelAdvancedArmor;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAntManArmor extends ItemArmor {

	private ShrinkerTypes type;
	
	public ItemAntManArmor(String name, int i, ShrinkerTypes type) {
		super(ArmorMaterial.IRON, 0, i);
		
		this.setUnlocalizedName(AntMan.MODID.toLowerCase() + "." + name);
		this.setTextureName(AntMan.ASSETDIR + name);
		GameRegistry.registerItem(this, name);
		
		this.setCreativeTab(AntMan.tabAntMan);
		
		this.type = type;
		
		AMItems.items.add(this);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(slot == 2) return AntMan.ASSETDIR + "textures/models/armor/" + this.type.toString().toLowerCase() + "/armor_2.png";
		else return AntMan.ASSETDIR + "textures/models/armor/" + this.type.toString().toLowerCase() + "/armor_1.png";
	}
	
	public ShrinkerTypes getShrinkerType() {
		return type;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		ModelBiped armorModel = null;
		if (itemStack != null) {
			armorModel = ShrinkerTypesHandlerClient.getModel(getShrinkerType());
			if (armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();

				ItemStack heldItem = entityLiving.getEquipmentInSlot(0);

				if (heldItem != null)
					armorModel.heldItemRight = 1;

				if (entityLiving instanceof EntityPlayer) {

					EntityPlayer player = (EntityPlayer) entityLiving;

					if (player.getItemInUseCount() > 0) {
						if (heldItem.getItemUseAction() == EnumAction.bow) {
							armorModel.aimedBow = true;
						} else if (heldItem.getItemUseAction() == EnumAction.block) {
							armorModel.heldItemRight = 3;
						}
					}
				}
				return armorModel;
			}
		}
		return null;
	}
	
}
