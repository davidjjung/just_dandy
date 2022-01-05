package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.JustDandyConfig;
import com.davigj.just_dandy.core.JustDandyMod;
import com.davigj.just_dandy.core.registry.JustDandyFeatures;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = JustDandyMod.MOD_ID)
public class JustDandyGeneration {
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        Logger LOGGER = LogManager.getLogger(JustDandyMod.MOD_ID);
        String biome = event.getName().toString();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (JustDandyConfig.COMMON.manyDandyBiomes.get().contains(biome)){
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JustDandyFeatures.Configured.DENSE_FLOWER_FLUFFY_DANDELION);
        }
        if (event.getCategory() == Biome.Category.PLAINS && JustDandyConfig.COMMON.plainSpawns.get()
                && !JustDandyConfig.COMMON.noDandyBiomes.get().contains(biome)) {
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JustDandyFeatures.Configured.FLOWER_FLUFFY_DANDELION);
        }
        if (event.getCategory() == Biome.Category.FOREST && JustDandyConfig.COMMON.forestSpawns.get()
                && !JustDandyConfig.COMMON.noDandyBiomes.get().contains(biome)) {
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JustDandyFeatures.Configured.FLOWER_FLUFFY_DANDELION);
        }
        if (event.getCategory() == Biome.Category.TAIGA && JustDandyConfig.COMMON.taigaSpawns.get()
                && !JustDandyConfig.COMMON.noDandyBiomes.get().contains(biome)) {
            generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JustDandyFeatures.Configured.FLOWER_FLUFFY_DANDELION);
        }
    }
}
