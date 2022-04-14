package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//Cat
//Cow
//Dolphin
//Fox
//IronGolem
//Bee
//Panda
//Parrot
//Pig
//PolarBear
//Rabbit
//Sheep
//SnowGolem
//Squid
//Turtle
//Wolf
//AbstractFish
//@Mixin({Cat.class, Cow.class,Dolphin.class,Fox.class,IronGolem.class,Bee.class,Panda.class,Parrot.class,Pig.class,PolarBear.class,Rabbit.class,Sheep.class,SnowGolem.class,Squid.class,Turtle.class,Wolf.class})
@Mixin(PolarBear.class)
public class PolarBearEditingMixin {
        @Inject(method="registerGoals",at=@At("HEAD"))
        protected void registerInjectedGoals(CallbackInfo ci) {
                ((Mob) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(((Mob) (Object) this), Player.class, true));

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

