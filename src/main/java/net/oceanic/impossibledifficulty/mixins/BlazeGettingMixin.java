package net.oceanic.impossibledifficulty.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(targets="net.minecraft.world.entity.monster.Blaze")

public interface BlazeGettingMixin {
    @Invoker("setCharged")
    public void invokeSetCharged(boolean charged);
}

