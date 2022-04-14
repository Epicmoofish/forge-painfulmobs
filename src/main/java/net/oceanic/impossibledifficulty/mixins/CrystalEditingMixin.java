package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.oceanic.impossibledifficulty.explosions.NukeExplosion;
import net.oceanic.impossibledifficulty.packet.ClientNukeExplosionPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;


@Mixin(EndCrystal.class)
public class CrystalEditingMixin {
@Redirect(method="hurt",at=@At(value="INVOKE",target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Explosion$BlockInteraction;)Lnet/minecraft/world/level/Explosion;"))
private Explosion customExplode(Level level, Entity p_46512_, double p_46513_, double p_46514_, double p_46515_, float p_46516_, Explosion.BlockInteraction p_46517_){

        return this.doNukeExplosion(level, (EndCrystal)(Object)this, (DamageSource) null, (ExplosionDamageCalculator) null, p_46513_, p_46514_, p_46515_, 10, false, p_46517_);

}
    public Explosion doNukeExplosion(Level level,@Nullable Entity p_46526_, @Nullable DamageSource p_46527_, @Nullable ExplosionDamageCalculator p_46528_, double p_46529_, double p_46530_, double p_46531_, float p_46532_, boolean p_46533_, Explosion.BlockInteraction p_46534_) {
        Explosion explosion = new NukeExplosion(level, p_46526_, p_46527_, p_46528_, p_46529_, p_46530_, p_46531_, p_46532_, false, p_46534_);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, explosion)) return explosion;
        explosion.explode();
        explosion.finalizeExplosion(true);
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            for (ServerPlayer serverplayer : serverLevel.players()) {
                if (serverplayer.distanceToSqr(p_46529_, p_46530_, p_46531_) < 4096.0D) {
                    serverplayer.connection.send(new ClientNukeExplosionPacket(p_46529_, p_46530_, p_46531_, p_46532_, explosion.getToBlow(), explosion.getHitPlayers().get(serverplayer)));
                }
            }
        }
        return explosion;
    }

}

