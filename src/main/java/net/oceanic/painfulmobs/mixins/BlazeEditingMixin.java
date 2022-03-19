package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.oceanic.painfulmobs.BlazeAttackingGoal;
import net.oceanic.painfulmobs.PainfulMobsMod;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(targets="net.minecraft.world.entity.monster.Blaze")

public class BlazeEditingMixin {
    @Inject(method="registerGoals",at=@At(value="TAIL"))
    private void customGoals(CallbackInfo ci){
        if (PainfulMobsMod.getShouldModify(((Blaze)(Object)this).getLevel())) {
            ((Blaze) (Object) this).goalSelector.removeAllGoals();
            ((Blaze) (Object) this).goalSelector.addGoal(4, new BlazeAttackingGoal(((Blaze) (Object) this)));
            ((Blaze) (Object) this).goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(((Blaze) (Object) this), 1.0D));
            ((Blaze) (Object) this).goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(((Blaze) (Object) this), 1.0D, 0.0F));
            ((Blaze) (Object) this).goalSelector.addGoal(8, new LookAtPlayerGoal(((Blaze) (Object) this), Player.class, 8.0F));
            ((Blaze) (Object) this).goalSelector.addGoal(8, new RandomLookAroundGoal(((Blaze) (Object) this)));
        }
    }
}

