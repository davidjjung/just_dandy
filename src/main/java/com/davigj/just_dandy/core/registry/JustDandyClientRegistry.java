package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.client.particle.DandelionFluffParticle;
import com.davigj.just_dandy.core.JustDandyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = JustDandyMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JustDandyClientRegistry {

    @SubscribeEvent
    public static void registerParticleFactorys(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particleEngine;
        registerParticleFactory(manager, JustDandyParticles.DANDELION_FLUFF, DandelionFluffParticle.Factory::new);
    }

    private static <T extends IParticleData> void registerParticleFactory(ParticleManager manager, RegistryObject<BasicParticleType> particleTypeIn, ParticleManager.IParticleMetaFactory<BasicParticleType> particleMetaFactoryIn) {
        if (checkForNonNull(particleTypeIn)) manager.register(particleTypeIn.get(), particleMetaFactoryIn);
    }

    private static boolean checkForNonNull(RegistryObject<BasicParticleType> registryObject) {
        return ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, registryObject, "value") != null;
    }
}
