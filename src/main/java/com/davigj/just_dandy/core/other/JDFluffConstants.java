package com.davigj.just_dandy.core.other;

import codyhuh.worldofwonder.common.block.trees.DandelionFluffTree;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.grower.AzaleaTreeGrower;
import net.minecraftforge.fml.ModList;

public class JDFluffConstants {
    public static final AbstractTreeGrower FLUFF_TREE;

    static {
        FLUFF_TREE = !ModList.get().isLoaded("worldofwonder") ? new AzaleaTreeGrower() : new DandelionFluffTree();
    }
}
