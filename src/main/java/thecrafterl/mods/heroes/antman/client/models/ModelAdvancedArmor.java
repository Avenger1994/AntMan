package thecrafterl.mods.heroes.antman.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAdvancedArmor extends ModelBiped {
	public ModelRenderer bipedBodyWear;

	public ModelRenderer bipedRightArmWear;

	public ModelRenderer bipedLeftArmWear;

	public ModelRenderer bipedRightLegWear;

	public ModelRenderer bipedLeftLegWear;

	public ModelAdvancedArmor(float f, int width, int height) {
		super(f, 0, width, height);

		this.textureWidth = width;
		this.textureHeight = height;
		
		float scale = f + 0.5F;
		
		this.bipedBodyWear = new ModelRenderer(this, 16, 32);
		this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
		this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.bipedRightArmWear = new ModelRenderer(this, 40, 32);
		this.bipedRightArmWear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.bipedRightArmWear.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.bipedLeftArmWear = new ModelRenderer(this, 48, 48);
//		this.bipedLeftArmWear.mirror = true;
		this.bipedLeftArmWear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
		this.bipedLeftArmWear.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.bipedRightLegWear = new ModelRenderer(this, 0, 32);
		this.bipedRightLegWear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedRightLegWear.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.bipedLeftLegWear = new ModelRenderer(this, 0, 48);
//		this.bipedLeftLegWear.mirror = true;
		this.bipedLeftLegWear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		this.bipedLeftLegWear.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		this.bipedRightArm.setTextureOffset(40, 16);
		this.bipedLeftArm.setTextureOffset(32, 48);
		this.bipedRightLeg.setTextureOffset(0, 16);
		this.bipedLeftLeg.setTextureOffset(16, 48);
		
		this.bipedBody.addChild(bipedBodyWear);
		this.bipedRightArm.addChild(bipedRightArmWear);
		this.bipedLeftArm.addChild(bipedLeftArmWear);
		this.bipedRightLeg.addChild(bipedRightLegWear);
		this.bipedLeftLeg.addChild(bipedLeftLegWear);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}