package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(FoodData.class)

public class EatingEditingMixin {
    @Redirect(method = "eat(IF)V", at = @At(value = "INVOKE",target="Ljava/lang/Math;min(II)I"))
    private int injectEat(int a, int b) {
        if (a>b){
            return a%b;
        } else {
            return a;
        }
    }
}

