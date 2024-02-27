package com.davigj.just_dandy.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class JDConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Double> particleSpawnMultiplier;
        public final ForgeConfigSpec.ConfigValue<Double> particleLifetimeMultiplier;

        public Common(ForgeConfigSpec.Builder builder) {
            particleSpawnMultiplier = builder.comment("A multiplier for how many particles emanate from dandies in general")
                    .define("particleSpawnMultiplier", 1.0);
            particleLifetimeMultiplier = builder.comment("A multiplier for how long particles last upon spawn")
                    .define("particleLifetimeMultiplier", 1.0);
        }
    }

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = (new ForgeConfigSpec.Builder()).configure(Common::new);
        COMMON_SPEC = (ForgeConfigSpec) commonSpecPair.getRight();
        COMMON = (Common) commonSpecPair.getLeft();
    }


}
