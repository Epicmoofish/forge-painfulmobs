package net.oceanic.impossibledifficulty.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.oceanic.impossibledifficulty.mixins.BlazeGettingMixin;

import java.util.EnumSet;

public class BlazeAttackingGoal extends Goal {
    private final Blaze blaze;
    private int attackStep;
    private int attackTime;
    private int lastSeen;

    public BlazeAttackingGoal(Blaze p_32247_) {
        this.blaze = p_32247_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.blaze.getTarget();
        return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
    }

    public void start() {
        this.attackStep = 0;
    }

    public void stop() {
        ((BlazeGettingMixin)(this.blaze)).invokeSetCharged(false);
        this.lastSeen = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.attackTime;
        LivingEntity livingentity = this.blaze.getTarget();
        if (livingentity != null) {
            boolean flag = this.blaze.getSensing().hasLineOfSight(livingentity);
            if (flag) {
                this.lastSeen = 0;
            } else {
                ++this.lastSeen;
            }

            double d0 = this.blaze.distanceToSqr(livingentity);
            if (d0 < 4.0D) {
                if (!flag) {
                    return;
                }

                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.blaze.doHurtTarget(livingentity);
                }

                this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                double d1 = livingentity.getX() - this.blaze.getX();
                double d2 = livingentity.getY(0.5D) - this.blaze.getY(0.5D);
                double d3 = livingentity.getZ() - this.blaze.getZ();
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 1;
                        ((BlazeGettingMixin)(this.blaze)).invokeSetCharged(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 3;
                    } else {
                        this.attackTime = 1;
                        this.attackStep = 0;
                        ((BlazeGettingMixin)(this.blaze)).invokeSetCharged(false);
                    }

                    if (this.attackStep > 1) {
                        double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                        if (!this.blaze.isSilent()) {
                            this.blaze.level.levelEvent((Player)null, 1018, this.blaze.blockPosition(), 0);
                        }

                        for(int i = 0; i < 1; ++i) {
                            SmallFireball smallfireball = new SmallFireball(this.blaze.level, this.blaze, d1 + this.blaze.getRandom().nextGaussian() * d4, d2, d3 + this.blaze.getRandom().nextGaussian() * d4);
                            smallfireball.setPos(smallfireball.getX(), this.blaze.getY(0.5D) + 0.5D, smallfireball.getZ());
                            this.blaze.level.addFreshEntity(smallfireball);
                        }
                    }
                }

                this.blaze.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
            } else if (this.lastSeen < 5) {
                this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
            }

            super.tick();
        }
    }

    private double getFollowDistance() {
        return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}