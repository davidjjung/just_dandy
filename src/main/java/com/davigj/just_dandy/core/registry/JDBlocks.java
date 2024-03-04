package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.block.FluffyDandelionBlock;
import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = JustDandy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDBlocks {
	public static final BlockSubRegistryHelper HELPER = JustDandy.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> FLUFFY_DANDELION = HELPER.createBlock("fluffy_dandelion", () -> new FluffyDandelionBlock(() -> MobEffects.SATURATION, 12, JustDandyBlockProperties.FLOWER));
	public static final RegistryObject<Block> POTTED_FLUFFY_DANDELION = HELPER.createBlockNoItem("potted_fluffy_dandelion", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FLUFFY_DANDELION, JustDandyBlockProperties.FLOWER_POT));

	public static class JustDandyBlockProperties {
		public static final BlockBehaviour.Properties FLOWER = Block.Properties.of().mapColor(MapColor.PLANT).noOcclusion().noCollission()
				.instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY);
		public static final BlockBehaviour.Properties FLOWER_POT = Block.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
	}
}
