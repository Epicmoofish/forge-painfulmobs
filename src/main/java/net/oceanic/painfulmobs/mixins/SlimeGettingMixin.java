package net.oceanic.painfulmobs.mixins;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Slime.class)
public interface SlimeGettingMixin {
    @Invoker("setSize")
    public void invokeSetSize(int p_33594_, boolean p_33595_);
}


