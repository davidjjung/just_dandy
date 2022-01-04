package com.davigj.just_dandy.common.block;

import com.davigj.just_dandy.core.JustDandyConfig;
import com.davigj.just_dandy.core.JustDandyMod;
import com.google.common.base.Supplier;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class FluffyDandelionFlowerBlock extends AbnormalsFlowerBlock {
    private final java.util.function.Supplier<IParticleData> particle;

    public FluffyDandelionFlowerBlock(Supplier<IParticleData> particle, Supplier<Effect> stewEffect, int stewEffectDuration, Properties properties) {
        super(stewEffect, stewEffectDuration, properties);
        this.particle = particle::get;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        int numParticles = (int) (RANDOM.nextInt(4) * JustDandyConfig.COMMON.particleSpawnMultiplier.get());
        if(JustDandyConfig.COMMON.fluffyDandyProlificBiomes.get().contains(worldIn.getBiome(pos).getRegistryName().toString())) {
            numParticles += 3;
        }
        for (int i = 0; i < numParticles; i++) {
            double offsetX = rand.nextFloat() * 0.6F;
            double offsetZ = rand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (rand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (worldIn.isClientSide && worldIn.getGameTime() % 3 == 0 && !worldIn.isRaining())
                worldIn.addParticle(this.particle.get(), x, y, z, 0.1 * Math.random(), 0.0D, 0.1 * Math.random());
        }
    }

    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        Random rand = worldIn.getRandom();
        double entityX = entity.getDeltaMovement().x;
        double entityZ = entity.getDeltaMovement().z;
        int numParticles = 1;
        if (entity instanceof ProjectileEntity) {
            numParticles += (int) (rand.nextInt(5) * JustDandyConfig.COMMON.particleSpawnMultiplier.get());
        }
        for (int i = 0; i < numParticles; i++) {
            double offsetX = rand.nextFloat() * 0.6F;
            double offsetZ = rand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (rand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (worldIn.isClientSide && (entityX != 0 || entity.getDeltaMovement().y != 0 ||
                    entityZ != 0) && !worldIn.isRainingAt(pos)) {
                worldIn.addParticle(this.particle.get(), x, y, z, 0.25 * entityX + (0.05 * (rand.nextDouble() - 0.5D)), 0.0D,
                        0.25 * entityZ);
            } else if (entity instanceof MobEntity) {
                if ((entity.getMotionDirection().getStepZ() != 0 || entity.getMotionDirection().getStepX() != 0)
                        && !worldIn.isClientSide && !worldIn.isRainingAt(pos) && worldIn.getGameTime() % 8 == 0) {
                    ((ServerWorld)worldIn).sendParticles(this.particle.get(), x, y, z, 0, 0.1 * entity.getMotionDirection().getStepX() * (Math.random() - 0.5D),
                            0.0D,0.1 * entity.getMotionDirection().getStepZ() * (Math.random() - 0.5D), 1.0F);
                    LOGGER.debug(entity.getMotionDirection().getStepZ());
                }
            }
        }
        super.entityInside(state, worldIn, pos, entity);
    }

    public void destroy(IWorld worldIn, BlockPos pos, BlockState state) {
        Random rand = worldIn.getRandom();
        int numParticles = (int) (RANDOM.nextInt(5) * JustDandyConfig.COMMON.particleSpawnMultiplier.get() + 3);
        if(JustDandyConfig.COMMON.fluffyDandyProlificBiomes.get().contains(worldIn.getBiome(pos).getRegistryName().toString())) {
            numParticles += 3;
        }
        for (int i = 0; i < numParticles; i++) {
            double offsetX = rand.nextFloat() * 0.6F;
            double offsetZ = rand.nextFloat() * 0.45F;

            double x = pos.getX() + 0.25D + offsetX;
            double y = pos.getY() + 0.25D + (rand.nextFloat() * 0.05F);
            double z = pos.getZ() + 0.25D + offsetZ;

            if (worldIn.isClientSide() && !worldIn.getLevelData().isRaining())
                worldIn.addParticle(this.particle.get(), x, y, z, 0.1D * (Math.random() - 0.5D), 0.0D, 0.1D * (Math.random() - 0.5D));
        }
    }
}
