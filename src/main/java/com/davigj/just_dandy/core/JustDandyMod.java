package com.davigj.just_dandy.core;

import com.davigj.just_dandy.core.other.JustDandyCompat;
import com.davigj.just_dandy.core.registry.JustDandyBlocks;
import com.davigj.just_dandy.core.registry.JustDandyFeatures;
import com.davigj.just_dandy.core.registry.JustDandyParticles;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JustDandyMod.MOD_ID)
public class JustDandyMod {
	public static final String MOD_ID = "just_dandy";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public JustDandyMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);
		JustDandyFeatures.FEATURES.register(bus);
		JustDandyParticles.PARTICLE_TYPES.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JustDandyConfig.COMMON_SPEC);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			JustDandyCompat.registerCompat();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			JustDandyCompat.setRenderLayers();
		});
	}

	private void dataSetup(GatherDataEvent event) {

	}
}