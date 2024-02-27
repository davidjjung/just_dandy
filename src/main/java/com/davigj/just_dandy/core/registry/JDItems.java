package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustDandy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDItems {
	public static final ItemSubRegistryHelper HELPER = JustDandy.REGISTRY_HELPER.getItemSubHelper();
}