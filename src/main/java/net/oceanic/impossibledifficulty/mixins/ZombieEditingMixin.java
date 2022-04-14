package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.monster.Zombie;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Zombie.class)
public class ZombieEditingMixin {
    @Inject(method = "isSunSensitive", at = @At("RETURN"), cancellable = true)
    private void injectedSunSensitive(CallbackInfoReturnable<Boolean> cir){
        if (ImpossibleDifficultyMod.getShouldModify(((Zombie)(Object)this).getLevel())) {
            cir.setReturnValue(false);
        }
    }


}

