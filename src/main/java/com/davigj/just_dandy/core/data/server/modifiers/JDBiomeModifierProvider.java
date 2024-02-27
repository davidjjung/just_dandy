package com.davigj.just_dandy.core.data.server.modifiers;

import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.other.JDBiomeTags;
import com.davigj.just_dandy.core.registry.JDFeatures;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JDBiomeModifierProvider {
    public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        RegistryAccess access = RegistryAccess.builtinCopy();
        Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
        Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
        HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

        addModifier(modifiers, "add_feature/fluffy_dandelion", new ForgeBiomeModifiers.AddFeaturesBiomeModifier(tag(biomeRegistry, JDBiomeTags.HAS_FLUFFY_DANDELION), of(placedFeatures, JDFeatures.JustDandyPlacedFeatures.FLOWER_FLUFFY_DANDELION), GenerationStep.Decoration.VEGETAL_DECORATION));
        addModifier(modifiers, "add_feature/frequent_fluffy_dandelion", new ForgeBiomeModifiers.AddFeaturesBiomeModifier(tag(biomeRegistry, JDBiomeTags.HAS_FREQUENT_FLUFFY_DANDELION), of(placedFeatures, JDFeatures.JustDandyPlacedFeatures.FLOWER_FREQUENT_FLUFFY_DANDELION), GenerationStep.Decoration.VEGETAL_DECORATION));

        return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, JustDandy.MODID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
    }

    private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
        return new HolderSet.Named<>(biomeRegistry, tagKey);
    }

    private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
        modifiers.put(new ResourceLocation(JustDandy.MODID, name), modifier);
    }

    @SafeVarargs
    @SuppressWarnings("ConstantConditions")
    private static HolderSet<PlacedFeature> of(Registry<PlacedFeature> placedFeatures, RegistryObject<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
    }
}
