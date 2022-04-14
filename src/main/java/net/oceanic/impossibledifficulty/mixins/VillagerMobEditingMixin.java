package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
@Mixin(Mob.class)
public class VillagerMobEditingMixin {
        @Inject(method="registerGoals",at=@At("HEAD"))
        protected void registerInjectedGoals(CallbackInfo ci) {
                if (((Mob) (Object) this) instanceof Villager) {
                        ((Mob) (Object) this).targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(((Mob) (Object) this), Player.class, true));
                        ((Mob) (Object) this).goalSelector.addGoal(6, new MeleeAttackGoal(((PathfinderMob) (Object) this), (double) 1.2F, true));
                }
        }
}

