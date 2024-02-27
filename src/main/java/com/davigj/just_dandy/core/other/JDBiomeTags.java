package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class JDBiomeTags {
    public static final TagKey<Biome> HAS_FREQUENT_FLUFFY_DANDELION = biomeTag("has_feature/has_frequent_fluffy_dandelion");
    public static final TagKey<Biome> HAS_FLUFFY_DANDELION = biomeTag("has_feature/has_fluffy_dandelion");

    private static TagKey<Biome> biomeTag(String tagName) {
        return TagUtil.biomeTag(JustDandy.MODID, tagName);
    }
}
