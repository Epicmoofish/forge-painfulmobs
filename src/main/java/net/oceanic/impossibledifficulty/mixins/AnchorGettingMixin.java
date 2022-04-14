package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RespawnAnchorBlock.class)
public interface AnchorGettingMixin {
    @Invoker("explode")
    public void invokeExplode(BlockState p_55891_, Level p_55892_, final BlockPos p_55893_);
}


