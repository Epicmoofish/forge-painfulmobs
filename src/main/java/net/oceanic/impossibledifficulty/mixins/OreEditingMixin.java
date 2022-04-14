package net.oceanic.impossibledifficulty.mixins;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;


@Mixin(FeatureUtils.class)

public class OreEditingMixin {
    @Redirect(method = "register(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)Lnet/minecraft/core/Holder;", at = @At(value = "INVOKE", target = "Lnet/minecraft/data/BuiltinRegistries;registerExact(Lnet/minecraft/core/Registry;Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraft/core/Holder;"))
    private static <V extends T, T> Holder customOreVeins(Registry<T> p_206381_, String p_206382_, V p_206383_) {
        if (p_206382_ != "ore_iron" && p_206382_ != "ore_iron_small") {
            return BuiltinRegistries.registerExact(p_206381_, p_206382_, p_206383_);
        } else {

            RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
            RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
            List<OreConfiguration.TargetBlockState> IRON_TO_COBBLESTONE = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, Blocks.COBBLESTONE.defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, Blocks.COBBLED_DEEPSLATE.defaultBlockState()));
            if (p_206382_ != "ore_iron") {
                return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, "ore_cobble", new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(IRON_TO_COBBLESTONE, 9)));
            } else {
                return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, "ore_cobble_small", new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(IRON_TO_COBBLESTONE, 4)));
            }
        }
    }
}

