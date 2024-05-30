package com.davigj.just_dandy.core.other;

import codyhuh.worldofwonder.init.WonderItems;
import com.davigj.just_dandy.core.JustDandy;
import com.davigj.just_dandy.core.registry.JDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.davigj.just_dandy.core.other.JDConstants.FLUFF_TREE;

@Mod.EventBusSubscriber(modid = JustDandy.MOD_ID)
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

    @SubscribeEvent
    public static void whatInTheBloomMeal(PlayerInteractEvent.RightClickBlock event) {
        if (ModList.get().isLoaded("worldofwonder")) {
            Level level = event.getLevel();
            ItemStack stack = event.getItemStack();
            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Player player = event.getEntity();
            InteractionHand hand = event.getHand();
            bloomMealShenanigans(level, stack, pos, state, player, hand, event);
        }
    }

    public static void bloomMealShenanigans(Level level, ItemStack stack, BlockPos pos, BlockState state, Player player, InteractionHand hand, PlayerInteractEvent.RightClickBlock event) {
        if (stack.getItem() == WonderItems.BLOOM_MEAL.get() && state.getBlock() == JDBlocks.FLUFFY_DANDELION.get()) {
            if (!level.isClientSide) {
                RandomSource random = level.random;
                level.levelEvent(2005, pos, 0);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                if (random.nextInt(3) == 0) {
                    FLUFF_TREE.growTree((ServerLevel) level, ((ServerLevel) level).getChunkSource().getGenerator(), pos, state, random);
                }
            } else {
                if (hand != null) {
                    player.swing(hand);
                }
            }
            if (event != null) {
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }
}
