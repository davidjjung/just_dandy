package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.world.entity.animal.Bee$BeeGrowCropGoal")
public class BeeGrowCropGoalMixin {
    @Shadow
    @Final
    Bee this$0;

    @Inject(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z", shift = At.Shift.AFTER))
    private void growThatDandy(CallbackInfo ci) {
        BlockPos blockpos = this.this$0.blockPosition();
        BlockState blockstate = this.this$0.level.getBlockState(blockpos);
        if (blockstate.is(Blocks.DANDELION)) {
            this.this$0.level.levelEvent(2005, blockpos, 0);
            this.this$0.level.setBlockAndUpdate(blockpos, JDBlocks.FLUFFY_DANDELION.get().defaultBlockState());
            ++this.this$0.numCropsGrownSincePollination;
        }
    }
}
