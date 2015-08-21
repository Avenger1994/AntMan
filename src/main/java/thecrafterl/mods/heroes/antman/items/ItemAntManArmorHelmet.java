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
import thecrafterl.mods.heroes.antman.client.models.ModelAntManHelmet;
import thecrafterl.mods.heroes.antman.items.AMItems.ShrinkerTypes;
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
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
//		list.add("" + stack.stackTagCompound.getBoolean("helmetOpen"));
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
			armorModel = new ModelAntManHelmet(0.5F);
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
	
}
