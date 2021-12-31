package com.davigj.just_dandy.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.davigj.just_dandy.core.JustDandyMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustDandyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JustDandyItems {
	public static final ItemSubRegistryHelper HELPER = JustDandyMod.REGISTRY_HELPER.getItemSubHelper();

}