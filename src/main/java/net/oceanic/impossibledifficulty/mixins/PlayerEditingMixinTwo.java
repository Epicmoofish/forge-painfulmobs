package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.oceanic.impossibledifficulty.interfaces.PlayerGettingMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ForgeIngameGui.class)
public abstract class PlayerEditingMixinTwo {
@Redirect(method="renderAir",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
private boolean injectedUnderwater(Player instance, TagKey tagKey){
        if (instance instanceof LocalPlayer){
                if (((PlayerGettingMixin)(LocalPlayer)instance).getIsAquatic()) {
                        return !instance.isEyeInFluid(tagKey);
                } else {
                        return instance.isEyeInFluid(tagKey);
                }
        }
        return false;
}
}

