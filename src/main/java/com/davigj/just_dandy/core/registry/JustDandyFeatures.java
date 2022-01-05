package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandyConfig;
import com.davigj.just_dandy.core.JustDandyMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = JustDandyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JustDandyFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JustDandyMod.MOD_ID);

    public static final class Configs {
        public static final BlockClusterFeatureConfig FLUFFY_DANDELION_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.DANDELION.defaultBlockState(), 2).add(JustDandyBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
        public static final BlockClusterFeatureConfig DENSE_FLUFFY_DANDELION_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.DANDELION.defaultBlockState(), 1).add(JustDandyBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 2), SimpleBlockPlacer.INSTANCE)).tries(64).build();
    }

    public static final class Configured {
        public static final ConfiguredFeature<?, ?> FLOWER_FLUFFY_DANDELION = register("flower_fluffy_dandelion", Feature.FLOWER.configured(Configs.FLUFFY_DANDELION_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(JustDandyConfig.COMMON.dandySpawnWeight.get()));
        public static final ConfiguredFeature<?, ?> DENSE_FLOWER_FLUFFY_DANDELION = register("dense_flower_fluffy_dandelion", Feature.FLOWER.configured(Configs.DENSE_FLUFFY_DANDELION_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(JustDandyConfig.COMMON.manyDandySpawnWeight.get()));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(JustDandyMod.MOD_ID, name), configuredFeature);
    }
}
