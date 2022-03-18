package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.oceanic.painfulmobs.PainfulMobsMod;
import net.oceanic.painfulmobs.explosions.NukeExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;


@Mixin(Zombie.class)
public class ZombieEditingMixin {
    @Inject(method = "isSunSensitive", at = @At("RETURN"), cancellable = true)
    private void injectedSunSensitive(CallbackInfoReturnable<Boolean> cir){
        if (PainfulMobsMod.getShouldModify(((Zombie)(Object)this).getLevel())) {
            cir.setReturnValue(false);
        }
    }


}

