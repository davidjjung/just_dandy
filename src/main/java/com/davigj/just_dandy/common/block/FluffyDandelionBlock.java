package com.davigj.just_dandy.common.block;

import com.davigj.just_dandy.core.JDConfig;
import com.davigj.just_dandy.core.other.JDBlockTags;
import com.davigj.just_dandy.core.registry.JDParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;

public class FluffyDandelionBlock extends FlowerBlock {
    public FluffyDandelionBlock(SuspiciousStewEffects suspiciousStewEffects, BlockBehaviour.Properties properties) {
        super(suspiciousStewEffects, properties);
    }

    public FluffyDandelionBlock(Holder<MobEffect> effect, float seconds, BlockBehaviour.Properties properties) {
        this(makeEffectList(effect, seconds), properties);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        BlockPos freezePos = pos.below();
        for (int i = 1; i <= JDConfig.CLIENT.stopFluff.get(); i++) {
            if (level.getBlockState(freezePos).is(JDBlockTags.STOP_FLUFF_BLOCKS)) {
                return;
            } else {
                freezePos = freezePos.below();
            }
        }
        if (level.isClientSide()) {
            int numParticles = (int) (rand.nextInt(3) * JDConfig.CLIENT.particleSpawnMultiplier.get());
            for (int i = 0; i < numParticles; i++) {
                double offsetX = state.getOffset(level, pos).x + rand.nextFloat() * 0.6F;
                double offsetZ = state.getOffset(level, pos).z + rand.nextFloat() * 0.45F;

                double x = pos.getX() + 0.25D + offsetX;
                double y = pos.getY() + 0.25D + (rand.nextFloat() * 0.05F);
                double z = pos.getZ() + 0.25D + offsetZ;
                RandomSource random = level.getRandom();

                if (level.getGameTime() % 3 == 0 && !level.isRainingAt(pos)) {
                    double xd = 0.06 * random.nextFloat();
                    double zd = 0.06 * random.nextFloat();
                    if (ModList.get().isLoaded("breezy")) {
                        xd = random.nextGaussian() * 0.015;
                        zd = random.nextGaussian() * 0.015;
                    }
                    level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z, xd, 0.0D, zd);
                }
            }
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide()) return;
        RandomSource worldrand = level.getRandom();
        int numParticles = 1;
        if (entity instanceof Projectile) {
            numParticles += (int) (worldrand.nextInt(5) * JDConfig.CLIENT.particleSpawnMultiplier.get());
        }
        for (int i = 0; i < numParticles; i++) {
            double offsetX = worldrand.nextFloat() * 0.6F;
            double offsetZ = worldrand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (worldrand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (!level.isRainingAt(pos) && entity.getDeltaMovement().length() != 0.0D) {
                level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z, 0.25 * entity.getDeltaMovement().x
                        + (0.05 * (worldrand.nextDouble() - 0.5D)), 0.0D, 0.25 * entity.getDeltaMovement().z);
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    public void onNearbyExplosion(Level level, BlockPos pos) {
        // There used to be more here but then the serverside nation attacked so now particles just go every which way i guess
        RandomSource rand = level.getRandom();
        int numParticles = 7 + rand.nextInt(6);

        double x = pos.getX() + 0.25 + rand.nextFloat() * 0.5;
        double y = pos.getY() + 0.25 + rand.nextFloat() * 0.25;
        double z = pos.getZ() + 0.25 + rand.nextFloat() * 0.5;

        if (level instanceof ServerLevel server) {
            for (int i = 0; i < numParticles; i++) {
                server.sendParticles(JDParticleTypes.DANDELION_FLUFF.get(), x, y, z,
                        1, 0, 0, 0, 0.04 + (rand.nextDouble() * 0.025));
            }
        }
    }


}
