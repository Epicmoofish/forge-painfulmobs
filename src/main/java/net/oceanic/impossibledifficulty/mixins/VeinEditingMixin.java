package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.level.levelgen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(NoiseGeneratorSettings.class)

public class VeinEditingMixin {
    @Inject(method="oreVeinsEnabled",at=@At(value="RETURN"),cancellable = true)
    private void customOreVeins(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}

