package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.oceanic.painfulmobs.PainfulMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Guardian.class)
public class GuardianEditingMixin {
    @Inject(method="getAttackDuration",at=@At("RETURN"),cancellable = true)
    private void replaceAttackDuration(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(5);
    }


}

