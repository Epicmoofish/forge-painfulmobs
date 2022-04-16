package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.oceanic.impossibledifficulty.interfaces.PlayerEatingGettingMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Item.class)

public class PreventEatingEditingMixin{
    @Redirect(method="use",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean injectingEat(Player instance, boolean p_36392_){
        Item item = (Item)(Object)this;
        boolean normallyEdible = instance.canEat(item.getFoodProperties().canAlwaysEat());
        String itemName = item.getRegistryName().toString();
        boolean actuallyEdible = !((PlayerEatingGettingMixin)instance).getLastFood().equals(itemName) && normallyEdible;
        return actuallyEdible;
    }
}

