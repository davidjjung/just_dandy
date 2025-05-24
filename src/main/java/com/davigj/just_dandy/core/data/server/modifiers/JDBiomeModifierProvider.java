package com.davigj.just_dandy.core.data.server.modifiers;

import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.other.JDBiomeTags;
import com.davigj.just_dandy.core.registry.JDFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JDBiomeModifierProvider {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addFeature(context, "flower_fluffy_dandelion", JDBiomeTags.HAS_FLUFFY_DANDELION,
                GenerationStep.Decoration.VEGETAL_DECORATION, JDFeatures.JDPlacedFeatures.FLOWER_FLUFFY_DANDELION);
        addFeature(context, "flower_frequent_fluffy_dandelion", JDBiomeTags.HAS_FREQUENT_FLUFFY_DANDELION,
                GenerationStep.Decoration.VEGETAL_DECORATION, JDFeatures.JDPlacedFeatures.FLOWER_FREQUENT_FLUFFY_DANDELION);
        addFeature(context, "flower_thick_fluffy_dandelion", JDBiomeTags.HAS_THICK_DANDELION,
                GenerationStep.Decoration.VEGETAL_DECORATION, JDFeatures.JDPlacedFeatures.FLOWER_THICK_FLUFFY_DANDELION);
    }

    @SafeVarargs
    private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, JustDandy.location(name)), modifier.get());
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(key -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(key)).collect(Collectors.toList()));
    }
}
