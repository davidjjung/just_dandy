package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.block.FluffyDandelionBlock;
import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.item.CreativeModeTabs.NATURAL_BLOCKS;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class JDBlocks {
	public static final BlockSubRegistryHelper BLOCKS = JustDandy.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> FLUFFY_DANDELION = BLOCKS.createBlock("fluffy_dandelion", () -> new FluffyDandelionBlock(MobEffects.SATURATION, 12, PropertyUtil.flower()));
	public static final DeferredBlock<Block> POTTED_FLUFFY_DANDELION = BLOCKS.createBlockNoItem("potted_fluffy_dandelion", () -> new FlowerPotBlock(FLUFFY_DANDELION.get(), PropertyUtil.flowerPot()));

	public static void setUpTabEditors() {
		CreativeModeTabContentsPopulator.mod(JustDandy.MOD_ID)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.DANDELION), FLUFFY_DANDELION);
	}
}
