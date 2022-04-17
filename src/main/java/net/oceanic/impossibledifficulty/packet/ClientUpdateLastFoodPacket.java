package net.oceanic.impossibledifficulty.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.oceanic.impossibledifficulty.interfaces.PlayerEatingGettingMixin;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ClientUpdateLastFoodPacket {
    public final String lastFood;
    public final UUID uuid;
    public ClientUpdateLastFoodPacket(String lastFood, UUID uuid) {
        this.lastFood = lastFood;
        this.uuid = uuid;
    }

    public ClientUpdateLastFoodPacket(FriendlyByteBuf p_178845_) {
        this.lastFood = (String)p_178845_.readUtf();
        this.uuid = (UUID)p_178845_.readUUID();
    }

    public void encode(FriendlyByteBuf p_132129_) {
        p_132129_.writeUtf((String)this.lastFood);
        p_132129_.writeUUID((UUID)this.uuid);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> success.set(ClientPacketHandler.handleLastFood(this.lastFood,this.uuid)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }

}