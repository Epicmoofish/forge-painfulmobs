package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Random;
import java.util.function.BiConsumer;


@Mixin(TreeFeature.class)
public interface TreeGettingMixin {
    @Invoker("doPlace")
    public boolean invokeDoPlace(WorldGenLevel p_160511_, Random p_160512_, BlockPos p_160513_, BiConsumer<BlockPos, BlockState> p_160514_, BiConsumer<BlockPos, BlockState> p_160515_, TreeConfiguration p_160516_);
}


