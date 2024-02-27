package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JustDandy.MODID)
public class JDEvents {

    @SubscribeEvent
    public static void feelingDandy(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        if (level.getBlockState(pos).is(Blocks.DANDELION)) {
            level.setBlock(pos, JDBlocks.FLUFFY_DANDELION.get().defaultBlockState(), 1);
            event.setResult(Event.Result.ALLOW);
        }
    }
}
