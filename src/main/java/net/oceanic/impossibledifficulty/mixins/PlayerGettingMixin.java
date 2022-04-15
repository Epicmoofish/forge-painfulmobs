package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Player.class)
public interface PlayerGettingMixin {
    @Accessor("wasUnderwater")
    public void setWasUnderwater(boolean wasUnderwater);
    @Accessor
    public boolean getWasUnderwater();
}


