package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ghast.class)
public interface GhastGettingMixin {
    @Accessor("explosionPower")
    public void setExplosionPower(int explosionPower);
}


