package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EnderMan.class)
public class EndermanEditingMixin {
    @Inject(method="registerGoals",at=@At("HEAD"))
    protected void registerInjectedGoals(CallbackInfo ci) {
        if (ImpossibleDifficultyMod.getShouldModify(((EnderMan)(Object)this).getLevel())) {
            ((EnderMan) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>((EnderMan) (Object) this, Player.class, true));
        }
    }


}

