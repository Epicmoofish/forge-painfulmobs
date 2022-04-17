package net.oceanic.impossibledifficulty.packet;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;
import net.oceanic.impossibledifficulty.interfaces.PlayerGettingMixin;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ClientAirPacket {
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

    public void encode(FriendlyByteBuf p_132129_) {
        p_132129_.writeInt((int)this.air);
        p_132129_.writeUUID((UUID)this.uuid);
        p_132129_.writeBoolean((Boolean)this.aquatic);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> success.set(ClientPacketHandler.handleAirPacket(this.air,this.uuid,this.aquatic)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }
}