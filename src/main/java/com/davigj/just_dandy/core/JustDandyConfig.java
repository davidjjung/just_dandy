package com.davigj.just_dandy.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JustDandyConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final JustDandyConfig.Common COMMON;

    static {
        Pair<JustDandyConfig.Common, ForgeConfigSpec> commonSpecPair = (new ForgeConfigSpec.Builder()).configure(JustDandyConfig.Common::new);
        COMMON_SPEC = (ForgeConfigSpec) commonSpecPair.getRight();
        COMMON = (JustDandyConfig.Common) commonSpecPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> forestSpawns;
        public final ForgeConfigSpec.ConfigValue<Boolean> plainSpawns;
        public final ForgeConfigSpec.ConfigValue<Boolean> taigaSpawns;
        public final ForgeConfigSpec.ConfigValue<Double> particleSpawnMultiplier;
        public final ForgeConfigSpec.ConfigValue<Double> particleLifetimeMultiplier;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> fluffyDandyProlificBiomes;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> noDandyBiomes;
        public Common(ForgeConfigSpec.Builder builder) {
            forestSpawns = builder.comment("Whether or not fluffy dandelion patches spawn in forest type biomes")
                    .define("forestSpawns", true);
            plainSpawns = builder.comment("Whether or not fluffy dandelion patches spawn in plain type biomes")
                    .define("plainSpawns", true);
            taigaSpawns = builder.comment("Whether or not fluffy dandelion patches spawn in taiga type biomes")
                    .define("taigaSpawns", false);
            particleSpawnMultiplier = builder.comment("A multiplier for how many particles emanate from dandies in general")
                    .define("particleSpawnMultiplier", 1.0D);
            particleLifetimeMultiplier = builder.comment("A multiplier for how long particles last upon spawn")
                    .define("particleLifetimeMultiplier", 1.0D);
            fluffyDandyProlificBiomes = builder.comment("A list of biomes where fluffy dandelions generate more particles than normal")
                    .define("fluffyDandyProlificBiomes", new ArrayList<>(Arrays.asList("worldofwonder:dandelion_fields", "minecraft:flower_forest",
                            "minecraft:sunflower_plains")), s -> s instanceof String);
            noDandyBiomes = builder.comment("A list of biomes where fluffy dandelion features do not generate")
                    .define("noDandyBiomes", new ArrayList<>(Arrays.asList("environmental:blossom_woods", "environmental:blossom_highlands",
                            "environmental:blossom_hills", "environmental:blossom_valleys", "minecraft:snowy_plains")), s -> s instanceof String);
        }
    }

}
