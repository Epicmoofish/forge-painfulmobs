package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.monster.Guardian;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Guardian.class)
public class GuardianEditingMixin {
    @Inject(method="getAttackDuration",at=@At("RETURN"),cancellable = true)
    private void replaceAttackDuration(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(5);
    }


}

