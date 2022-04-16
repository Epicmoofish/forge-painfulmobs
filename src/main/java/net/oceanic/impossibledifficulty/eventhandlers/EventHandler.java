package net.oceanic.impossibledifficulty.eventhandlers;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import net.oceanic.impossibledifficulty.mixins.GhastGettingMixin;
import net.oceanic.impossibledifficulty.mixins.LivingGettingMixin;
import net.oceanic.impossibledifficulty.mixins.PlayerGettingMixinActual;
import net.oceanic.impossibledifficulty.mixins.SlimeGettingMixin;

import java.util.Random;

public class EventHandler {
    @SubscribeEvent
    public void spawnEvent(LivingSpawnEvent.SpecialSpawn event){
        if (!event.getEntityLiving().getLevel().isClientSide() && ImpossibleDifficultyMod.getShouldModify(event.getEntityLiving().getLevel())) {

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
//            if (event.getEntityLiving() instanceof Phantom) {
//                Phantom phantom = ((Phantom) event.getEntityLiving());
//                phantom.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, false, false));
//            }
        }
    }
    @SubscribeEvent
    public void updateEvent(LivingEvent.LivingUpdateEvent event) {
//        System.out.println("Hi from spawn event");
        if (event.getEntityLiving().getServer() !=null) {
            if (event.getEntityLiving().getLevel().getDifficulty()!=Difficulty.HARD) {
                event.getEntityLiving().getServer().setDifficulty(Difficulty.HARD, true);
            }
            if (!event.getEntityLiving().getLevel().getLevelData().isDifficultyLocked()) {
                event.getEntityLiving().getServer().setDifficultyLocked(true);
            }
        }
        if (!event.getEntityLiving().getLevel().isClientSide() && ImpossibleDifficultyMod.getShouldModify(event.getEntityLiving().getLevel())) {
            if (event.getEntityLiving() instanceof Player && new Random().nextInt(1200)==0){
                if (event.getEntityLiving() !=null && event.getEntityLiving() instanceof Player){
                    (event.getEntityLiving()).hurt(DamageSource.FALL,0.01f);
                    (event.getEntityLiving()).addEffect(new MobEffectInstance(MobEffects.BLINDNESS,5));
                    (event.getEntityLiving()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,5,9));
                    ((PlayerGettingMixinActual) (LivingEntity) event.getEntityLiving()).invokeDropAllDeathLoot(DamageSource.FALL);
                    ((Player)event.getEntityLiving()).sendMessage((new TextComponent("You tripped.").withStyle(ChatFormatting.RED)), Util.NIL_UUID);
                }
            }
            if (event.getEntityLiving() instanceof Ghast) {
                Ghast ghast = ((Ghast) event.getEntityLiving());
                if (ghast.getExplosionPower()<5){
                    ((GhastGettingMixin)ghast).setExplosionPower(ghast.getExplosionPower()*5);
                }
            }
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
            if (event.getEntityLiving() instanceof Evoker) {
                Evoker evoker = ((Evoker) event.getEntityLiving());
                if (evoker.getOffhandItem().isEmpty() && !evoker.getTags().contains("GivenTotemAlready")) {
                    evoker.addTag("GivenTotemAlready");
                    evoker.setItemInHand(InteractionHand.OFF_HAND,new ItemStack(Items.TOTEM_OF_UNDYING,1));
                }
            }
//            if (event.getEntityLiving() instanceof Phantom) {
//                Phantom phantom = ((Phantom) event.getEntityLiving());
//                phantom.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, false, false));
//            }
        }
    }
    @SubscribeEvent
    public void shieldEvent(ShieldBlockEvent event) {
        if ( event.getEntityLiving()!=null && ImpossibleDifficultyMod.getShouldModify(event.getEntityLiving().getLevel())) {
            if (event.getDamageSource() != null && event.getDamageSource().getEntity() instanceof Vindicator) {
                ((LivingGettingMixin) event.getEntityLiving()).invokeUseShield((LivingEntity) event.getDamageSource().getEntity());
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void hurtEvent(LivingHurtEvent event) {
        if ( event.getEntityLiving()!=null && ImpossibleDifficultyMod.getShouldModify(event.getEntityLiving().getLevel())) {

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
            if (event.getSource() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof WitherSkeleton && event.getEntityLiving() != null) {

                if (event.getEntityLiving().isAffectedByPotions() && event.getAmount() > 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 9));
                }
            }
            if (event.getSource() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Shulker && event.getEntityLiving() != null) {

                if (event.getEntityLiving().isAffectedByPotions() && event.getAmount() > 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 9));
                }
            }
            if (!event.getEntityLiving().getLevel().isClientSide) {
                if (event.getEntityLiving() != null && event.getEntityLiving() instanceof Player && new Random().nextInt(10)==0) {
                    ((PlayerGettingMixinActual) (LivingEntity) event.getEntityLiving()).invokeDropAllDeathLoot(event.getSource());
                }
            }
        }
    }
    @SubscribeEvent
    public void knockBackEvent(LivingKnockBackEvent event) {
        if (event.getEntityLiving() !=null && event.getEntityLiving() instanceof PiglinBrute){
            event.setCanceled(true);
        }
    }
}
