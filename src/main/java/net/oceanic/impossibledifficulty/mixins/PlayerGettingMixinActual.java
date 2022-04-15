package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface PlayerGettingMixinActual {
    @Invoker("dropAllDeathLoot")
    public void invokeDropAllDeathLoot(DamageSource source);

}

