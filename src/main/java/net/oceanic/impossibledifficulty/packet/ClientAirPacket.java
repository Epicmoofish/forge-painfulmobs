package net.oceanic.impossibledifficulty.packet;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.player.Player;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;
import net.oceanic.impossibledifficulty.interfaces.PlayerGettingMixin;

import java.util.UUID;

public class ClientAirPacket implements Packet<ClientGamePacketListener> {
    private final int air;
    private final UUID uuid;
    private final boolean aquatic;
    public ClientAirPacket(int p_132115_,UUID uuid, boolean aquatic) {
        this.air = p_132115_;
        this.uuid = uuid;
        this.aquatic = aquatic;
    }

    public ClientAirPacket(FriendlyByteBuf p_178845_) {
        this.air = (int)p_178845_.readInt();
        this.uuid = (UUID)p_178845_.readUUID();
        this.aquatic = (Boolean)p_178845_.readBoolean();
    }

    public void write(FriendlyByteBuf p_132129_) {
        p_132129_.writeInt((int)this.air);
        p_132129_.writeUUID((UUID)this.uuid);
        p_132129_.writeBoolean((Boolean)this.aquatic);
    }

    public void handle(ClientGamePacketListener p_132126_) {
        this.handleExplosion(p_132126_);
    }
    public void handleExplosion(ClientGamePacketListener p_132126_) {
        PacketUtils.ensureRunningOnSameThread(this, p_132126_, ((ClientGettingMixin)p_132126_).getMinecraft());
        if (((ClientGettingMixin)p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid) !=null) {
            ((ClientGettingMixin) p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid).setAirSupply(this.air);
            if (((ClientGettingMixin) p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid) instanceof LocalPlayer) {
                Player player = ((ClientGettingMixin) p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid);
                LocalPlayer localplayer = (LocalPlayer) player;
                PlayerGettingMixin playermixin = (PlayerGettingMixin)localplayer;
                playermixin.setIsAquatic(this.aquatic);
            }
        }
        }
    public int getAir() {
        return this.air;
    }
    public UUID getUUID() {
        return this.uuid;
    }
    public boolean getAquatic() {
        return this.aquatic;
    }
}