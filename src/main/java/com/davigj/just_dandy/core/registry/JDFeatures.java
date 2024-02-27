package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.worldgen.configuration.WeightedPatchConfiguration;
import com.davigj.just_dandy.common.worldgen.feature.WeightedPatchFeature;
import com.davigj.just_dandy.core.JustDandy;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = JustDandy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JustDandy.MODID);

    public static final BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);

    public static final RegistryObject<Feature<WeightedPatchConfiguration>> WEIGHTED_PATCH = FEATURES.register("weighted_patch", () -> new WeightedPatchFeature(WeightedPatchConfiguration.CODEC));

    public static WeightedPatchConfiguration weightedPatchConfig(Block primaryBlock, Block secondaryBlock, BlockPredicate plantedOn, int tries) {
        return new WeightedPatchConfiguration(tries, 6, 3, plantBlockConfig(primaryBlock, plantedOn), plantBlockConfig(secondaryBlock, plantedOn), null);
    }

    public static Holder<PlacedFeature> plantBlockConfig(Block block, BlockPredicate plantedOn) {
        return PlacementUtils.filtered(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)),
                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn));
    }

    public static final class JustDandyConfiguredFeatures {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, JustDandy.MODID);

        public static final RegistryObject<ConfiguredFeature<WeightedPatchConfiguration, ?>> FLOWER_FLUFFY_DANDELION = register("flower_fluffy_dandelion", () ->
                new ConfiguredFeature<>(JDFeatures.WEIGHTED_PATCH.get(), weightedPatchConfig(JDBlocks.FLUFFY_DANDELION.get(),
                        Blocks.DANDELION, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), 16)));
        public static final RegistryObject<ConfiguredFeature<WeightedPatchConfiguration, ?>> FLOWER_DENSE_FLUFFY_DANDELION = register("flower_dense_fluffy_dandelion", () ->
                new ConfiguredFeature<>(JDFeatures.WEIGHTED_PATCH.get(), weightedPatchConfig(JDBlocks.FLUFFY_DANDELION.get(),
                        Blocks.DANDELION, BlockPredicate.matchesTag(BLOCK_BELOW, BlockTags.DIRT), 48)));

        private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
            return CONFIGURED_FEATURES.register(name, feature);
        }
    }

    public static final class JustDandyPlacedFeatures {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, JustDandy.MODID);
        public static final RegistryObject<PlacedFeature> FLOWER_FLUFFY_DANDELION = register("flower_fluffy_dandelion", JustDandyConfiguredFeatures.FLOWER_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        public static final RegistryObject<PlacedFeature> FLOWER_FREQUENT_FLUFFY_DANDELION = register("flower_frequent_fluffy_dandelion", JustDandyConfiguredFeatures.FLOWER_DENSE_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());


        private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
            return register(name, feature, List.of(placementModifiers));
        }

        @SuppressWarnings("unchecked")
        private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
            return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
        }
    }
}
