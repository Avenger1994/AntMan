package thecrafterl.mods.heroes.antman.entity.render;

import org.lwjgl.opengl.GL11;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.client.models.ModelCrazyAnt;
import thecrafterl.mods.heroes.antman.entity.EntityAnt;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCrazyAnt extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation(AntMan.ASSETDIR + "textures/models/entity/crazyAnt.png");
	
	private ModelCrazyAnt model;
	
	public RenderCrazyAnt(ModelBase model, float f) {
		super(model, f);
		this.model = (ModelCrazyAnt) model;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityCrazyAntTexture((EntityAnt)entity);
	}
	
	protected ResourceLocation getEntityCrazyAntTexture(EntityAnt entity) {
		return texture;
	}

}