package com.davigj.just_dandy.core.mixin;

import com.davigj.just_dandy.common.util.MixinUtil;
import com.davigj.just_dandy.core.JDConfig;
import com.simibubi.create.content.kinetics.fan.AirCurrent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.simibubi.create.content.kinetics.fan.AirCurrent")
public class AirCurrentMixin {
    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    private void dandyCurrent(CallbackInfo ci) {
        AirCurrent current = (AirCurrent) (Object) this;
        Direction facing = current.direction;
        Level level = current.source.getAirCurrentWorld();
        if (facing != null && level != null && level.isClientSide()) {
            BlockPos pos = current.source.getAirCurrentPos();
            int distance = (int) current.maxDistance;
            float intensity = (float) (current.source.getSpeed() * 0.005F * JDConfig.CLIENT.particleSpawnMultiplier.get());
            MixinUtil.currentPuff(pos, facing, level, intensity, distance);
        }
    }

}
