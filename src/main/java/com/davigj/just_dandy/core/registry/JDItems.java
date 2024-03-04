package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = JustDandy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDItems {
	public static final ItemSubRegistryHelper HELPER = JustDandy.REGISTRY_HELPER.getItemSubHelper();

	public static void buildCreativeTabContents() {
		CreativeModeTabContentsPopulator.mod(JustDandy.MOD_ID)
				.tab(CreativeModeTabs.NATURAL_BLOCKS)
				.addItemsAfter(of(Items.DANDELION), JDBlocks.FLUFFY_DANDELION);
	}
}