package com.davigj.just_dandy.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "codyhuh.worldofwonder.common.events.ItemEvents$BloomMealDispenseBehavior")
public class BloomMealDispenseBehaviorMixin {
//    @Inject(method = "execute", at = @At("HEAD"))
//    public void fluffBloom(BlockSource source, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
//        if (ModList.get().isLoaded("worldofwonder")) {
//            ServerLevel level = source.getLevel();
//            BlockPos blockpos = source.getPos().relative((Direction)source.getBlockState().getValue(DispenserBlock.FACING));
//            bloomMealShenanigans(level, stack, blockpos, level.getBlockState(blockpos), FakePlayerFactory.getMinecraft(level), null, null);
//        }
//    }
}
