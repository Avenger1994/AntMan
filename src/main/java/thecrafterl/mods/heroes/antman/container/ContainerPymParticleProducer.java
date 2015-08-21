package thecrafterl.mods.heroes.antman.container;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thecrafterl.mods.heroes.antman.tileentity.TileEntityPymParticleProducer;
import thecrafterl.mods.heroes.antman.util.AntManUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;

public class ContainerPymParticleProducer extends Container {
	private TileEntityPymParticleProducer tileFurnace;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
    public FluidTank water;
    public FluidTank lava;
    public FluidTank pymParticles;
	private static final String __OBFID = "CL_00001748";

	public ContainerPymParticleProducer(InventoryPlayer inventory, TileEntityPymParticleProducer tileEntity) {
		this.tileFurnace = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 7, 62));
		this.addSlotToContainer(new SlotOneItem(tileEntity, 1, 80, 68, new ItemStack(Blocks.obsidian)));
		this.addSlotToContainer(new SlotOneItem(tileEntity, 2, 80, 48, new ItemStack(Items.dye, 1, 15)));
		this.addSlotToContainer(new SlotOneItem(tileEntity, 3, 80, 28, new ItemStack(Items.ender_pearl)));
		this.addSlotToContainer(new SlotPymParticleTank(tileEntity, 4, 152, 70));
		this.addSlotToContainer(new SlotOneItem(tileEntity, 5, 29, 6, new ItemStack(Items.water_bucket)));
		this.addSlotToContainer(new SlotOneItem(tileEntity, 6, 49, 6, new ItemStack(Items.lava_bucket)));
		this.addSlotToContainer(new SlotPymParticleTank(tileEntity, 7, 185, 6));
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 24 + j * 18, 101 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventory, i, 24 + i * 18, 159));
		}
		
		this.water = tileFurnace.water;
		this.lava = tileFurnace.lava;
		this.pymParticles = tileFurnace.pymParticles;
		
	}

	public void addCraftingToCrafters(ICrafting p_75132_1_) {
		super.addCraftingToCrafters(p_75132_1_);
		p_75132_1_.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
		p_75132_1_.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
		p_75132_1_.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.tileFurnace.furnaceCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
			}

			if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
			}

			if (this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.tileFurnace.furnaceCookTime;
		this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
		this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
		this.water = tileFurnace.water;
		this.lava = tileFurnace.lava;
		this.pymParticles = tileFurnace.pymParticles;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
		if (p_75137_1_ == 0) {
			this.tileFurnace.furnaceCookTime = p_75137_2_;
		}

		if (p_75137_1_ == 1) {
			this.tileFurnace.furnaceBurnTime = p_75137_2_;
		}

		if (p_75137_1_ == 2) {
			this.tileFurnace.currentItemBurnTime = p_75137_2_;
		}
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return this.tileFurnace.isUseableByPlayer(p_75145_1_);
	}

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {

        return null;
    }
}
