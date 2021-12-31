package com.davigj.just_dandy.core.other;

import com.davigj.just_dandy.core.registry.JustDandyBlocks;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class JustDandyCompat {

    public static void registerCompat() {
        registerCompostables();
    }

    public static void registerCompostables() {
        DataUtil.registerCompostable(JustDandyBlocks.FLUFFY_DANDELION.get(), 0.65F);
    }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(JustDandyBlocks.FLUFFY_DANDELION.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JustDandyBlocks.POTTED_FLUFFY_DANDELION.get(), RenderType.cutout());
    }
}
