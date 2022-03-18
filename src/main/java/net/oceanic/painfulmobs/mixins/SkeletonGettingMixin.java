package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RangedBowAttackGoal.class)
public interface SkeletonGettingMixin<T extends net.minecraft.world.entity.Mob & RangedAttackMob> {
    @Accessor()
    public int getAttackTime();
    @Accessor()
    public int getAttackIntervalMin();
    @Accessor("attackTime")
    public void setAttackTime(int attackTime);
    @Accessor()
    public T getMob();
}


