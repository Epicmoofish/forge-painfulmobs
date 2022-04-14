package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Witch.class)

public class WitchEditingMixin {
    @Inject(method="registerGoals",at=@At(value="TAIL"))
    private void customGoals(CallbackInfo ci){
        if (ImpossibleDifficultyMod.getShouldModify(((Witch) (Object) this).getLevel())) {
            ((Witch) (Object) this).goalSelector.removeAllGoals();
            ((Witch) (Object) this).goalSelector.addGoal(1, new FloatGoal(((Witch) (Object) this)));
            ((Witch) (Object) this).goalSelector.addGoal(2, new RangedAttackGoal(((Witch) (Object) this), 1.0D, 1, 10.0F));
            ((Witch) (Object) this).goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(((Witch) (Object) this), 1.0D));
            ((Witch) (Object) this).goalSelector.addGoal(3, new LookAtPlayerGoal(((Witch) (Object) this), Player.class, 8.0F));
            ((Witch) (Object) this).goalSelector.addGoal(3, new RandomLookAroundGoal(((Witch) (Object) this)));
        }
    }
}

