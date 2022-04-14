package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(PhantomSpawner.class)

public class PhantomEditingMixin {
    @ModifyVariable(method="tick",at=@At(value="STORE"),name="l")
    private int injectedL(int l){
        return 10;
    }
    @ModifyConstant(method="tick",constant = @Constant(intValue = 5))
    private int injectedSkyDarken(int constant){
        return 0;
    }
    @ModifyConstant(method="tick",constant = @Constant(intValue = 72000))
    private int injectedRandomCheck(int rconstant){
        return 0;
    }
//    @ModifyConstant(method="tick",constant = @Constant(intValue = 20),slice = @Slice(
//            from = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"),
//            to = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;")
//    ))
//    private int injectedAbove(int above){
//        return 3;
//    }
//    @ModifyConstant(method="tick",constant = @Constant(intValue = 15))
//    private int injectedAboveRand(int above){
//        return 1;
//    }
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getSeaLevel()I"))
    private int seaLevelRedirect(ServerLevel instance) {
        return Integer.MIN_VALUE;
    }


}

