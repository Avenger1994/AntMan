package thecrafterl.mods.heroes.antman.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelAntManHelmet - TheCrafterL
 * Created using Tabula 4.1.1
 */
public class ModelAntManHelmet extends ModelBiped {
	
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    
	public ModelAntManHelmet(float f) {
		super(f, 0, 64, 64);
		
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape7 = new ModelRenderer(this, 0, 32);
        this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7.addBox(4.2F, -4.3F, 0.0F, 2, 2, 4, -0.1F);
        this.setRotateAngle(shape7, 0.5918411493512771F, 0.0F, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 32);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-6.2F, -4.3F, 0.0F, 2, 2, 4, -0.1F);
        this.setRotateAngle(shape6, 0.5918411493512771F, 0.0F, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 45);
        this.shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9.addBox(-6.0F, -2.9F, 4.4F, 1, 1, 4, -0.2F);
        this.setRotateAngle(shape9, 0.8196066167365371F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 45);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(4.5F, -4.1F, -3.8F, 1, 1, 4, -0.1F);
        this.setRotateAngle(shape4, 0.5918411493512771F, 0.0F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 51);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-1.0F, -1.0F, -6.0F, 2, 2, 2, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 45);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(-5.5F, -4.1F, -3.8F, 1, 1, 4, -0.1F);
        this.setRotateAngle(shape5, 0.5918411493512771F, 0.0F, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 42);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(0.6F, -0.1F, -5.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(shape2, 0.0F, 0.0F, -0.27314402793711257F);
        this.shape3 = new ModelRenderer(this, 0, 42);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-5.4F, -0.1F, -5.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(shape3, 0.0F, 0.0F, 0.27314402793711257F);
        this.shape8 = new ModelRenderer(this, 0, 45);
        this.shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8.addBox(5.0F, -2.9F, 4.4F, 1, 1, 4, -0.2F);
        this.setRotateAngle(shape8, 0.8196066167365371F, 0.0F, 0.0F);
        
//        if(closed) {
//        	this.bipedHead.setTextureOffset(0, 0);
//        	this.bipedHeadwear.setTextureOffset(32, 0);
//        } else {
//        	this.bipedHead.setTextureOffset(0, 16);
//        	this.bipedHeadwear.setTextureOffset(32, 16);
//        }
        
        this.bipedHead.addChild(shape1);
        this.bipedHead.addChild(shape2);
        this.bipedHead.addChild(shape3);
        this.bipedHead.addChild(shape4);
        this.bipedHead.addChild(shape5);
        this.bipedHead.addChild(shape6);
        this.bipedHead.addChild(shape7);
        this.bipedHead.addChild(shape8);
        this.bipedHead.addChild(shape9);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.render(entity, f, f1, f2, f3, f4, f5);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
	}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
