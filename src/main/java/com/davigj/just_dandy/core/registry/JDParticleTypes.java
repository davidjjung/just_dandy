package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.client.particle.DandelionFluffParticle;
import com.davigj.just_dandy.core.JustDandy;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = JustDandy.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JDParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, JustDandy.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DANDELION_FLUFF = PARTICLE_TYPES.register("dandelion_fluff", () -> new SimpleParticleType(false));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DANDELION_FLUFF.get(), DandelionFluffParticle.Factory::new);
    }
}
