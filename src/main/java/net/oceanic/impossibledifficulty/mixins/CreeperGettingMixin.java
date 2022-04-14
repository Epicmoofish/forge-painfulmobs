package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Creeper.class)
public interface CreeperGettingMixin {
    @Invoker("explodeCreeper")
    public void invokeExplodeCreeper();
}


