package thecrafterl.mods.heroes.antman.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCrazyAnt extends ModelBase {
    // fields
    ModelRenderer gaster;
    ModelRenderer head;
    ModelRenderer node2;
    ModelRenderer node;
    ModelRenderer thorax;
    ModelRenderer mandibleLeft;
    ModelRenderer mandibleRight;
    ModelRenderer legLeftBottom;
    ModelRenderer antennaLeft;
    ModelRenderer antennaRight;
    ModelRenderer legLeft2Top;
    ModelRenderer legRight1Top;
    ModelRenderer legRight1Bottom;
    ModelRenderer legLeft1Top;
    ModelRenderer legLeft1Bottom;
    ModelRenderer legLeft3Top;
    ModelRenderer legLeft3Bottom;
    ModelRenderer legRight3Top;
    ModelRenderer legRight3Bottom;
    ModelRenderer legRight2Top;
    ModelRenderer legRight2Bottom;

    public ModelCrazyAnt() {
	textureWidth = 32;
	textureHeight = 32;

	gaster = new ModelRenderer(this, 10, 10);
	gaster.addBox(-3F, -1F, 0F, 6, 3, 5);
	gaster.setRotationPoint(0F, 21F, 3F);
	gaster.setTextureSize(64, 32);
	gaster.mirror = true;
	setRotation(gaster, 0F, 0F, 0F);
	head = new ModelRenderer(this, 0, 0);
	head.addBox(-2F, -1F, -3F, 4, 1, 2);
	head.setRotationPoint(0F, 22F, -2F);
	head.setTextureSize(64, 32);
	head.mirror = true;
	setRotation(head, 0F, 0F, 0F);
	node2 = new ModelRenderer(this, 12, 7);
	node2.addBox(-1F, -1F, -1F, 2, 1, 1);
	node2.setRotationPoint(0F, 22F, -2F);
	node2.setTextureSize(64, 32);
	node2.mirror = true;
	setRotation(node2, 0F, 0F, 0F);
	node = new ModelRenderer(this, 21, 6);
	node.addBox(-1F, 0F, -2F, 2, 1, 2);
	node.setRotationPoint(0F, 21F, 3F);
	node.setTextureSize(64, 32);
	node.mirror = true;
	setRotation(node, 0F, 0F, 0F);
	thorax = new ModelRenderer(this, 18, 0);
	thorax.addBox(-2F, -1F, -3F, 4, 2, 3);
	thorax.setRotationPoint(0F, 22F, 1F);
	thorax.setTextureSize(64, 32);
	thorax.mirror = true;
	setRotation(thorax, 0F, 0F, 0F);
	mandibleLeft = new ModelRenderer(this, 0, 7);
	mandibleLeft.addBox(0F, 0F, -1F, 1, 1, 1);
	mandibleLeft.setRotationPoint(-2F, 21F, -5F);
	mandibleLeft.setTextureSize(64, 32);
	mandibleLeft.mirror = true;
	setRotation(mandibleLeft, 0F, -0.3346075F, 0F);
	mandibleRight = new ModelRenderer(this, 5, 7);
	mandibleRight.addBox(-1F, 0F, -1F, 1, 1, 1);
	mandibleRight.setRotationPoint(2F, 21F, -5F);
	mandibleRight.setTextureSize(64, 32);
	mandibleRight.mirror = true;
	setRotation(mandibleRight, 0F, 0.3346145F, 0F);
	legLeftBottom = new ModelRenderer(this, 7, 24);
	legLeftBottom.addBox(-1F, -1F, 0F, 1, 5, 1);
	legLeftBottom.setRotationPoint(-3F, 21F, -1F);
	legLeftBottom.setTextureSize(64, 32);
	legLeftBottom.mirror = true;
	setRotation(legLeftBottom, 0F, 0F, 0.8551066F);
	antennaLeft = new ModelRenderer(this, 0, 10);
	antennaLeft.addBox(0F, -2F, 0F, 1, 2, 1);
	antennaLeft.setRotationPoint(-2F, 21F, -5F);
	antennaLeft.setTextureSize(64, 32);
	antennaLeft.mirror = true;
	setRotation(antennaLeft, 0.0743572F, 0F, -0.1858931F);
	antennaRight = new ModelRenderer(this, 5, 10);
	antennaRight.addBox(0F, -2F, 0F, 1, 2, 1);
	antennaRight.setRotationPoint(1F, 21F, -5F);
	antennaRight.setTextureSize(64, 32);
	antennaRight.mirror = true;
	setRotation(antennaRight, -0.074351F, 0F, 0.185895F);
	legLeft2Top = new ModelRenderer(this, 7, 19);
	legLeft2Top.addBox(-1F, -3F, 0F, 1, 3, 1);
	legLeft2Top.setRotationPoint(-1F, 22F, -1F);
	legLeft2Top.setTextureSize(64, 32);
	legLeft2Top.mirror = true;
	setRotation(legLeft2Top, 0F, 0F, -0.8551066F);
	legRight1Top = new ModelRenderer(this, 17, 19);
	legRight1Top.addBox(-1F, -3F, 0F, 1, 3, 1);
	legRight1Top.setRotationPoint(1F, 22F, -2F);
	legRight1Top.setTextureSize(64, 32);
	legRight1Top.mirror = true;
	setRotation(legRight1Top, 0.4833281F, 0F, 0.85511F);
	legRight1Bottom = new ModelRenderer(this, 17, 24);
	legRight1Bottom.addBox(0F, 0F, -1F, 1, 5, 1);
	legRight1Bottom.setRotationPoint(2F, 20F, -2F);
	legRight1Bottom.setTextureSize(64, 32);
	legRight1Bottom.mirror = true;
	setRotation(legRight1Bottom, -0.074351F, 0.3717861F, -0.8551061F);
	legLeft1Top = new ModelRenderer(this, 2, 19);
	legLeft1Top.addBox(0F, -3F, 0F, 1, 3, 1);
	legLeft1Top.setRotationPoint(-2F, 22F, -2F);
	legLeft1Top.setTextureSize(64, 32);
	legLeft1Top.mirror = true;
	setRotation(legLeft1Top, 0.483334F, 0F, -0.8551066F);
	legLeft1Bottom = new ModelRenderer(this, 2, 24);
	legLeft1Bottom.addBox(0F, 0F, 0F, 1, 5, 1);
	legLeft1Bottom.setRotationPoint(-3F, 19F, -3F);
	legLeft1Bottom.setTextureSize(64, 32);
	legLeft1Bottom.mirror = true;
	setRotation(legLeft1Bottom, -0.074351F, -0.37179F, 0.8551066F);
	legLeft3Top = new ModelRenderer(this, 12, 19);
	legLeft3Top.addBox(0F, -3F, -1F, 1, 3, 1);
	legLeft3Top.setRotationPoint(-2F, 22F, 1F);
	legLeft3Top.setTextureSize(64, 32);
	legLeft3Top.mirror = true;
	setRotation(legLeft3Top, -0.483334F, 0F, -0.8551066F);
	legLeft3Bottom = new ModelRenderer(this, 12, 24);
	legLeft3Bottom.addBox(0F, 0F, -1F, 1, 5, 1);
	legLeft3Bottom.setRotationPoint(-4F, 19F, 2F);
	legLeft3Bottom.setTextureSize(64, 32);
	legLeft3Bottom.mirror = true;
	setRotation(legLeft3Bottom, 0.074351F, 0.37179F, 0.8551066F);
	legRight3Top = new ModelRenderer(this, 27, 19);
	legRight3Top.addBox(-1F, -3F, -1F, 1, 3, 1);
	legRight3Top.setRotationPoint(2F, 22F, 1F);
	legRight3Top.setTextureSize(64, 32);
	legRight3Top.mirror = true;
	setRotation(legRight3Top, -0.483334F, 0F, 0.8551066F);
	legRight3Bottom = new ModelRenderer(this, 27, 24);
	legRight3Bottom.addBox(0F, -1F, -1F, 1, 5, 1);
	legRight3Bottom.setRotationPoint(3F, 20F, 2F);
	legRight3Bottom.setTextureSize(64, 32);
	legRight3Bottom.mirror = true;
	setRotation(legRight3Bottom, 0.074351F, -0.37179F, -0.8551066F);
	legRight2Top = new ModelRenderer(this, 22, 19);
	legRight2Top.addBox(-1F, -3F, 0F, 1, 3, 1);
	legRight2Top.setRotationPoint(2F, 22F, -1F);
	legRight2Top.setTextureSize(64, 32);
	legRight2Top.mirror = true;
	setRotation(legRight2Top, 0F, 0F, 0.8551066F);
	legRight2Bottom = new ModelRenderer(this, 22, 24);
	legRight2Bottom.addBox(0F, -1F, 0F, 1, 5, 1);
	legRight2Bottom.setRotationPoint(3F, 21F, -1F);
	legRight2Bottom.setTextureSize(64, 32);
	legRight2Bottom.mirror = true;
	setRotation(legRight2Bottom, 0F, 0F, -0.8551066F);
    }

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		GL11.glPushMatrix();
		GL11.glScalef(0.1F, 0.1F, 0.1F);
		GL11.glTranslatef(0F, 13.6F, 0F);
		gaster.render(f5);
		head.render(f5);
		node2.render(f5);
		node.render(f5);
		thorax.render(f5);
		mandibleLeft.render(f5);
		mandibleRight.render(f5);
		legLeftBottom.render(f5);
		antennaLeft.render(f5);
		antennaRight.render(f5);
		legLeft2Top.render(f5);
		legRight1Top.render(f5);
		legRight1Bottom.render(f5);
		legLeft1Top.render(f5);
		legLeft1Bottom.render(f5);
		legLeft3Top.render(f5);
		legLeft3Bottom.render(f5);
		legRight3Top.render(f5);
		legRight3Bottom.render(f5);
		legRight2Top.render(f5);
		legRight2Bottom.render(f5);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

}
