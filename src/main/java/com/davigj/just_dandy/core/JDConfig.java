package com.davigj.just_dandy.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class JDConfig {
    static final ModConfigSpec COMMON_SPEC;
    public static final JDConfig.Common COMMON;

    static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    public static class Common {
        @ConfigKey("dandelions_bloom")
        public final ModConfigSpec.ConfigValue<Boolean> dandelionsBloom;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("common");
            dandelionsBloom = builder.comment("Dandelions grow fluffy over time")
                    .define("growth ticks", false);
            builder.pop();
        }
    }


    public static class Client {
        public final ModConfigSpec.ConfigValue<Double> particleSpawnMultiplier;
        public final ModConfigSpec.ConfigValue<Double> particleLifetimeMultiplier;
        public final ModConfigSpec.ConfigValue<Integer> stopFluff;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("client");
            particleSpawnMultiplier = builder.comment("A multiplier for how many particles emanate from dandies in general")
                    .define("particleSpawnMultiplier", 1.0);
            particleLifetimeMultiplier = builder.comment("A multiplier for how long particles last upon spawn")
                    .define("particleLifetimeMultiplier", 1.0);
            stopFluff = builder.comment("Placing a stop_fluff_blocks block stops particles when placed up to this far below fluffy dandelions")
                    .define("stopFluffDepth", 2);
            builder.pop();
        }
    }

    static {
        Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = (ModConfigSpec) commonSpecPair.getRight();
        COMMON = (Common) commonSpecPair.getLeft();

        Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
