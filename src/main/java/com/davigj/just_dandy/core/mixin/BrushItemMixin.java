package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.common.util.MixinUtil;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.davigj.just_dandy.core.other.JDConstants.FLUFF_BLOCK;

@Mixin(BrushItem.class)
public class BrushItemMixin {

    @Inject(method = "onUseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void brushItUp(Level level, LivingEntity living, ItemStack stack, int remainingUseTicks, CallbackInfo ci,
                           Player $$5, HitResult $$6, BlockHitResult blockHitResult, int $$9, boolean $$10, BlockPos pos,
                           BlockState state, HumanoidArm arm) {
        if (state.is(FLUFF_BLOCK) || state.is(JDBlocks.POTTED_FLUFFY_DANDELION.get())) {
            MixinUtil.brushUpFluff(level, blockHitResult, $$5.getViewVector(0.0F), arm);
        }
    }
}
