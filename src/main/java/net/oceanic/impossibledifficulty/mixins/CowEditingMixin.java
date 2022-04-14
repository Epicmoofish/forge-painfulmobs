package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//Cow
//Dolphin
//Fox
//IronGolem
//Bee
//Panda
//Parrot
//Pig
//PolarBear
//Rabbbit
//Sheep
//SnowGolem
//Squid
//Turtle
//Wolf
//AbstractFish
//@Mixin({Cat.class, Cow.class,Dolphin.class,Fox.class,IronGolem.class,Bee.class,Panda.class,Parrot.class,Pig.class,PolarBear.class,Rabbit.class,Sheep.class,SnowGolem.class,Squid.class,Turtle.class,Wolf.class})
@Mixin(Cow.class)
public class CowEditingMixin {
        @Inject(method="registerGoals",at=@At("HEAD"))
        protected void registerInjectedGoals(CallbackInfo ci) {
                ((Mob) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(((Mob) (Object) this), Player.class, true));
                ((Mob) (Object) this).goalSelector.addGoal(6, new MeleeAttackGoal(((PathfinderMob) (Object) this), (double)1.2F, true));
        }
        @Inject(method="createAttributes",at=@At("RETURN"),cancellable = true)
        private static void registerAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
                if (cir.getReturnValue().hasAttribute(Attributes.ATTACK_DAMAGE)){
                        cir.setReturnValue(cir.getReturnValue());
                } else{
                cir.setReturnValue(cir.getReturnValue().add(Attributes.ATTACK_DAMAGE,4));
                }
        }
}

