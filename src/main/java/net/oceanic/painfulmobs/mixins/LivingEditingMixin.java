package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.Explosion;
import net.oceanic.painfulmobs.PainfulMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(LivingEntity.class)
public abstract class LivingEditingMixin {
    @Shadow protected abstract void hurtCurrentlyUsedShield(float p_21316_);

    @ModifyVariable(method="hurt",at=@At(value="HEAD"))
    private float changeDamage(float p_21017_, DamageSource p_21016_) {
        LivingEntity target=(LivingEntity)(Object)this;
        float damage = p_21017_;
        if ( target!=null && PainfulMobsMod.getShouldModify(target.getLevel())) {
            if (target instanceof Guardian && target != null &&p_21016_.isExplosion()){
                damage=0;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof Guardian && target != null &&!p_21016_.isExplosion() ) {
                if (p_21016_ instanceof EntityDamageSource) {
                    if (!((EntityDamageSource)p_21016_).isThorns()) {
                        damage = 0;
                        Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(target.getLevel(), p_21016_.getEntity()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
                        target.getLevel().explode(p_21016_.getEntity(), target.getX(), target.getY(), target.getZ(), 3, explosion$blockinteraction);
                    }
                } else {
                    damage = 0;
                    Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(target.getLevel(), p_21016_.getEntity()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
                    target.getLevel().explode(p_21016_.getEntity(), target.getX(), target.getY(), target.getZ(), 3, explosion$blockinteraction);

                }
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof Zombie) {
                damage = damage * 5;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof AbstractSkeleton) {
                damage = damage * 20;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof Spider && target != null) {
                damage = damage * 3;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof PiglinBrute && target != null) {
                damage = damage * 10;
            } else if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof Piglin && target != null){
                damage = damage * 1.5F;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof EnderMan && target != null) {
                damage = damage * 5;
            }
            if (p_21016_ != null && p_21016_.getEntity() != null && p_21016_.getEntity() instanceof Slime && target != null) {
                damage = damage * 2;
            }
            if (target != null && target instanceof Zombie) {
                damage = damage / 10;
            }
            if (target != null && target instanceof PiglinBrute) {
                damage = damage / 3;
            }
        }
            return damage;

    }


}

