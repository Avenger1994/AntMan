package thecrafterl.mods.heroes.antman.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPymWorkbench extends ModelBase {
	
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer blueprints;

    public ModelPymWorkbench() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-7.0F, 10.0F, 6.0F, 1, 14, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 17);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(-8.0F, 21.0F, -8.0F, 16, 1, 16, -0.2F);
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-7.0F, 10.0F, -7.0F, 1, 14, 1, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(6.0F, 10.0F, -7.0F, 1, 14, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 17);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-8.0F, 9.0F, -8.0F, 16, 1, 16, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(6.0F, 10.0F, 6.0F, 1, 14, 1, 0.0F);
        this.blueprints = new ModelRenderer(this, 0, 34);
        this.blueprints.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.blueprints.addBox(-8.0F, 20.8F, -8.0F, 16, 1, 16, -0.2F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1.render(f5);
        this.shape5.render(f5);
        this.shape3.render(f5);
        this.shape4.render(f5);
        this.shape6.render(f5);
        this.shape2.render(f5);
        this.blueprints.render(f5);
	}
	
	public void renderModel(float f) {
		this.shape2.render(f);
		this.shape5.render(f);
		this.shape4.render(f);
		this.shape6.render(f);
		this.shape1.render(f);
		this.shape3.render(f);
		this.blueprints.render(f);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
