package net.oceanic.impossibledifficulty.interfaces;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


public interface PlayerGettingMixin {
    boolean getIsAquatic();
    void setIsAquatic(boolean isAquatic);
}

