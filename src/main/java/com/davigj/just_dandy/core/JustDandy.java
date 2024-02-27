package com.davigj.just_dandy.core;

import com.davigj.just_dandy.core.data.server.modifiers.JDBiomeModifierProvider;
import com.davigj.just_dandy.core.other.JDCompat;
import com.davigj.just_dandy.core.registry.JDFeatures;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JustDandy.MODID)
public class JustDandy {
	public static final String MODID = "just_dandy";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public JustDandy() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);
		JDParticleTypes.PARTICLE_TYPES.register(bus);
		JDFeatures.FEATURES.register(bus);
		JDFeatures.JustDandyPlacedFeatures.PLACED_FEATURES.register(bus);
		JDFeatures.JustDandyConfiguredFeatures.CONFIGURED_FEATURES.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JDConfig.COMMON_SPEC);

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
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();

		generator.addProvider(includeServer, JDBiomeModifierProvider.create(generator, helper));
	}
}