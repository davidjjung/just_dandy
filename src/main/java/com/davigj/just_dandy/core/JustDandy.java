package com.davigj.just_dandy.core;

import com.davigj.just_dandy.core.data.server.JDDatapackBuiltinEntriesProvider;
import com.davigj.just_dandy.core.data.server.tags.JDBiomeTagsProvider;
import com.davigj.just_dandy.core.other.JDClientCompat;
import com.davigj.just_dandy.core.other.JDCompat;
import com.davigj.just_dandy.core.registry.JDBlocks;
import com.davigj.just_dandy.core.registry.JDFeatures;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(JustDandy.MOD_ID)
public class JustDandy {
	public static final String MOD_ID = "just_dandy";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public JustDandy(IEventBus bus, ModContainer container) {
		JDBlocks.BLOCKS.register(bus);
		JDBlocks.ITEMS.register(bus);
		JDParticleTypes.PARTICLE_TYPES.register(bus);
		JDFeatures.FEATURES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		container.registerConfig(ModConfig.Type.COMMON, JDConfig.COMMON_SPEC);
		container.registerConfig(ModConfig.Type.CLIENT, JDConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(JDCompat::register);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(JDClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		generator.addProvider(server, new JDDatapackBuiltinEntriesProvider(output, provider));
		generator.addProvider(server, new JDBiomeTagsProvider(output, provider, helper));
//		generator.addProvider(server, new JDDataRemolderProvider(output, provider));
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}