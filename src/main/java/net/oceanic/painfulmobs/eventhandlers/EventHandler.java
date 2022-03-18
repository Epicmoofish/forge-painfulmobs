package net.oceanic.painfulmobs.eventhandlers;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.logging.Level;
import java.util.logging.Logger;
public class EventHandler {
    @SubscribeEvent
    public void updateEvent(LivingEvent.LivingUpdateEvent event) {
//        System.out.println("Hi from spawn event");
        if (event.getEntityLiving() instanceof Zombie){
            Zombie zombie=((Zombie) event.getEntityLiving());
            if (!zombie.canBreakDoors()){
                zombie.setCanBreakDoors(true);
            }
            if (!zombie.isBaby()){
                zombie.setBaby(true);
            }
            if (!zombie.canPickUpLoot()){
                zombie.setCanPickUpLoot(true);
            }
        }
        if (event.getEntityLiving() instanceof AbstractSkeleton){
            AbstractSkeleton skeleton=((AbstractSkeleton) event.getEntityLiving());
            if (!skeleton.canPickUpLoot()){
                skeleton.setCanPickUpLoot(true);
            }
        }
    }
    @SubscribeEvent
    public void hurtEvent(LivingHurtEvent event) {
        if (event.getSource()!=null && event.getSource().getEntity()!=null &&event.getSource().getEntity() instanceof Zombie){
            event.setAmount(event.getAmount()*5);
        }
        if (event.getSource()!=null && event.getSource().getEntity()!=null &&event.getSource().getEntity() instanceof AbstractSkeleton){
            event.setAmount(event.getAmount()*20);
        }
        if (event.getEntity()!=null &&event.getEntity() instanceof Zombie){
            event.setAmount(event.getAmount()/10);
        }
    }
}
