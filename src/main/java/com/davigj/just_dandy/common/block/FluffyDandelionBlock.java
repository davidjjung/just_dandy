package com.davigj.just_dandy.common.block;

import com.davigj.just_dandy.core.JDConfig;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.BellowsBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class FluffyDandelionBlock extends BlueprintFlowerBlock {
    public FluffyDandelionBlock(Supplier<MobEffect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        int numParticles = (int) (rand.nextInt(3) * JDConfig.COMMON.particleSpawnMultiplier.get());
        for (int i = 0; i < numParticles; i++) {
            double offsetX = rand.nextFloat() * 0.6F;
            double offsetZ = rand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (rand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (level.getGameTime() % 3 == 0 && !level.isRainingAt(pos))
                level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z, 0.06 * Math.random(), 0.0D, 0.06 * Math.random());
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        RandomSource worldrand = level.getRandom();
        int numParticles = 1;
        if (entity instanceof Projectile) {
            numParticles += (int) (worldrand.nextInt(5) * JDConfig.COMMON.particleSpawnMultiplier.get());
        }
        for (int i = 0; i < numParticles; i++) {
            double offsetX = worldrand.nextFloat() * 0.6F;
            double offsetZ = worldrand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (worldrand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (level.isClientSide && !level.isRainingAt(pos) && entity.getDeltaMovement().length() != 0.0D) {
                level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z, 0.25 * entity.getDeltaMovement().x
                                + (0.05 * (worldrand.nextDouble() - 0.5D)), 0.0D,0.25 * entity.getDeltaMovement().z);
            }
        }
        super.entityInside(state, level, pos, entity);
    }
}
