package thecrafterl.mods.heroes.antman.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCrazyAnt extends ModelBase {
	
    public ModelRenderer gaster;
    public ModelRenderer node;
    public ModelRenderer thorax;
    public ModelRenderer node2;
    public ModelRenderer head;
    public ModelRenderer leg1Top;
    public ModelRenderer leg1Bottom;
    public ModelRenderer leg2Top;
    public ModelRenderer leg2Bottom;
    public ModelRenderer leg3Top;
    public ModelRenderer leg3Bottom;
    public ModelRenderer leg4Top;
    public ModelRenderer leg4Bottom;
    public ModelRenderer leg5Top;
    public ModelRenderer leg5Bottom;
    public ModelRenderer leg6Top;
    public ModelRenderer leg6Bottom;
    public ModelRenderer shape21;
    public ModelRenderer shape22;
    public ModelRenderer shape23;
    public ModelRenderer shape24;

    public ModelCrazyAnt() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg1Bottom = new ModelRenderer(this, 44, 24);
        this.leg1Bottom.setRotationPoint(3.0F, 21.0F, -1.6F);
        this.leg1Bottom.addBox(0.6F, -0.8F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg1Bottom, 0.0F, 0.0F, -0.855036800552022F);
        this.leg5Top = new ModelRenderer(this, 44, 19);
        this.leg5Top.setRotationPoint(-1.7F, 22.0F, 0.3F);
        this.leg5Top.addBox(0.1F, -2.8F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg5Top, 0.0F, 0.0F, -0.855036800552022F);
        this.leg6Top = new ModelRenderer(this, 44, 19);
        this.leg6Top.setRotationPoint(-1.6F, 22.0F, 1.9F);
        this.leg6Top.addBox(0.1F, -2.8F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg6Top, 0.0F, 0.0F, -0.855036800552022F);
        this.thorax = new ModelRenderer(this, 0, 21);
        this.thorax.setRotationPoint(0.0F, 22.0F, 1.0F);
        this.thorax.addBox(-2.0F, -1.0F, -3.0F, 4, 2, 3, 0.0F);
        this.leg5Bottom = new ModelRenderer(this, 44, 24);
        this.leg5Bottom.setRotationPoint(-2.7F, 21.0F, 0.3F);
        this.leg5Bottom.addBox(-1.6F, -0.7F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg5Bottom, 0.0F, 0.0F, 0.855036800552022F);
        this.shape23 = new ModelRenderer(this, 5, 0);
        this.shape23.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.shape23.addBox(1.0F, -5.2F, -3.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape23, 0.22759093446006054F, 0.0F, 0.0F);
        this.shape24 = new ModelRenderer(this, 5, 0);
        this.shape24.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.shape24.addBox(-2.0F, -5.2F, -3.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape24, 0.22759093446006054F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 26);
        this.head.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.head.addBox(-2.0F, -2.5F, -4.0F, 4, 3, 3, 0.0F);
        this.leg6Bottom = new ModelRenderer(this, 44, 24);
        this.leg6Bottom.setRotationPoint(-2.6F, 21.0F, 1.9F);
        this.leg6Bottom.addBox(-1.6F, -0.7F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg6Bottom, 0.0F, 0.0F, 0.855036800552022F);
        this.node = new ModelRenderer(this, 0, 18);
        this.node.setRotationPoint(0.0F, 21.0F, 3.0F);
        this.node.addBox(-1.0F, 0.0F, -2.0F, 2, 1, 2, 0.0F);
        this.shape21 = new ModelRenderer(this, 0, 0);
        this.shape21.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.shape21.addBox(1.0F, -0.6F, -5.0F, 1, 1, 1, 0.0F);
        this.leg3Bottom = new ModelRenderer(this, 44, 24);
        this.leg3Bottom.setRotationPoint(2.4F, 21.0F, 1.9F);
        this.leg3Bottom.addBox(0.6F, -0.8F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg3Bottom, 0.0F, 0.0F, -0.855036800552022F);
        this.node2 = new ModelRenderer(this, 9, 18);
        this.node2.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.node2.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
        this.leg4Top = new ModelRenderer(this, 44, 19);
        this.leg4Top.setRotationPoint(-2.0F, 22.0F, -1.6F);
        this.leg4Top.addBox(0.1F, -2.8F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg4Top, 0.0F, 0.0F, -0.855036800552022F);
        this.leg2Top = new ModelRenderer(this, 44, 19);
        this.leg2Top.setRotationPoint(1.3F, 22.0F, 0.3F);
        this.leg2Top.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg2Top, 0.0F, 0.0F, 0.855036800552022F);
        this.leg1Top = new ModelRenderer(this, 44, 19);
        this.leg1Top.setRotationPoint(2.0F, 22.0F, -1.6F);
        this.leg1Top.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg1Top, 0.0F, 0.0F, 0.855036800552022F);
        this.leg3Top = new ModelRenderer(this, 44, 19);
        this.leg3Top.setRotationPoint(1.3F, 22.0F, 1.9F);
        this.leg3Top.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leg3Top, 0.0F, 0.0F, 0.855036800552022F);
        this.gaster = new ModelRenderer(this, 0, 10);
        this.gaster.setRotationPoint(0.0F, 21.0F, 3.0F);
        this.gaster.addBox(-2.0F, -1.0F, 0.0F, 4, 3, 5, 0.0F);
        this.shape22 = new ModelRenderer(this, 0, 0);
        this.shape22.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.shape22.addBox(-2.0F, -0.6F, -5.0F, 1, 1, 1, 0.0F);
        this.leg2Bottom = new ModelRenderer(this, 44, 24);
        this.leg2Bottom.setRotationPoint(2.4F, 21.0F, 0.3F);
        this.leg2Bottom.addBox(0.6F, -0.8F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg2Bottom, 0.0F, 0.0F, -0.855036800552022F);
        this.leg4Bottom = new ModelRenderer(this, 44, 24);
        this.leg4Bottom.setRotationPoint(-3.0F, 21.0F, -1.6F);
        this.leg4Bottom.addBox(-1.6F, -0.7F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(leg4Bottom, 0.0F, 0.0F, 0.855036800552022F);
    }

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		GL11.glPushMatrix();
		GL11.glScalef(0.1F, 0.1F, 0.1F);
		GL11.glTranslatef(0F, 13.6F, 0F);
        this.leg1Bottom.render(f5);
        this.leg5Top.render(f5);
        this.leg6Top.render(f5);
        this.thorax.render(f5);
        this.leg5Bottom.render(f5);
        this.shape23.render(f5);
        this.shape24.render(f5);
        this.head.render(f5);
        this.leg6Bottom.render(f5);
        this.node.render(f5);
        this.shape21.render(f5);
        this.leg3Bottom.render(f5);
        this.node2.render(f5);
        this.leg4Top.render(f5);
        this.leg2Top.render(f5);
        this.leg1Top.render(f5);
        this.leg3Top.render(f5);
        this.gaster.render(f5);
        this.shape22.render(f5);
        this.leg2Bottom.render(f5);
        this.leg4Bottom.render(f5);
		GL11.glPopMatrix();
	}

	private void setRotateAngle(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

}
