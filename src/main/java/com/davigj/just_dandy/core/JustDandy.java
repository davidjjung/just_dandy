package com.davigj.just_dandy.core;

import com.davigj.just_dandy.core.data.server.JDDatapackBuiltinEntriesProvider;
import com.davigj.just_dandy.core.data.server.tags.JDBiomeTagsProvider;
import com.davigj.just_dandy.core.other.JDCompat;
import com.davigj.just_dandy.core.registry.JDFeatures;
import com.davigj.just_dandy.core.registry.JDItems;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

@Mod(JustDandy.MOD_ID)
public class JustDandy {
	public static final String MOD_ID = "just_dandy";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public JustDandy() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);
		JDParticleTypes.PARTICLE_TYPES.register(bus);
		JDFeatures.FEATURES.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JDConfig.COMMON_SPEC);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> JDItems::buildCreativeTabContents);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			JDCompat.registerCompat();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		generator.addProvider(server, new JDDatapackBuiltinEntriesProvider(output, provider));
		generator.addProvider(server, new JDBiomeTagsProvider(output, provider, helper));
	}
}