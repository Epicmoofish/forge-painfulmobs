package net.oceanic.impossibledifficulty.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.oceanic.impossibledifficulty.interfaces.PlayerEatingGettingMixin;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;

import java.util.UUID;
public class ClientUpdateLastFoodPacket implements Packet<ClientGamePacketListener> {
    private final String lastFood;
    private final UUID uuid;
    public ClientUpdateLastFoodPacket(String lastFood, UUID uuid) {
        this.lastFood = lastFood;
        this.uuid = uuid;
    }

    public ClientUpdateLastFoodPacket(FriendlyByteBuf p_178845_) {
        this.lastFood = (String)p_178845_.readUtf();
        this.uuid = (UUID)p_178845_.readUUID();
    }

    public void write(FriendlyByteBuf p_132129_) {
        p_132129_.writeUtf((String)this.lastFood);
        p_132129_.writeUUID((UUID)this.uuid);
    }

    public void handle(ClientGamePacketListener p_132126_) {
        this.handleExplosion(p_132126_);
    }
    @OnlyIn(Dist.CLIENT)
    public void handleExplosion(ClientGamePacketListener p_132126_) {
        PacketUtils.ensureRunningOnSameThread(this, p_132126_, ((ClientGettingMixin)p_132126_).getMinecraft());
        if (((ClientGettingMixin)p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid) !=null) {
            ((PlayerEatingGettingMixin)(((ClientGettingMixin) p_132126_).getMinecraft().level.getPlayerByUUID(this.uuid))).setLastFood(lastFood);
        }
        }
    public String getLastFood() {
        return this.lastFood;
    }
    public UUID getUUID() {
        return this.uuid;
    }
}