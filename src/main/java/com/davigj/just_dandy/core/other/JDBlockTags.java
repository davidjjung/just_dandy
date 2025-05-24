package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.JustDandy;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.teamabnormals.blueprint.core.util.TagUtil.blockTag;

public class JDBlockTags {
    public static final TagKey<Block> FLUFFY_BLOCKS = blockTag(JustDandy.MOD_ID, "fluffy_blocks");
    public static final TagKey<Block> FULL_FLUFFY_BLOCKS = blockTag(JustDandy.MOD_ID, "full_fluffy_blocks");
}
