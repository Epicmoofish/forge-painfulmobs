package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public class EndermiteEditingMixin {
    @Inject(method="isSensitiveToWater",at=@At("TAIL"),cancellable = true)
    protected void registerInjectedGoals(CallbackInfoReturnable<Boolean> cir) {
        if (ImpossibleDifficultyMod.getShouldModify(((LivingEntity)(Object)this).getLevel())) {
            if ((LivingEntity)(Object)this instanceof Endermite) {
                cir.setReturnValue(true);
            }
        }
    }


}

