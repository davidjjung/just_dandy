package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.registry.JDBlocks;
import com.teamabnormals.blueprint.core.util.DataUtil;

public class JDCompat {

    public static void register() {
        registerFlammables();
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(JDBlocks.FLUFFY_DANDELION.get(), 60, 100);
    }
}
