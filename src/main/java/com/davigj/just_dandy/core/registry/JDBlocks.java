package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.block.FluffyDandelionBlock;
import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = JustDandy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDBlocks {
	public static final BlockSubRegistryHelper HELPER = JustDandy.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> FLUFFY_DANDELION = HELPER.createBlock("fluffy_dandelion", () -> new FluffyDandelionBlock(() -> MobEffects.SATURATION, 12, JustDandyBlockProperties.FLOWER));
	public static final RegistryObject<Block> POTTED_FLUFFY_DANDELION = HELPER.createBlockNoItem("potted_fluffy_dandelion", () -> flowerPot(FLUFFY_DANDELION.get()));

	public static class JustDandyBlockProperties {
		public static final BlockBehaviour.Properties FLOWER = Block.Properties.of().mapColor(MapColor.PLANT).noOcclusion().noCollission()
				.instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY);
	}

	private static FlowerPotBlock flowerPot(Block block, FeatureFlag... flags) {
		BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
		if (flags.length > 0) {
			blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(flags);
		}
		return new FlowerPotBlock(block, blockbehaviour$properties);
	}
}
