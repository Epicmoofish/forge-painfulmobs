package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(LivingEntity.class)
public interface LivingGettingMixin {
    @Invoker("hurtCurrentlyUsedShield")
    public void invokeHurtShield(float p_33594_);
    @Invoker("blockUsingShield")
    public void invokeUseShield(LivingEntity living);
}


