package net.oceanic.painfulmobs.eventhandlers;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.oceanic.painfulmobs.PainfulMobsMod;
import net.oceanic.painfulmobs.mixins.SlimeGettingMixin;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EventHandler {
    @SubscribeEvent
    public void updateEvent(LivingEvent.LivingUpdateEvent event) {
//        System.out.println("Hi from spawn event");
        if (!event.getEntityLiving().getLevel().isClientSide() && PainfulMobsMod.getShouldModify(event.getEntityLiving().getLevel())) {
            if (event.getEntityLiving() instanceof Zombie) {
                Zombie zombie = ((Zombie) event.getEntityLiving());
                if (!zombie.canBreakDoors()) {
                    zombie.setCanBreakDoors(true);
                }
                if (!zombie.isBaby()) {
                    zombie.setBaby(true);
                }
                if (!zombie.canPickUpLoot()) {
                    zombie.setCanPickUpLoot(true);
                }
            }
            if (event.getEntityLiving() instanceof Slime) {
Slime slime = (Slime)event.getEntityLiving();
if (slime.getSize()<4&&slime.isAlive()) {
if (slime.getLevel().getGameTime()%200 ==0){
    ((SlimeGettingMixin) slime).invokeSetSize(Math.min(slime.getSize() *2,4), true);
}
}
            }
            if (event.getEntityLiving() instanceof AbstractSkeleton) {
                AbstractSkeleton skeleton = ((AbstractSkeleton) event.getEntityLiving());
                if (!skeleton.canPickUpLoot()) {
                    skeleton.setCanPickUpLoot(true);
                }
            }
            if (event.getEntityLiving() instanceof Spider) {
                Spider spider = ((Spider) event.getEntityLiving());
                spider.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100, 0, false, false));
                spider.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0, false, false));
                spider.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false));
                spider.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, false));
            }
        }
    }
//    @SubscribeEvent
    public void deathEvent(LivingDeathEvent event) {
        if (event.getEntityLiving() != null && PainfulMobsMod.getShouldModify(event.getEntityLiving().getLevel())) {
            if (event.getSource() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Guardian && event.getEntityLiving() != null && !event.getSource().isExplosion()) {
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void hurtEvent(LivingHurtEvent event) {
        if ( event.getEntityLiving()!=null && PainfulMobsMod.getShouldModify(event.getEntityLiving().getLevel())) {

            if (event.getSource() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Spider && event.getEntityLiving() != null) {
                if (event.getEntityLiving().isAffectedByPotions() && event.getAmount() > 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                }
            }
            if (event.getSource() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof EnderMan && event.getEntityLiving() != null) {

                if (event.getEntityLiving().isAffectedByPotions() && event.getAmount() > 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
                }
            }
        }
    }
}
