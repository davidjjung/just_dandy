package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.client.particle.DandelionFluffParticle;
import com.davigj.just_dandy.core.JustDandy;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(modid = JustDandy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JDParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JustDandy.MODID);

    public static final RegistryObject<SimpleParticleType> DANDELION_FLUFF = PARTICLE_TYPES.register("dandelion_fluff", () -> new SimpleParticleType(true));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.register(DANDELION_FLUFF.get(), DandelionFluffParticle.Factory::new);
    }
}
