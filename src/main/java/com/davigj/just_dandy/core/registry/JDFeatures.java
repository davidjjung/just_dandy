package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandy;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class JDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, JustDandy.MOD_ID);

    public static final class JDConfiguredFeatures {
        public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FLUFFY_DANDELION = createKey("flower_fluffy_dandelion");
        public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_DENSE_FLUFFY_DANDELION = createKey("flower_dense_fluffy_dandelion");

        public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
            HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

            register(context, FLOWER_FLUFFY_DANDELION, Feature.RANDOM_PATCH,
                    grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                    .add(JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 20)
                                    .add(Blocks.DANDELION.defaultBlockState(), 12)), 14));
            register(context, FLOWER_DENSE_FLUFFY_DANDELION, Feature.RANDOM_PATCH, grassPatch(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 20)
                            .add(Blocks.DANDELION.defaultBlockState(), 8)), 40));
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, JustDandy.location(name));
        }

        public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
            context.register(key, new ConfiguredFeature<>(feature, config));
        }

        private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
            return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
        }
    }

    public static final class JDPlacedFeatures {
        public static final ResourceKey<PlacedFeature> FLOWER_FLUFFY_DANDELION = createKey("flower_fluffy_dandelion");
        public static final ResourceKey<PlacedFeature> FLOWER_FREQUENT_FLUFFY_DANDELION = createKey("flower_frequent_fluffy_dandelion");
        public static final ResourceKey<PlacedFeature> FLOWER_THICK_FLUFFY_DANDELION = createKey("flower_thick_fluffy_dandelion");

        public static void bootstrap(BootstrapContext<PlacedFeature> context) {
            register(context, FLOWER_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
            register(context, FLOWER_FREQUENT_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_DENSE_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
            register(context, FLOWER_THICK_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_DENSE_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        }

        public static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, JustDandy.location(name));
        }

        public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureHolder, PlacementModifier... modifiers) {
            context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configuredFeatureHolder), List.of(modifiers)));
        }
    }
}
