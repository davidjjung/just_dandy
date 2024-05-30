package com.davigj.just_dandy.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.davigj.just_dandy.core.other.JDEvents.bloomMealShenanigans;

@Mixin(targets = "codyhuh.worldofwonder.common.events.ItemEvents$BloomMealDispenseBehavior")
public class BloomMealDispenseBehaviorMixin {
    @Inject(method = "execute", at = @At("HEAD"))
    public void fluffBloom(BlockSource source, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (ModList.get().isLoaded("worldofwonder")) {
            ServerLevel level = source.getLevel();
            BlockPos blockpos = source.getPos().relative((Direction)source.getBlockState().getValue(DispenserBlock.FACING));
            bloomMealShenanigans(level, stack, blockpos, level.getBlockState(blockpos), FakePlayerFactory.getMinecraft(level), null, null);
        }
    }
}
