package com.davigj.just_dandy.common.util;

import com.davigj.just_dandy.common.block.FluffyDandelionBlock;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PuffUtil {
    // Util class that handles compatibility-based particle fx because mixins not being able to hotswap wastes my time
    public static void bellowsPuff(BlockPos startingPos, Direction facing, Level level) {
        for (int i = 1; i <= 5; i++) {
            BlockPos facingPos = startingPos.relative(facing, i);
            BlockState frontState = level.getBlockState(facingPos);
            if (frontState.getBlock() instanceof FluffyDandelionBlock) {
                double xo = (double) facing.getStepX();
                double yo = (double) facing.getStepY();
                double zo = (double) facing.getStepZ();
                double x = xo * 0.5 + (double) facingPos.getX() + 0.5 + ((double) level.random.nextFloat() - 0.5) / 3.0;
                double y = yo * 0.5 + (double) facingPos.getY() + 0.5 + ((double) level.random.nextFloat() - 0.5) / 3.0;
                double z = zo * 0.5 + (double) facingPos.getZ() + 0.5 + ((double) level.random.nextFloat() - 0.5) / 3.0;
                double vel = (double) (0.125F + level.random.nextFloat() * 0.2F);
                double velX = xo * vel;
                double velY = yo * vel;
                double velZ = zo * vel;
                level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z, velX * 0.8, velY * 0.8, velZ * 0.8);
            }
        }
    }

    public static void currentPuff(BlockPos startingPos, Direction facing, Level level, float intensity, int distance) {
        intensity = Math.min(intensity, 0.04F);
        RandomSource random = level.getRandom();
        double xo = (double) (facing.getStepX() * -intensity) + (random.nextGaussian() * 0.01);
        double yo = (double) facing.getStepY() * -intensity;
        double zo = (double) (facing.getStepZ() * -intensity) + (random.nextGaussian() * 0.01);
        for (int i = 1; i <= distance; i++) {
            if (random.nextInt(i + 1) == i) {
                BlockPos facingPos = startingPos.relative(facing, i);
                BlockState frontState = level.getBlockState(facingPos);
                if (frontState.getBlock() instanceof FluffyDandelionBlock) {
                    level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(),
                            facingPos.getX() + 0.5, facingPos.getY() + 0.5, facingPos.getZ() + 0.5, xo, yo, zo);
                }
            }
        }
    }
}
