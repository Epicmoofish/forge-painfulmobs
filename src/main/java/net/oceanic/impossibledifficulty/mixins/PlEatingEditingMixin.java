package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.oceanic.impossibledifficulty.interfaces.PlayerEatingGettingMixin;
import net.oceanic.impossibledifficulty.packet.ClientAirPacket;
import net.oceanic.impossibledifficulty.packet.ClientUpdateLastFoodPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Player.class)

public class PlEatingEditingMixin implements PlayerEatingGettingMixin {
    String lastFood="minecraft:non_existent";
    @Inject(method="eat",at=@At(value="HEAD"))
    private void injectingEat(Level p_36185_, ItemStack p_36186_, CallbackInfoReturnable<ItemStack> cir){
        ((PlayerEatingGettingMixin)(Object)this).setLastFood(p_36186_.getItem().getRegistryName().toString());
    }
    @Inject(method="readAdditionalSaveData",at = @At("TAIL"))
    private void injectingSaveData(CompoundTag p_36215_, CallbackInfo ci){
        ((PlayerEatingGettingMixin)(Object)this).setLastFood(p_36215_.getString("LastFood"));
    }
    @Inject(method="addAdditionalSaveData",at = @At("TAIL"))
    private void injectingWritingSaveData(CompoundTag p_36215_, CallbackInfo ci){
        p_36215_.putString("LastFood",((PlayerEatingGettingMixin)(Object)this).getLastFood());
    }
    @Override
    public String getLastFood() {
        return this.lastFood;
    }

    @Override
    public void setLastFood(String lastFood) {
        this.lastFood=lastFood;
    }
}

