package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.monster.Silverfish;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import net.oceanic.impossibledifficulty.goals.SilverfishWakeupGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Silverfish.class)
public class SilverfishEditingMixin {
    public SilverfishWakeupGoal friendGoalTwo;
    @Inject(method="registerGoals",at=@At("HEAD"))
    protected void registerInjectedGoals(CallbackInfo ci) {
        if (ImpossibleDifficultyMod.getShouldModify(((Silverfish)(Object)this).getLevel())) {
            this.friendGoalTwo= new SilverfishWakeupGoal(((Silverfish) (Object) this));
            ((Silverfish) (Object) this).goalSelector.addGoal(3,this.friendGoalTwo);
        }
    }
    @Inject(method="hurt",at=@At("HEAD"))
    protected void injectHurt(DamageSource p_33527_, float p_33528_, CallbackInfoReturnable<Boolean> cir) {
        if (ImpossibleDifficultyMod.getShouldModify(((Silverfish)(Object)this).getLevel())) {
            if (((Silverfish) (Object) this).isInvulnerableTo(p_33527_)) {
            } else {
                if ((p_33527_ instanceof EntityDamageSource || p_33527_ == DamageSource.MAGIC) && this.friendGoalTwo != null) {
                    this.friendGoalTwo.notifyHurt();
                }

            }
        }
    }


}

