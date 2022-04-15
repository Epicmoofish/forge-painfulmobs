package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.oceanic.impossibledifficulty.ImpossibleDifficultyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(LivingEntity.class)
public class PlayerEditingMixin {
@Inject(method="canBreatheUnderwater",at=@At(value="RETURN"),cancellable = true)
private void injectedUnderwater(CallbackInfoReturnable<Boolean> cir){
    LivingEntity ent = (LivingEntity) (Object) this;
    if (ent instanceof Player){
        cir.setReturnValue(true);
    }
}
    @Redirect(method="baseTick",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/LivingEntity;setAirSupply(I)V"))
    private void injectedAirSupply(LivingEntity instance, int i) {

    }
    @Inject(method="baseTick",at=@At(value="HEAD"))
    private void injectedTick(CallbackInfo ci) {
        LivingEntity ent = (LivingEntity) (Object) this;
        if (ent instanceof Player) {
            Random rand=new Random();
            if (!ent.isEyeInFluid(FluidTags.WATER) || ent.level.getBlockState(new BlockPos(ent.getX(), ent.getEyeY(), ent.getZ())).is(Blocks.BUBBLE_COLUMN)) {
                boolean flag1 = !MobEffectUtil.hasWaterBreathing(ent) && ( !((Player)ent).getAbilities().invulnerable);
                if (flag1) {
                    int i32 = EnchantmentHelper.getRespiration(ent);
                    ent.setAirSupply(i32 > 0 && rand.nextInt(i32 + 1) > 0 ? ent.getAirSupply() : ent.getAirSupply() - 1);
                    if (ent.getAirSupply() == -20) {
                        ent.setAirSupply(0);
                        Vec3 vec3 = ent.getDeltaMovement();

                        for(int i = 0; i < 8; ++i) {
                            double d2 = rand.nextDouble() - rand.nextDouble();
                            double d3 = rand.nextDouble() - rand.nextDouble();
                            double d4 = rand.nextDouble() - rand.nextDouble();
                            ent.level.addParticle(ParticleTypes.BUBBLE, ent.getX() + d2, ent.getY() + d3, ent.getZ() + d4, vec3.x, vec3.y, vec3.z);
                        }

                        ent.hurt(DamageSource.DROWN, 2.0F);
                    }
                }
            } else if (ent.getAirSupply() < ent.getMaxAirSupply()) {
                ent.setAirSupply(Math.min(ent.getAirSupply() + 4, ent.getMaxAirSupply()));
            }
        }
    }

}
