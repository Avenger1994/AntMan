package thecrafterl.mods.heroes.antman.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYellowjacketHelmet extends ModelBiped {

    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;

    public ModelYellowjacketHelmet(float f) {
    	super(f, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.shape4 = new ModelRenderer(this, 0, 39);
        this.shape4.mirror = true;
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-4.6F, -0.7F, -4.9F, 1, 1, 7, 0.0F);
        this.setRotateAngle(shape4, 0.0F, 0.05235987755982988F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 32);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-1.0F, -2.0F, -5.5F, 2, 2, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 32);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 39);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(3.6F, -0.7F, -4.9F, 1, 1, 7, 0.0F);
        this.setRotateAngle(shape3, 0.0F, -0.05235987755982988F, 0.0F);
        
        this.bipedHead.addChild(shape1);
        this.bipedHead.addChild(shape2);
        this.bipedHead.addChild(shape3);
        this.bipedHead.addChild(shape4);
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

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
