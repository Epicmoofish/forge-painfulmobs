package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(RangedBowAttackGoal.class)
public class SkeletonEditingMixin {
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 20)
            ,slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;getTicksUsingItem()I"),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/RangedAttackMob;performRangedAttack(Lnet/minecraft/world/entity/LivingEntity;F)V")
    )
    )
    private int injectedTick(int value){
        if (ImpossibleDifficultyMod.getShouldModify(((SkeletonGettingMixin)(RangedBowAttackGoal)(Object)this).getMob().getLevel())) {
            return 2;
        }
        return value;
    }
    @Redirect(method="tick",at=@At(value="FIELD", target="Lnet/minecraft/world/entity/ai/goal/RangedBowAttackGoal;attackTime:I",opcode= Opcodes.PUTFIELD))
    private void injectedAttackTime(RangedBowAttackGoal instance, int value){
        SkeletonGettingMixin mixined=(SkeletonGettingMixin)instance;
        if (ImpossibleDifficultyMod.getShouldModify(((SkeletonGettingMixin)(RangedBowAttackGoal)(Object)this).getMob().getLevel())) {
            if (value == mixined.getAttackIntervalMin()) {
                mixined.setAttackTime(1);
            } else {
                mixined.setAttackTime(value);
            }
        } else{
            mixined.setAttackTime(value);
        }
    }

}

