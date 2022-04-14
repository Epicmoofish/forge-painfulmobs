package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShovelItem.class)
public class PathEditingMixin {
        @Redirect(method="useOn",at=@At(value="INVOKE",target="Lnet/minecraft/world/level/block/state/BlockState;getToolModifiedState(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraftforge/common/ToolAction;)Lnet/minecraft/world/level/block/state/BlockState;"))
        protected BlockState registerInjectedGoals(BlockState instance, Level level, BlockPos blockPos, Player player, ItemStack itemStack, ToolAction toolAction) {
                if (instance.getBlock() instanceof CampfireBlock && instance.getValue(CampfireBlock.LIT)){
                        return null;
                }
                return Blocks.DIRT_PATH.defaultBlockState();
        }
}

