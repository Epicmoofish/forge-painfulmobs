package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.client.gui.Gui;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(Gui.class)
public class PlayerEditingMixinTwo {
@Redirect(method="renderPlayerHealth",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
private boolean injectedUnderwater(Player instance, TagKey tagKey){
//    Player player = (Player)(Object)this;
//            ((PlayerGettingMixin)player).setWasUnderwater(!player.isEyeInFluid(FluidTags.WATER));
//        cir.setReturnValue(((PlayerGettingMixin)player).getWasUnderwater());
    return !instance.isEyeInFluid(tagKey);
}

}

