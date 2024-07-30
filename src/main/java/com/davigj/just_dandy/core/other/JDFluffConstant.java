package com.davigj.just_dandy.core.other;

import codyhuh.worldofwonder.core.WonderBlocks;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;

public class JDFluffConstant {
    // This is a band-aid solution! I have no idea why the game would care about the DandelionTree grower of all things??
    public static final Block FLUFF_BLOCK;

    static {
        FLUFF_BLOCK = !ModList.get().isLoaded("worldofwonder") ? JDBlocks.POTTED_FLUFFY_DANDELION.get() : WonderBlocks.DANDELION_FLUFF.get();
    }
}
