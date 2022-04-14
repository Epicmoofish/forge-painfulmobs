package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(RespawnAnchorBlock.class)

public class AnchorEditingMixin {
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RespawnAnchorBlock;explode(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
    private void explodeAnchor(RespawnAnchorBlock instance, BlockState p_55891_, Level p_55892_, BlockPos p_55893_) {
    }
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"))
    private Comparable explodeAnchor(BlockState instance, Property property) {
        return 4;
    }
//    instance.explode(p_46526_, p_46527_, p_46528_, p_46529_, p_46530_, p_46531_, p_46532_, p_46533_, p_46534_);
@Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RespawnAnchorBlock;explode(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
private void actuallyExplode(BlockState p_55874_, Level p_55875_, BlockPos p_55876_, Player p_55877_, InteractionHand p_55878_, BlockHitResult p_55879_, CallbackInfoReturnable<InteractionResult> cir) {
    p_55875_.setBlock(p_55876_, Blocks.AIR.defaultBlockState(), 3);
    ((AnchorGettingMixin) (RespawnAnchorBlock) (Object) (this)).invokeExplode(p_55874_, p_55875_, new BlockPos(p_55877_.getX(),p_55877_.getY(),p_55877_.getZ()));

}
    @Inject(method = "canSetSpawn", at = @At(value = "RETURN"),cancellable = true)
    private static void canSetSpawn(Level p_49489_, CallbackInfoReturnable<Boolean> cir) {

        cir.setReturnValue(false);
    }
    @Inject(method = "canBeCharged", at = @At(value = "RETURN"),cancellable = true)
    private static void canCharge(BlockState p_55895_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}

