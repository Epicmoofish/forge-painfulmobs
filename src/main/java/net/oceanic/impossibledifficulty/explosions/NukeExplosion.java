package net.oceanic.impossibledifficulty.explosions;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.oceanic.impossibledifficulty.mixins.CreeperGettingMixin;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NukeExplosion extends Explosion {

    private float radius;
    private double x;
    private double y;
    private double z;
    private Level level;
    private Entity source;
    private BlockInteraction blockInteraction;
    private final Random random = new Random();
    private final boolean fire;
    private final List<BlockPos> toBlow = Lists.newArrayList();
    public NukeExplosion(Level p_151471_, @javax.annotation.Nullable Entity p_151472_, double p_151473_, double p_151474_, double p_151475_, float p_151476_) {
        this(p_151471_, p_151472_, p_151473_, p_151474_, p_151475_, p_151476_, false, Explosion.BlockInteraction.DESTROY);
    }

    public NukeExplosion(Level p_46024_, @javax.annotation.Nullable Entity p_46025_, double p_46026_, double p_46027_, double p_46028_, float p_46029_, List<BlockPos> p_46030_) {
        this(p_46024_, p_46025_, p_46026_, p_46027_, p_46028_, p_46029_, false, Explosion.BlockInteraction.DESTROY, p_46030_);
    }

    public NukeExplosion(Level p_46041_, @javax.annotation.Nullable Entity p_46042_, double p_46043_, double p_46044_, double p_46045_, float p_46046_, boolean p_46047_, Explosion.BlockInteraction p_46048_, List<BlockPos> p_46049_) {
        this(p_46041_, p_46042_, p_46043_, p_46044_, p_46045_, p_46046_, p_46047_, p_46048_);
        this.toBlow.addAll(p_46049_);
    }

    public NukeExplosion(Level p_46032_, @javax.annotation.Nullable Entity p_46033_, double p_46034_, double p_46035_, double p_46036_, float p_46037_, boolean p_46038_, Explosion.BlockInteraction p_46039_) {
        this(p_46032_, p_46033_, (DamageSource)null, (ExplosionDamageCalculator)null, p_46034_, p_46035_, p_46036_, p_46037_, p_46038_, p_46039_);
    }
    public NukeExplosion(Level p_46051_, @Nullable Entity p_46052_, @Nullable DamageSource p_46053_, @Nullable ExplosionDamageCalculator p_46054_, double x, double y, double z, float explosionRadius, boolean p_46059_, BlockInteraction p_46060_) {
        super(p_46051_, p_46052_, p_46053_, p_46054_, x, y, z, explosionRadius, p_46059_, p_46060_);
        this.source=p_46052_;
        this.level = p_46051_;
        this.radius=explosionRadius;
        this.x=x;
        this.y=y;
        this.z=z;
        this.blockInteraction=p_46060_;
        this.fire = p_46059_;
    }

    @Override
    public void explode() {
        this.level.gameEvent(this.source, GameEvent.EXPLODE, new BlockPos(this.x, this.y, this.z));
        int actualradius = (int)(this.radius*1);
        if (blockInteraction != BlockInteraction.NONE&&!this.source.isInWaterOrBubble()) {
            for (int xCoord = -actualradius; xCoord < actualradius + 1; xCoord++) {
                for (int yCoord = -actualradius; yCoord < actualradius + 1; yCoord++) {
                    for (int zCoord = -actualradius; zCoord < actualradius + 1; zCoord++) {
                        BlockPos posToExplode = new BlockPos((int) x + xCoord, (int) y + yCoord, (int) z + zCoord);
                        if (xCoord * xCoord + yCoord * yCoord + zCoord * zCoord <= actualradius * actualradius && level.getBlockState(posToExplode) != null && level.getBlockState(posToExplode).getDestroySpeed(level, posToExplode) != -1.0F) {
                            if (level.getBlockState(posToExplode).getBlock() instanceof TntBlock){
                                ((TntBlock)level.getBlockState(posToExplode).getBlock()).onBlockExploded(level.getBlockState(posToExplode),level,posToExplode,this);
                                this.toBlow.add(posToExplode);
                            } else {
                                level.setBlockAndUpdate(posToExplode, Blocks.AIR.defaultBlockState());
                                this.toBlow.add(posToExplode);
                            }
                        }

                    }
                }
            }
        }

        int k1 = Mth.floor(this.x - (double)actualradius - 10.0D);
        int l1 = Mth.floor(this.x + (double)actualradius + 10.0D);
        int i2 = Mth.floor(this.y - (double)actualradius - 10.0D);
        int i1 = Mth.floor(this.y + (double)actualradius + 10.0D);
        int j2 = Mth.floor(this.z - (double)actualradius - 10.0D);
        int j1 = Mth.floor(this.z + (double)actualradius + 10.0D);
        List<Entity> list = this.level.getEntities(this.source, new AABB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        this.source.addTag("immuneToChain");
        for (Entity entity : list){
            double displaceX=entity.getX()-this.x;
            double displaceY=entity.getY()-this.y;
            double displaceZ=entity.getZ()-this.z;
            double distance=displaceX*displaceX+displaceY*displaceY+displaceZ*displaceZ;

            if (distance<=((double)actualradius+0.3*(double)actualradius)*((double)actualradius+0.3*(double)actualradius)){
                if (entity instanceof Creeper && !entity.is(this.source) && !entity.getTags().contains("immuneToChain")){
                    ((CreeperGettingMixin)(Creeper)entity).invokeExplodeCreeper();
                } else if (!(entity instanceof EnderDragonPart || entity instanceof EnderDragon)) {
                    double damageDealt = 30 * (((double) actualradius + 0.3 * (double) actualradius) * ((double) actualradius + 0.3 * (double) actualradius) - distance) + 10;
                    entity.hurt(this.getDamageSource(), (float) damageDealt);
                }
            }
        }
    }
    public void finalizeExplosion(boolean p_46076_) {
        if (this.level.isClientSide) {
            this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
        boolean flag = this.blockInteraction != Explosion.BlockInteraction.NONE;
        if (this.level.isClientSide) {
            if (p_46076_) {
                if (!(this.radius < 2.0F) && flag) {
                    this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
                } else {
                    this.level.addParticle(ParticleTypes.EXPLOSION, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
                }
            }
        } else {
            if (flag) {
                ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList<>();
                Collections.shuffle(this.toBlow, this.level.random);

                for (BlockPos blockpos : this.toBlow) {
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    if (!blockstate.isAir()) {
                        BlockPos blockpos1 = blockpos.immutable();
                        this.level.getProfiler().push("explosion_blocks");
                        if (blockstate.canDropFromExplosion(this.level, blockpos, this) && this.level instanceof ServerLevel) {
                            BlockEntity blockentity = blockstate.hasBlockEntity() ? this.level.getBlockEntity(blockpos) : null;
                            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel) this.level)).withRandom(this.level.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockpos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockentity).withOptionalParameter(LootContextParams.THIS_ENTITY, this.source);
                            if (this.blockInteraction == Explosion.BlockInteraction.DESTROY) {
                                lootcontext$builder.withParameter(LootContextParams.EXPLOSION_RADIUS, this.radius);
                            }


                        }

                        this.level.getProfiler().pop();
                    }
                }

                for (Pair<ItemStack, BlockPos> pair : objectarraylist) {
                    Block.popResource(this.level, pair.getSecond(), pair.getFirst());
                }
            }

            if (this.fire) {
                for (BlockPos blockpos2 : this.toBlow) {
                    if (this.random.nextInt(3) == 0 && this.level.getBlockState(blockpos2).isAir() && this.level.getBlockState(blockpos2.below()).isSolidRender(this.level, blockpos2.below())) {
                        this.level.setBlockAndUpdate(blockpos2, BaseFireBlock.getState(this.level, blockpos2));
                    }
                }
            }

        }
    }
}
