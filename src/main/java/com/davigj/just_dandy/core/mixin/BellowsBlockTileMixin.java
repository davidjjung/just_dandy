package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.common.util.PuffUtil;
import net.mehvahdjukaar.supplementaries.common.block.tiles.BellowsBlockTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.mehvahdjukaar.supplementaries.common.block.tiles.BellowsBlockTile")
public class BellowsBlockTileMixin {

    @Inject(method = "blowParticles", at = @At("HEAD"), remap = false)
    private void dandyBellows(float air, Direction facing, Level level, boolean waterInFront, CallbackInfo ci) {
        BellowsBlockTile bellows = (BellowsBlockTile) (Object) this;
        if (level.random.nextFloat() < air && !waterInFront) {
            BlockPos startingPos = bellows.getBlockPos();
            PuffUtil.bellowsPuff(startingPos, facing, level);
        }
    }
}
