package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ghast.class)
public interface GhastGettingMixin {
    @Accessor("explosionPower")
    public void setExplosionPower(int explosionPower);
}


