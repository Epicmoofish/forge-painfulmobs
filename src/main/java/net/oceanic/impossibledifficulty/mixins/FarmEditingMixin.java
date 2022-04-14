package net.oceanic.impossibledifficulty.mixins;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Mixin(HoeItem.class)
public class FarmEditingMixin {
        @ModifyVariable(method = "useOn", at = @At("STORE"), name = "pair")
        private Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> injected(Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair, UseOnContext p_41341_) {
                BlockPos blockpos = p_41341_.getClickedPos();
                Level level = p_41341_.getLevel();
                Block block = level.getBlockState(blockpos).getBlock();
                Predicate<UseOnContext> isairabove = p_150857_ -> (p_150857_.getClickedFace() != Direction.DOWN && p_150857_.getLevel().getBlockState(p_150857_.getClickedPos().above()).isAir());
                Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> betterpair = Maps.newHashMap(ImmutableMap.of(block, Pair.of(isairabove, HoeItem.changeIntoState(Blocks.FARMLAND.defaultBlockState())))).get(block);
                return betterpair;
        }

}

