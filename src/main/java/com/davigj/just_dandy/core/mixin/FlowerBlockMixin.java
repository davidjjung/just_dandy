package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.core.JDConfig;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlowerBlock.class)
public abstract class FlowerBlockMixin extends BushBlock {
    public FlowerBlockMixin(Properties p_51021_) {
        super(p_51021_);
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.is(Blocks.DANDELION);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isAreaLoaded(pos, 1)) {
            if (JDConfig.COMMON.dandelionsBloom.get()) {
                if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextDouble() <= 0.08)) {
                    level.setBlock(pos, JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 2);
                    ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
    }

}
