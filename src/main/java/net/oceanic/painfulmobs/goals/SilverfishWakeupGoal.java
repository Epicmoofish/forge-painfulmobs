package net.oceanic.painfulmobs.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class SilverfishWakeupGoal extends Goal {
    private final Silverfish silverfish;
    private int lookForFriends;

    public SilverfishWakeupGoal(Silverfish p_33565_) {
        this.silverfish = p_33565_;
    }

    public void notifyHurt() {
        if (this.lookForFriends == 0) {
            this.lookForFriends = this.adjustedTickDelay(20);
        }

    }

    public boolean canUse() {
        return this.lookForFriends > 0;
    }

    public void tick() {
        --this.lookForFriends;
        if (this.lookForFriends <= 0) {
            Level level = this.silverfish.level;
            Random random = this.silverfish.getRandom();
            BlockPos blockpos = this.silverfish.blockPosition();

            for (int i = 0; i <= 5 && i >= -5; i = (i <= 0 ? 1 : 0) - i) {
                for (int j = 0; j <= 10 && j >= -10; j = (j <= 0 ? 1 : 0) - j) {
                    for (int k = 0; k <= 10 && k >= -10; k = (k <= 0 ? 1 : 0) - k) {
                        BlockPos blockpos1 = blockpos.offset(j, i, k);
                        BlockState blockstate = level.getBlockState(blockpos1);
                        Block block = blockstate.getBlock();
                        if (block== Blocks.STONE) {
                            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level, this.silverfish)) {
                                level.destroyBlock(blockpos1, true, this.silverfish);
                                Silverfish silverfish = EntityType.SILVERFISH.create(level);
                                silverfish.moveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY(), (double)blockpos1.getZ() + 0.5D, 0.0F, 0.0F);
                                level.addFreshEntity(silverfish);
                                silverfish.spawnAnim();
                            } else {
                                level.setBlock(blockpos1, ((InfestedBlock) block).hostStateByInfested(level.getBlockState(blockpos1)), 3);
                            }

                            if (random.nextBoolean()) {
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
}
