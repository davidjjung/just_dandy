package com.davigj.just_dandy.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DandelionFluffParticle extends TextureSheetParticle {
    double rand = Math.random() - 0.5D;
    protected final SpriteSet animatedSprite;

    public DandelionFluffParticle(SpriteSet animatedSprite, ClientLevel world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.xd = motionX;
        this.yd = motionY + (random.nextDouble() * 0.05D);
        this.zd = motionZ;
        this.animatedSprite = animatedSprite;
        this.lifetime = random.nextInt(75) + 75;
        this.quadSize = 0.35F * (this.random.nextFloat() * 0.5F + 0.5F);
        this.pickSprite(animatedSprite);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.age++;
        if (this.age >= this.lifetime) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
            this.yd -= 0.04D * (double)this.gravity * Math.random();
            this.yd = Math.max(this.yd, -0.1F);

            this.oRoll = this.roll;
            if (!this.onGround) {
                this.roll += (float) (rand * 0.04 * ((double) (Math.asin(Math.sin(0.15 * this.age)))));
            } else {
                this.xd *= (double)0.7F;
                this.zd *= (double)0.7F;
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float partialTick) {
        float f = this.lifetime / (((this.age + (this.lifetime * 0.5F)) + partialTick));
        f = Mth.clamp(f, 0F, 0.5F);
        int i = super.getLightColor(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int) (f * 15f * 16f);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet animatedSprite;

        public Factory(SpriteSet animatedSprite) {
            this.animatedSprite = animatedSprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new DandelionFluffParticle(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
