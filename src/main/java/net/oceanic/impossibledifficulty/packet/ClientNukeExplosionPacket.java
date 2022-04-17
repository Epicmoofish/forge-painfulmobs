package net.oceanic.impossibledifficulty.packet;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.oceanic.impossibledifficulty.explosions.NukeExplosion;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.oceanic.impossibledifficulty.mixins.ClientGettingMixin;

import javax.annotation.Nullable;
public class ClientNukeExplosionPacket {
    private final double x;
    private final double y;
    private final double z;
    private final float power;
    private final List<BlockPos> toBlow;

    public ClientNukeExplosionPacket(double p_132115_, double p_132116_, double p_132117_, float p_132118_, List<BlockPos> p_132119_) {
        this.x = p_132115_;
        this.y = p_132116_;
        this.z = p_132117_;
        this.power = p_132118_;
        this.toBlow = Lists.newArrayList(p_132119_);

    }

    public ClientNukeExplosionPacket(FriendlyByteBuf p_178845_) {
        this.x = (double)p_178845_.readFloat();
        this.y = (double)p_178845_.readFloat();
        this.z = (double)p_178845_.readFloat();
        this.power = p_178845_.readFloat();
        int i = Mth.floor(this.x);
        int j = Mth.floor(this.y);
        int k = Mth.floor(this.z);
        this.toBlow = p_178845_.readList((p_178850_) -> {
            int l = p_178850_.readByte() + i;
            int i1 = p_178850_.readByte() + j;
            int j1 = p_178850_.readByte() + k;
            return new BlockPos(l, i1, j1);
        });
    }

    public void encode(FriendlyByteBuf p_132129_) {
        p_132129_.writeFloat((float)this.x);
        p_132129_.writeFloat((float)this.y);
        p_132129_.writeFloat((float)this.z);
        p_132129_.writeFloat(this.power);
        int i = Mth.floor(this.x);
        int j = Mth.floor(this.y);
        int k = Mth.floor(this.z);
        p_132129_.writeCollection(this.toBlow, (p_178855_, p_178856_) -> {
            int l = p_178856_.getX() - i;
            int i1 = p_178856_.getY() - j;
            int j1 = p_178856_.getZ() - k;
            p_178855_.writeByte(l);
            p_178855_.writeByte(i1);
            p_178855_.writeByte(j1);
        });
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                    () -> () -> success.set(ClientPacketHandler.handleExplosion(this.x,this.y,this.z,this.power,this.toBlow)));
        });

        ctx.get().setPacketHandled(true);
        return success.get();
    }
}