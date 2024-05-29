package com.davigj.just_dandy.core.data.server.tags;

import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.other.JDBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.davigj.just_dandy.core.other.JDConstants.*;
import static net.minecraft.world.level.biome.Biomes.*;

public class JDBiomeTagsProvider extends BiomeTagsProvider {
    public JDBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, JustDandy.MOD_ID, helper);
    }
    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(JDBiomeTags.HAS_FLUFFY_DANDELION).add(PLAINS).add(FOREST).addOptional(DANDELION_FIELDS)
                .addOptional(HAZE_MOUNTAIN).addOptional(PINE_BARRENS);
        this.tag(JDBiomeTags.HAS_FREQUENT_FLUFFY_DANDELION).add(MEADOW).addOptional(CLOUD_FOREST)
                .addOptional(DANDELION_FIELDS);
        this.tag(JDBiomeTags.HAS_THICK_DANDELION).addOptional(DANDELION_FIELDS);
    }
}
