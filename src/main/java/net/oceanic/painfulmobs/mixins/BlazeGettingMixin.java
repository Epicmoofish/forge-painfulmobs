package net.oceanic.painfulmobs.mixins;

import com.google.common.eventbus.AllowConcurrentEvents;
import net.minecraft.world.entity.monster.Blaze;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(targets="net.minecraft.world.entity.monster.Blaze")

public interface BlazeGettingMixin {
    @Invoker("setCharged")
    public void invokeSetCharged(boolean charged);
}

