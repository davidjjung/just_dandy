package com.davigj.just_dandy.core.registry;

import com.davigj.just_dandy.core.JustDandyMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JustDandyParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, JustDandyMod.MOD_ID);

    public static final RegistryObject<BasicParticleType> DANDELION_FLUFF = PARTICLE_TYPES.register(
            "dandelion_fluff", () -> new BasicParticleType(true));
}
