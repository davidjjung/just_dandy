package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.common.block.FluffyDandelionFlowerBlock;
import com.davigj.just_dandy.core.registry.JustDandyBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(FlowerBlock.class)
public class FlowerBlockMixin extends Block implements IGrowable {
    public FlowerBlockMixin(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return this == Blocks.DANDELION;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return this == Blocks.DANDELION;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        FluffyDandelionFlowerBlock fluffyDandelion = (FluffyDandelionFlowerBlock) JustDandyBlocks.FLUFFY_DANDELION.get();
        worldIn.setBlock(pos, fluffyDandelion.defaultBlockState(), 1);
    }
}
