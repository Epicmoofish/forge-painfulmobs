package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ForgeIngameGui.class)
public abstract class PlayerEditingMixinTwo {
@Redirect(method="renderAir",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
private boolean injectedUnderwater(Player instance, TagKey tagKey){
    return !instance.isEyeInFluid(tagKey);
}
//    @Inject(method="render",at=@At("HEAD"))
//    private void injectTick(CallbackInfo ci){
//        System.out.println("This worked (render)?");
//    }
//    @Inject(method="tick()V",at=@At("HEAD"))
//    private void injectTtt(CallbackInfo ci){
//        System.out.println("This worked (tick)?");
//    }
}

