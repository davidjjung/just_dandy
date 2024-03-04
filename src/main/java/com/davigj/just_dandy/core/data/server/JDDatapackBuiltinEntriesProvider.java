package com.davigj.just_dandy.core.data.server;

import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.registry.JDFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraft.core.HolderLookup.Provider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class JDDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, JDFeatures.JustDandyConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, JDFeatures.JustDandyPlacedFeatures::bootstrap);

    public JDDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider, BUILDER, Set.of(JustDandy.MOD_ID));
    }
}
