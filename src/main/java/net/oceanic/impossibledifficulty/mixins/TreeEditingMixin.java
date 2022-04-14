package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;
import java.util.function.BiConsumer;


@Mixin(TreeFeature.class)

public class TreeEditingMixin {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/TreeFeature;doPlace(Lnet/minecraft/world/level/WorldGenLevel;Ljava/util/Random;Lnet/minecraft/core/BlockPos;Ljava/util/function/BiConsumer;Ljava/util/function/BiConsumer;Lnet/minecraft/world/level/levelgen/feature/configurations/TreeConfiguration;)Z"))
    private boolean customTrees(TreeFeature instance, WorldGenLevel p_160511_, Random p_160512_, BlockPos p_160513_, BiConsumer<BlockPos, BlockState> p_160514_, BiConsumer<BlockPos, BlockState> p_160515_, TreeConfiguration p_160516_) {
        if (p_160512_.nextInt(1000)==0){
            return ((TreeGettingMixin)(TreeFeature)(Object)this).invokeDoPlace(p_160511_, p_160512_, p_160513_, p_160514_, p_160515_, p_160516_);
        }
        return false;
    }
}

