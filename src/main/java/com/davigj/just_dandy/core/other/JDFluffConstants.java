package com.davigj.just_dandy.core.other;

import codyhuh.worldofwonder.common.block.trees.DandelionFluffTree;
import codyhuh.worldofwonder.core.WonderBlocks;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.AzaleaTreeGrower;
import net.minecraftforge.fml.ModList;

public class JDFluffConstants {
    public static final Block FLUFF_BLOCK;
    public static final AbstractTreeGrower FLUFF_TREE;

    static {
        FLUFF_BLOCK = !ModList.get().isLoaded("worldofwonder") ? JDBlocks.POTTED_FLUFFY_DANDELION.get() : WonderBlocks.DANDELION_FLUFF.get();
        FLUFF_TREE = !ModList.get().isLoaded("worldofwonder") ? new AzaleaTreeGrower() : new DandelionFluffTree();
    }
}
