package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.oceanic.painfulmobs.PainfulMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ZombifiedPiglin.class)
public class ZombiePigmanEditingMixin {
    @Inject(method="addBehaviourGoals",at=@At("HEAD"))
    protected void registerInjectedGoals(CallbackInfo ci) {
        if (PainfulMobsMod.getShouldModify(((ZombifiedPiglin)(Object)this).getLevel())) {
            ((ZombifiedPiglin) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(((ZombifiedPiglin) (Object) this), Player.class, true));
        }
    }


}

