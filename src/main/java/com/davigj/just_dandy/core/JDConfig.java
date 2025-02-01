package com.davigj.just_dandy.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class JDConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> dandelionsBloom;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("common");
            dandelionsBloom = builder.comment("Dandelions grow fluffy over time")
                    .define("growth ticks", false);
            builder.pop();
        }
    }


    public static class Client {
        public final ForgeConfigSpec.ConfigValue<Double> particleSpawnMultiplier;
        public final ForgeConfigSpec.ConfigValue<Double> particleLifetimeMultiplier;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("client");
            particleSpawnMultiplier = builder.comment("A multiplier for how many particles emanate from dandies in general")
                    .define("particleSpawnMultiplier", 1.0);
            particleLifetimeMultiplier = builder.comment("A multiplier for how long particles last upon spawn")
                    .define("particleLifetimeMultiplier", 1.0);
            builder.pop();
        }
    }

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = (new ForgeConfigSpec.Builder()).configure(Common::new);
        COMMON_SPEC = (ForgeConfigSpec) commonSpecPair.getRight();
        COMMON = (Common) commonSpecPair.getLeft();

        Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
