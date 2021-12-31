package com.davigj.just_dandy.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class DandelionFluffParticle extends SpriteTexturedParticle {
    double rand = Math.random() - 0.5D;

    public DandelionFluffParticle(ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.xd = motionX;
        this.yd = motionY + (random.nextDouble() * 0.05D);
        this.zd = motionZ;
        this.lifetime = (random.nextInt(130) * 2) + 30;
        this.quadSize = (float) (0.4F * Math.max(random.nextDouble(), 0.4));
        this.roll = (float) Math.random() * ((float) Math.PI * 2F) * 0.03F;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

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

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite animatedSprite;

        public Factory(IAnimatedSprite animatedSprite) {
            this.animatedSprite = animatedSprite;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            DandelionFluffParticle particle = new DandelionFluffParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.animatedSprite);
            return particle;
        }
    }
}
