package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(LivingEntity.class)
public interface LivingGettingMixin {
    @Invoker("hurtCurrentlyUsedShield")
    public void invokeHurtShield(float p_33594_);
}


