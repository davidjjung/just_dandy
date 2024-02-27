package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.registry.JDBlocks;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

public class JDCompat {

    public static void registerCompat() {
        registerCompostables();
    }

    public static void registerCompostables() {
        DataUtil.registerCompostable(JDBlocks.FLUFFY_DANDELION.get(), 0.65F);
    }
}
