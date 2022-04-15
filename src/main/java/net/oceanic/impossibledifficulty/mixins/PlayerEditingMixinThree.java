package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.client.player.LocalPlayer;
import net.oceanic.impossibledifficulty.interfaces.PlayerGettingMixin;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(LocalPlayer.class)
public class PlayerEditingMixinThree implements PlayerGettingMixin {
    boolean isAquatic=false;

    @Override
    public boolean getIsAquatic() {
        return this.isAquatic;
    }

    @Override
    public void setIsAquatic(boolean isAquatic) {
        this.isAquatic=isAquatic;
    }
}

