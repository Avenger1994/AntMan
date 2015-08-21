package thecrafterl.mods.heroes.antman.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYellowjacketChestplate extends ModelAdvancedArmor {

	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape8;
	public ModelRenderer shape9;
	public ModelRenderer shape10;
	public ModelRenderer shape11;
	public ModelRenderer shape12;
	public ModelRenderer shape13;
	public ModelRenderer shape14;

	public ModelYellowjacketChestplate(float f, int width, int height) {
		super(f, width, height);
		
		this.textureWidth = width;
		this.textureHeight = height;
		
		this.shape5 = new ModelRenderer(this, 64, 12);
		this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5.addBox(1.0F, -3.0F, 7.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape5, -0.8196066167365371F, 0.5462880558742251F, 0.0F);
		this.shape14 = new ModelRenderer(this, 100, 12);
		this.shape14.mirror = true;
		this.shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape14.addBox(17.1F, -11.9F, -5.6F, 2, 5, 2, -0.4F);
		this.setRotateAngle(shape14, -1.3658946726107624F, -2.9595548126067843F, 0.0F);
		this.shape3 = new ModelRenderer(this, 64, 0);
		this.shape3.mirror = true;
		this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape3.addBox(-4.0F, -8.0F, 2.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape3, -0.8196066167365371F, -0.5462880558742251F, 0.0F);
		this.shape6 = new ModelRenderer(this, 73, 12);
		this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape6.addBox(-5.0F, -10.4F, 6.6F, 2, 9, 2, -0.4F);
		this.setRotateAngle(shape6, -0.8196066167365371F, 1.3203415791337103F, 0.0F);
		this.shape10 = new ModelRenderer(this, 64, 12);
		this.shape10.mirror = true;
		this.shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape10.addBox(-3.0F, -3.0F, 7.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape10, -0.8196066167365371F, -0.5462880558742251F, 0.0F);
		this.shape12 = new ModelRenderer(this, 82, 12);
		this.shape12.mirror = true;
		this.shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape12.addBox(8.7F, -16.0F, 2.4F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape12, -1.0927506446736497F, -1.8212510744560826F, 0.0F);
		this.shape13 = new ModelRenderer(this, 91, 12);
		this.shape13.mirror = true;
		this.shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape13.addBox(17.1F, -7.3F, -5.6F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape13, -1.3658946726107624F, -2.9595548126067843F, 0.0F);
		this.shape8 = new ModelRenderer(this, 91, 12);
		this.shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape8.addBox(-18.9F, -7.3F, -5.6F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape8, -1.3658946726107624F, 2.9595548126067843F, 0.0F);
		this.shape1 = new ModelRenderer(this, 64, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(2.0F, -8.0F, 2.0F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape1, -0.8196066167365371F, 0.5462880558742251F, 0.0F);
		this.shape7 = new ModelRenderer(this, 82, 12);
		this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape7.addBox(-10.3F, -16.0F, 2.4F, 2, 9, 2, 0.0F);
		this.setRotateAngle(shape7, -1.0927506446736497F, 1.8212510744560826F, 0.0F);
		this.shape4 = new ModelRenderer(this, 73, 0);
		this.shape4.mirror = true;
		this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape4.addBox(-4.0F, -15.0F, 2.0F, 2, 9, 2, -0.5F);
		this.setRotateAngle(shape4, -0.8196066167365371F, -0.5462880558742251F, 0.0F);
		this.shape2 = new ModelRenderer(this, 73, 0);
		this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2.addBox(2.0F, -15.0F, 2.0F, 2, 9, 2, -0.5F);
		this.setRotateAngle(shape2, -0.8196066167365371F, 0.5462880558742251F, 0.0F);
		this.shape9 = new ModelRenderer(this, 100, 12);
		this.shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape9.addBox(-18.9F, -11.9F, -5.6F, 2, 5, 2, -0.4F);
		this.setRotateAngle(shape9, -1.3658946726107624F, 2.9595548126067843F, 0.0F);
		this.shape11 = new ModelRenderer(this, 73, 12);
		this.shape11.mirror = true;
		this.shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape11.addBox(3.0F, -10.4F, 6.6F, 2, 9, 2, -0.4F);
		this.setRotateAngle(shape11, -0.8196066167365371F, -1.3203415791337103F, 0.0F);
		
		this.bipedBody.addChild(shape1);
		this.bipedBody.addChild(shape2);
		this.bipedBody.addChild(shape3);
		this.bipedBody.addChild(shape4);
		this.bipedBody.addChild(shape5);
		this.bipedBody.addChild(shape6);
		this.bipedBody.addChild(shape7);
		this.bipedBody.addChild(shape8);
		this.bipedBody.addChild(shape9);
		this.bipedBody.addChild(shape10);
		this.bipedBody.addChild(shape11);
		this.bipedBody.addChild(shape12);
		this.bipedBody.addChild(shape13);
		this.bipedBody.addChild(shape14);
		
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
