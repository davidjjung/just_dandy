package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.core.registry.JDParticleTypes;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(targets = "codyhuh.worldofwonder.common.block.DandelionFluffBlock")
public class DandelionFluffBlockMixin extends Block {
    public DandelionFluffBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (ModList.get().isLoaded("worldofwonder")) {
            if (random.nextInt(8) == 0 && !level.isRainingAt(pos)) {
                Direction direction = Direction.getRandom(random);
                if (direction != Direction.UP) {
                    BlockPos blockpos = pos.relative(direction);
                    BlockState blockstate = level.getBlockState(blockpos);
                    if (!state.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
                        double d0 = direction.getStepX() == 0 ? random.nextDouble() : 0.5 + (double) direction.getStepX() * 0.6;
                        double d1 = direction.getStepY() == 0 ? random.nextDouble() : 0.5 + (double) direction.getStepY() * 0.6;
                        double d2 = direction.getStepZ() == 0 ? random.nextDouble() : 0.5 + (double) direction.getStepZ() * 0.6;
                        level.addParticle(JDParticleTypes.DANDELION_FLUFF.get(), (double) pos.getX() + d0,
                                (double) pos.getY() + d1, (double) pos.getZ() + d2,
                                0.06 * random.nextFloat(), -0.03, 0.06 * random.nextFloat());
                    }
                }
            }
        }
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, entity, stack);
        for (int i = 1; i < 10; i++) {
            RandomSource random = level.getRandom();
            double d0 = pos.getX() + random.nextDouble() * 0.6D + 0.3D;
            double d1 = pos.getY() + random.nextDouble() * 0.6D + 0.3D;
            double d2 = pos.getZ() + random.nextDouble() * 0.6D + 0.3D;
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d5 = random.nextGaussian() * 0.02D;
            NetworkUtil.spawnParticle("just_dandy:dandelion_fluff", d0, d1, d2, d3, d4, d5);
        }
    }
}
