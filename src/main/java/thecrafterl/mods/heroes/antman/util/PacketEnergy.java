package thecrafterl.mods.heroes.antman.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import thecrafterl.mods.heroes.antman.util.AntManUtils.IAntManPacket;

public class PacketEnergy implements IAntManPacket {

	private int x;
	private int y;
	private int z;
	private int energyStored;

	public PacketEnergy() {
	}

	public PacketEnergy(int x, int y, int z, int energyStored) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.energyStored = energyStored;
	}

	@Override
	public void write(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(energyStored);
	}

	@Override
	public void read(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		energyStored = buffer.readInt();
	}

	@Override
	public void executeClientSide(EntityPlayer player) {
		TileEntity tileEntity = player.worldObj.getTileEntity(x, y, z);

		if (tileEntity instanceof ISyncEnergy) {
			((ISyncEnergy) tileEntity).setEnergy(energyStored);
		}
	}

	@Override
	public void executeServerSide(EntityPlayer player) {

	}
}
