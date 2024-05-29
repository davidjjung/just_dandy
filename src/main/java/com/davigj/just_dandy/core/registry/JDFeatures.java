package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandy;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = JustDandy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JustDandy.MOD_ID);

    public static final RegistryObject<Feature<RandomPatchConfiguration>> FLUFFY_DANDELION_PATCH = FEATURES.register("fluffy_dandelion_patch", () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> DENSE_FLUFFY_DANDELION_PATCH = FEATURES.register("dense_fluffy_dandelion_patch", () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    public static final class JDConfiguredFeatures {
        public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FLUFFY_DANDELION = createKey("flower_fluffy_dandelion");
        public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_DENSE_FLUFFY_DANDELION = createKey("flower_dense_fluffy_dandelion");

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            register(context, FLOWER_FLUFFY_DANDELION, JDFeatures.FLUFFY_DANDELION_PATCH.get(), grassPatch(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 20)
                            .add(Blocks.DANDELION.defaultBlockState(), 8)), 14));
            register(context, FLOWER_DENSE_FLUFFY_DANDELION, JDFeatures.DENSE_FLUFFY_DANDELION_PATCH.get(), grassPatch(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 20)
                            .add(Blocks.DANDELION.defaultBlockState(), 8)), 40));
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(JustDandy.MOD_ID, name));
        }

        public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
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

        public static void bootstrap(BootstapContext<PlacedFeature> context) {
            register(context, FLOWER_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
            register(context, FLOWER_FREQUENT_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_DENSE_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
            register(context, FLOWER_THICK_FLUFFY_DANDELION, JDFeatures.JDConfiguredFeatures.FLOWER_DENSE_FLUFFY_DANDELION, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        }

        public static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(JustDandy.MOD_ID, name));
        }

        public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureHolder, PlacementModifier... modifiers) {
            context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configuredFeatureHolder), List.of(modifiers)));
        }
    }
}
