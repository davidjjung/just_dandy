package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.common.block.FluffyDandelionFlowerBlock;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.davigj.just_dandy.core.JustDandyMod;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustDandyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JustDandyBlocks {
	public static final BlockSubRegistryHelper HELPER = JustDandyMod.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> FLUFFY_DANDELION = HELPER.createBlock("fluffy_dandelion",
			() -> new FluffyDandelionFlowerBlock(JustDandyParticles.DANDELION_FLUFF::get, () -> Effects.SATURATION, 5, AbstractBlock.Properties.of
					(Material.PLANT).noOcclusion().noCollission().instabreak().sound(SoundType.GRASS)), ItemGroup.TAB_DECORATIONS);
	public static final RegistryObject<Block> POTTED_FLUFFY_DANDELION = HELPER.createBlockNoItem("potted_fluffy_dandelion",
			() -> new FlowerPotBlock(FLUFFY_DANDELION.get(), AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).noOcclusion()));
}
