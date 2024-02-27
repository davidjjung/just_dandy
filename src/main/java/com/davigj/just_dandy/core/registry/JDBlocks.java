package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.block.FluffyDandelionBlock;
import com.davigj.just_dandy.core.JustDandy;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = JustDandy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JDBlocks {
	public static final BlockSubRegistryHelper HELPER = JustDandy.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> FLUFFY_DANDELION = HELPER.createBlock("fluffy_dandelion", () -> new FluffyDandelionBlock(() -> MobEffects.SATURATION, 12, JustDandyBlockProperties.FLOWER), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_FLUFFY_DANDELION = HELPER.createBlockNoItem("potted_fluffy_dandelion", () -> new FlowerPotBlock(FLUFFY_DANDELION.get(), JustDandyBlockProperties.FLOWER_POT));

	public static class JustDandyBlockProperties {
		public static final BlockBehaviour.Properties FLOWER = Block.Properties.of(Material.PLANT).noOcclusion().noCollission().instabreak().sound(SoundType.GRASS);
		public static final BlockBehaviour.Properties FLOWER_POT = Block.Properties.of(Material.DECORATION).instabreak().noOcclusion();
	}
}
