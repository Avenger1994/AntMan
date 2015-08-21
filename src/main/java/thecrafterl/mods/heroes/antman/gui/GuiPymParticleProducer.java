package thecrafterl.mods.heroes.antman.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

import org.lwjgl.opengl.GL11;

import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.container.ContainerPymParticleProducer;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymParticleProducer;

import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPymParticleProducer extends GuiContainer
{
    private static final ResourceLocation PymParticleProducerGuiTextures = new ResourceLocation(AntMan.ASSETDIR + "textures/gui/pymParticleProducer.png");
    private TileEntityPymParticleProducer tilePymParticleProducer;

    public GuiPymParticleProducer(InventoryPlayer inventory, TileEntityPymParticleProducer tileentity)
    {
        super(new ContainerPymParticleProducer(inventory, tileentity));
        this.tilePymParticleProducer = tileentity;
        this.xSize = 208;
        this.ySize = 183;
    }

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
        fr.drawSplitString(ChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tile." + tilePymParticleProducer.getInventoryName() + ".name"), 75, 5, 70, 0 );
//        fr.drawSplitString(ChatFormatting.DARK_GRAY + "Client: " + this.tilePymParticleProducer.pymParticles.getFluidAmount(), 105, 25, 70, 0 );
//        fr.drawSplitString(ChatFormatting.DARK_GRAY + "Server: " + this.tilePymParticleProducerServer.pymParticles.getFluidAmount(), 105, 35, 70, 0 );
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		super.drawScreen(x, y, f);
		this.drawToolTips(x, y, FluidRegistry.WATER, 29, 26);
		this.drawToolTips(x, y, FluidRegistry.LAVA, 49, 26);
		this.drawToolTips(x, y, AMBlocks.pymParticles, 185, 26);
	}
	
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(PymParticleProducerGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
//		this.drawItemIcon(new ItemStack(Items.water_bucket), k + 29, l + 6);		
		
		drawFluidTank(tilePymParticleProducer.water, k + 29, l + 26);
		drawFluidTank(tilePymParticleProducer.lava, k + 49, l + 26);
		drawFluidTank(tilePymParticleProducer.pymParticles, k + 185, l + 26);
		
		if (this.tilePymParticleProducer.isBurning()) {
			int i1 = this.tilePymParticleProducer.getBurnTimeRemainingScaled(13);
			this.drawTexturedModalRect(k + 7, l + 45 + 12 - i1, 208, 12 - i1, 14, i1 + 2);
			i1 = this.tilePymParticleProducer.getCookProgressScaled(130);
			this.drawTexturedModalRect(k + 41, l + 32, 0, 183, i1 + 1, 64);
		}
	}
	
	public void drawToolTips(int mouseX, int mouseY, Fluid fluid, int x, int y) {
		int boxX = (this.width - this.xSize) / 2 + x;
		int boxY = (this.height - this.ySize) / 2 + y;

		int defaultX = 16;
		int defaultY = 60;

		if (mouseX > boxX && mouseX < boxX + defaultX && mouseY > boxY && mouseY < boxY + defaultY) {
			List list = new ArrayList();

			if (fluid == FluidRegistry.WATER)
				list.add(StatCollector.translateToLocal("tile.water.name") + ": " + getFluidAmount(tilePymParticleProducer.water) + "/" + tilePymParticleProducer.capacity + " mB");

			if (fluid == FluidRegistry.LAVA) {
				list.add(StatCollector.translateToLocal("tile.lava.name") + ": " + getFluidAmount(tilePymParticleProducer.lava) + "/" + tilePymParticleProducer.capacity + " mB");
			}

			if (fluid == AMBlocks.pymParticles) {
				list.add(StatCollector.translateToLocal("fluid.pymParticles") + ": " + getFluidAmount(tilePymParticleProducer.pymParticles) + "/" + tilePymParticleProducer.capacity + " mB");
			}

			this.drawHoveringText(list, mouseX, mouseY, fontRendererObj);
		}
	}
	
//	@Override
//	protected void func_146283_a(List list, int x, int y) {
//		if(getFluidFromHover(x, y) != null) {
//			if(getFluidFromHover(x, y) == FluidRegistry.WATER) {
//				list.add(StatCollector.translateToLocal("tile.water.name") + ": " + getFluidAmount(tilePymParticleProducer.water) + "/" + tilePymParticleProducer.capacity + " mB");
//			}
//			
//			if(getFluidFromHover(x, y) == FluidRegistry.LAVA) {
//				list.add(StatCollector.translateToLocal("tile.lava.name") + ": " + getFluidAmount(tilePymParticleProducer.lava) + "/" + tilePymParticleProducer.capacity + " mB");
//			}
//			
//			if(getFluidFromHover(x, y) == AMBlocks.pymParticles) {
//				list.add(StatCollector.translateToLocal("fluid.pymParticles") + ": " + getFluidAmount(tilePymParticleProducer.pymParticles) + "/" + tilePymParticleProducer.capacity + " mB");
//			}
//		}
//	}
	
	private int getFluidAmount(FluidTank tank) {
		if(tank == null)
			return 0;
		else
			return tank.getFluidAmount();
	}

	public void drawFluidTank(IFluidTank tank, int x, int y) {
		FluidStack fluid = tank.getFluid();
		TextureManager manager = Minecraft.getMinecraft().renderEngine;
		if (fluid != null) {
			manager.bindTexture(manager.getResourceLocation(0));
			float amount = fluid.amount;
			float capacity = tank.getCapacity();
			float scale = amount / capacity;
			int fluidTankHeight = 60;
			int fluidAmount = (int) (scale * fluidTankHeight);
			drawFluid(x, y + fluidTankHeight - fluidAmount, fluid.getFluid().getIcon(fluid), 16, fluidAmount);
			manager.bindTexture(PymParticleProducerGuiTextures);
			drawTexturedModalRect(x - 1, y - 1, 238, 0, 18, 62);
		}
	}

	private void drawItemIcon(ItemStack stack, int x, int y) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		IIcon iicon1 = stack.getIconIndex();
		this.mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
		this.drawTexturedModelRectFromIcon(x, y, iicon1, 16, 16);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		this.mc.getTextureManager().bindTexture(PymParticleProducerGuiTextures);
	}
	
	private void drawFluid(int x, int y, IIcon icon, int width, int height) {
		int i = 0;
		int j = 0;

		int drawHeight = 0;
		int drawWidth = 0;

		for (i = 0; i < width; i += 16) {
			for (j = 0; j < height; j += 16) {
				drawWidth = Math.min(width - i, 16);
				drawHeight = Math.min(height - j, 16);
				drawRectangleFromIcon(x + i, y + j, icon, drawWidth, drawHeight);
			}
		}
	}

	private void drawRectangleFromIcon(int x, int y, IIcon icon, int width, int height) {
		if (icon == null)
			return;
		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 0, minU, minV + (maxV - minV) * height / 16.0D);
		tessellator.addVertexWithUV(x + width, y + height, 0, minU + (maxU - minU) * width / 16.0D, minV + (maxV - minV) * height / 16.0D);
		tessellator.addVertexWithUV(x + width, y + 0, 0, minU + (maxU - minU) * width / 16.0D, minV);
		tessellator.addVertexWithUV(x + 0, y + 0, 0, minU, minV);
		tessellator.draw();
	}
}