package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.oceanic.painfulmobs.PainfulMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EnderMan.class)
public class EndermanEditingMixin {
    @Inject(method="registerGoals",at=@At("HEAD"))
    protected void registerInjectedGoals(CallbackInfo ci) {
        if (PainfulMobsMod.getShouldModify(((EnderMan)(Object)this).getLevel())) {
            ((EnderMan) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>((EnderMan) (Object) this, Player.class, true));
        }
    }


}

