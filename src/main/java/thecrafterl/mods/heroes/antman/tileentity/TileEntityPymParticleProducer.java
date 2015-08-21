package thecrafterl.mods.heroes.antman.tileentity;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thecrafterl.mods.heroes.antman.AntMan;
import thecrafterl.mods.heroes.antman.blocks.AMBlocks;
import thecrafterl.mods.heroes.antman.items.IPymParticleContainer;
import thecrafterl.mods.heroes.antman.util.IContentDropper;
import thecrafterl.mods.heroes.antman.util.PymParticleHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityPymParticleProducer extends TileEntity implements ISidedInventory, IContentDropper, IFluidHandler {

	private int slotAmount = 8;
	private ItemStack[] furnaceItemStacks = new ItemStack[slotAmount];
	
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
	
    public int capacity;
    public FluidTank water;
    public FluidTank lava;
    public FluidTank pymParticles;
    public int fluidPerTick;
	public int amountPerBucket = 1000;
	public int requiredAmount = 500;
    
    public byte facing;
    public boolean active;
    
    private String name = "pymParticleProducer";
    
	public TileEntityPymParticleProducer() {
		capacity = 5000;
		fluidPerTick = 10;
		facing = 2;
		active = false;
		water = new FluidTank(FluidRegistry.WATER, 0, capacity);
		lava = new FluidTank(FluidRegistry.LAVA, 0, capacity);
		pymParticles = new FluidTank(AMBlocks.pymParticles, 0, capacity);
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int p_145955_1_) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = 200;
		}
		
		return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		return this.furnaceCookTime * i / 200;
	}
	
	public void updateEntity() {
		
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		
		if(this.furnaceBurnTime > 0)
			--this.furnaceBurnTime;
		
		if (!this.worldObj.isRemote) {
			if((this.furnaceBurnTime != 0 || this.furnaceItemStacks[0] != null) && areSlotsEnoughOccupied()) {
				if(this.furnaceBurnTime == 0 && canSmelt()) {
					this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(furnaceItemStacks[0]);
					
					if(this.furnaceBurnTime > 0) {
						flag1 = true;
						if(this.furnaceItemStacks[0] != null) {
							--this.furnaceItemStacks[0].stackSize;

							if (this.furnaceItemStacks[0].stackSize == 0) {
								this.furnaceItemStacks[0] = furnaceItemStacks[0].getItem().getContainerItem(furnaceItemStacks[0]);
							}
						}
					}
				}
				
				if(this.isBurning() && this.canSmelt()) {
					++this.furnaceCookTime;
					
					if(this.furnaceCookTime == 200) {
						this.furnaceCookTime = 0;
						this.smeltItem();
						flag1 = true;
					}
				} else {
					furnaceCookTime = 0;
				}	
			}
			
			if(flag != this.furnaceBurnTime > 0) {
				flag1 = true;
			}
			
			if(flag1)
				markDirty();
		}
		
		this.active = flag1;
		
		if(water.getFluidAmount() < capacity) {
			if(getStackInSlot(5) != null && getStackInSlot(5).getItem() == Items.water_bucket) {
				setInventorySlotContents(5, new ItemStack(Items.bucket));
				fillWater(amountPerBucket);
			}
		}
		
		if(lava.getFluidAmount() < capacity) {
			if(getStackInSlot(6) != null && getStackInSlot(6).getItem() == Items.lava_bucket) {
				setInventorySlotContents(6, new ItemStack(Items.bucket));
				fillLava(amountPerBucket);
			}
		}
		
		int pyms = 1;
		
		if (pymParticles.getFluidAmount() < capacity && getStackInSlot(7) != null && getStackInSlot(7).getItem() instanceof IPymParticleContainer && PymParticleHandler.hasEnoughPymParticles(getStackInSlot(7), pyms) && getStackInSlot(4) == null) {
			PymParticleHandler.removePymParticles(getStackInSlot(7), pyms);
			fillPymParticles(pyms);
		}
		
		if(pymParticles.getFluidAmount() > 0) {
			if(getStackInSlot(4) != null && getStackInSlot(4).getItem() instanceof IPymParticleContainer && PymParticleHandler.canAddPymParticles(getStackInSlot(4), pyms)) {
				PymParticleHandler.addPymParticles(getStackInSlot(4), pyms);
				drainPymParticles(pyms);
			}
		}
		
	}
	
	public boolean areSlotsEnoughOccupied() {
		return this.furnaceItemStacks[1] != null && this.furnaceItemStacks[2] != null && this.furnaceItemStacks[3] != null;
	}
	
	private boolean canSmelt() {
		if(this.furnaceItemStacks[1] == null || this.furnaceItemStacks[2] == null || this.furnaceItemStacks[3] == null && this.water == null && this.lava == null)
			return false;
		else {
			return this.pymParticles.getFluidAmount() < capacity && this.water != null && this.lava != null && this.water.getFluidAmount() >= requiredAmount && this.lava.getFluidAmount() >= requiredAmount && this.furnaceItemStacks[1].getItem() == Item.getItemFromBlock(Blocks.obsidian) && this.furnaceItemStacks[2].getItem() == Items.dye && this.furnaceItemStacks[2].getItemDamage() == 15 && this.furnaceItemStacks[3].getItem() == Items.ender_pearl;
		}
	}
	
	public void smeltItem() {
		if(canSmelt()) {
			--this.furnaceItemStacks[1].stackSize;
			--this.furnaceItemStacks[2].stackSize;
			--this.furnaceItemStacks[3].stackSize;

			if (this.furnaceItemStacks[1].stackSize <= 0)
				this.furnaceItemStacks[1] = null;
			if (this.furnaceItemStacks[2].stackSize <= 0)
				this.furnaceItemStacks[2] = null;
			if (this.furnaceItemStacks[3].stackSize <= 0)
				this.furnaceItemStacks[3] = null;
			
			
			drainWater(requiredAmount);
			drainLava(requiredAmount);
			fillPymParticles(requiredAmount);
		}
	}
	
	public void fillWater(int amount) {
		this.water.fill(new FluidStack(FluidRegistry.WATER, amount), true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void fillLava(int amount) {
		this.lava.fill(new FluidStack(FluidRegistry.LAVA, amount), true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void fillPymParticles(int amount) {
		this.pymParticles.fill(new FluidStack(AMBlocks.pymParticles, amount), true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void drainWater(int amount) {
		this.water.drain(amount, true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void drainLava(int amount) {
		this.lava.drain(amount, true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void drainPymParticles(int amount) {
		this.pymParticles.drain(amount, true);
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return this.furnaceItemStacks[i];
	}

	@Override
	public int getSizeInventory() {
		return slotAmount;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.furnaceItemStacks[slot] != null) {
			ItemStack itemstack;

			if (this.furnaceItemStacks[slot].stackSize <= amount) {
				itemstack = this.furnaceItemStacks[slot];
				this.furnaceItemStacks[slot] = null;
				return itemstack;
			} else {
				itemstack = this.furnaceItemStacks[slot].splitStack(amount);

				if (this.furnaceItemStacks[slot].stackSize == 0) {
					this.furnaceItemStacks[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.furnaceItemStacks[i] != null) {
			ItemStack itemstack = this.furnaceItemStacks[i];
			this.furnaceItemStacks[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.furnaceItemStacks[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if(slot == 0) {
			return isItemFuel(stack);
		}
		return true;
	}
	
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null) {
			return 0;
		} else {
			Item item = stack.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.wooden_slab) {
					return 150;
				}

				if (block.getMaterial() == Material.wood) {
					return 300;
				}

				if (block == Blocks.coal_block) {
					return 16000;
				}
			}

			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD"))
				return 200;
			if (item == Items.stick)
				return 100;
			if (item == Items.coal)
				return 1600;
			if (item == Items.lava_bucket)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.sapling))
				return 100;
			if (item == Items.blaze_rod)
				return 2400;
			return GameRegistry.getFuelValue(stack);
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.writeCustomToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
        this.readCustomFromNBT(compound);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		if (resource.getFluid() == FluidRegistry.WATER) {
			return water.fill(resource, doFill);
		}

		if (resource.getFluid() == FluidRegistry.LAVA) {
			return lava.fill(resource, doFill);
		}

		if (resource.getFluid() == AMBlocks.pymParticles) {
			return pymParticles.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		if(!canDrain(from, resource.getFluid())) {
			return null;
		}
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return pymParticles.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(fluid == FluidRegistry.WATER) {
			return true;
		} else if(fluid == FluidRegistry.LAVA) {
			return true;
		} else if(fluid == AMBlocks.pymParticles) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(fluid == this.pymParticles.getFluid().getFluid()) {
			return this.pymParticles != null;
		}
		
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(water), new FluidTankInfo(lava), new FluidTankInfo(pymParticles)};
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int slot) {
		return new int[6];
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int amount) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 4;
	}

	@Override
	public ArrayList<ItemStack> getDrops() {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		
		for(ItemStack stacks : this.furnaceItemStacks)
			drops.add(stacks);
		
		return drops;
	}
	
	public void readCustomFromNBT(NBTTagCompound compound) {
		facing = compound.getByte("Facing");
		active = compound.getBoolean("Active");
		
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[slotAmount];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < slotAmount)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = compound.getShort("BurnTime");
        this.furnaceCookTime = compound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[0]);
        
        if(compound.hasKey("WaterTank")) {
        	water.readFromNBT(compound.getCompoundTag("WaterTank"));
        } if(compound.hasKey("LavaTank")) {
        	lava.readFromNBT(compound.getCompoundTag("LavaTank"));
        } if(compound.hasKey("PymParticlesTank")) {
        	pymParticles.readFromNBT(compound.getCompoundTag("PymParticlesTank"));
        }
	}
	
	public void writeCustomToNBT(NBTTagCompound compound) {
		compound.setByte("Facing", facing);
		compound.setBoolean("Active", active);
		compound.setShort("BurnTime", (short)this.furnaceBurnTime);
		compound.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < slotAmount; ++i) {
			if (this.furnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

        compound.setTag("Items", nbttaglist);
        
        if(water != null) {
        	NBTTagCompound waterCompound = new NBTTagCompound();
        	water.writeToNBT(waterCompound);
        	compound.setTag("WaterTank", waterCompound);
        } 
        if(lava != null) {
        	NBTTagCompound lavaCompound = new NBTTagCompound();
        	lava.writeToNBT(lavaCompound);
        	compound.setTag("LavaTank", lavaCompound);
        }
        if(pymParticles != null) {
        	NBTTagCompound pymParticlesCompound = new NBTTagCompound();
        	pymParticles.writeToNBT(pymParticlesCompound);
        	compound.setTag("PymParticlesTank", pymParticlesCompound);
        }
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readCustomFromNBT(pkt.func_148857_g());
        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
	}
	

	@Override
	public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeCustomToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
}