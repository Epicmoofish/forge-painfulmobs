package net.oceanic.impossibledifficulty.interfaces;


import net.minecraft.world.item.Item;

public interface PlayerEatingGettingMixin {
    String getLastFood();
    void setLastFood(String lastFood);
}

