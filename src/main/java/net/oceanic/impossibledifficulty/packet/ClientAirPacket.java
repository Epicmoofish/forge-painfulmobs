package net.oceanic.impossibledifficulty.packet;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.oceanic.impossibledifficulty.explosions.NukeExplosion;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ClientAirPacket implements Packet<ClientGamePacketListener> {
    private final int air;
    private final UUID uuid;
    public ClientAirPacket(int p_132115_,UUID uuid) {
        this.air = p_132115_;
        this.uuid = uuid;
    }

    public ClientAirPacket(FriendlyByteBuf p_178845_) {
        this.air = (int)p_178845_.readInt();
        this.uuid = (UUID)p_178845_.readUUID();
    }

    public void write(FriendlyByteBuf p_132129_) {
        p_132129_.writeInt((int)this.air);
        p_132129_.writeUUID((UUID)this.uuid);
    }

    public void handle(ClientGamePacketListener p_132126_) {
        this.handleExplosion(p_132126_);
    }
    public void handleExplosion(ClientGamePacketListener p_132126_) {
        PacketUtils.ensureRunningOnSameThread(this, p_132126_, ((ClientGettingMixin)p_132126_).getMinecraft());
        if (((ClientGettingMixin)p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid) !=null) {
            ((ClientGettingMixin) p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid).setAirSupply(this.air);
            System.out.println("Hopefully updated air");
        }
        }
    public int getAir() {
        return this.air;
    }
    public UUID getUUID() {
        return this.uuid;
    }
}