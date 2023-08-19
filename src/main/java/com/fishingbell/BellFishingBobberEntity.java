package com.fishingbell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class BellFishingBobberEntity extends FishingBobberEntity {
    public BellFishingBobberEntity(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
    }

    public BellFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel) {
        super(FishingBell.BELL_FISHING_BOBBER, world, luckOfTheSeaLevel, lureLevel);
        this.setOwner(thrower);
        float f = thrower.getPitch();
        float g = thrower.getYaw();
        float h = MathHelper.cos(-g * 0.017453292F - 3.1415927F);
        float i = MathHelper.sin(-g * 0.017453292F - 3.1415927F);
        float j = -MathHelper.cos(-f * 0.017453292F);
        float k = MathHelper.sin(-f * 0.017453292F);
        double d = thrower.getX() - (double)i * 0.3;
        double e = thrower.getEyeY();
        double l = thrower.getZ() - (double)h * 0.3;
        this.refreshPositionAndAngles(d, e, l, g, f);
        Vec3d vec3d = new Vec3d((double)(-i), (double)MathHelper.clamp(-(k / j), -5.0F, 5.0F), (double)(-h));
        double m = vec3d.length();
        vec3d = vec3d.multiply(0.6 / m + this.random.nextTriangular(0.5, 0.0103365), 0.6 / m + this.random.nextTriangular(0.5, 0.0103365), 0.6 / m + this.random.nextTriangular(0.5, 0.0103365));
        this.setVelocity(vec3d);
        this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875));
        this.setPitch((float)(MathHelper.atan2(vec3d.y, vec3d.horizontalLength()) * 57.2957763671875));
        this.prevYaw = this.getYaw();
        this.prevPitch = this.getPitch();
    }

    private boolean last = false;

    private void ring()
    {
        getWorld().playSound(null, Objects.requireNonNull(getPlayerOwner()).getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0f, 1.0f);
    }

    @Override
    public void tick() {
        super.tick();
        if (!last && caughtFish)
        {
            last = caughtFish;
            ring();
        }
        else if (!caughtFish)
        {
            last = caughtFish;
        }
    }

    @Override
    protected boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(FishingBell.BELL_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(FishingBell.BELL_FISHING_ROD);
        if (player.isRemoved() || !player.isAlive() || !bl && !bl2 || this.squaredDistanceTo(player) > 4096.0) {
            this.discard();
            return true;
        }
        return false;
  }
}

