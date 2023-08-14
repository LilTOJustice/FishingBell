package com.fishingbell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class BellFishingBobberEntity extends FishingBobberEntity {
    public BellFishingBobberEntity(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
    }

    public BellFishingBobberEntity(PlayerEntity user, World world, int j, int i) {
        super(user, world, j, i);
    }

    private boolean last = false;

    private void ring()
    {
        getWorld().playSound(null, getPlayerOwner().getBlockPos(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0f, 1.0f);
    }

    @Override
    public void tick() {
        super.tick();
        if (last == false && caughtFish == true)
        {
            last = caughtFish;
            ring();
        }
        else if (caughtFish == false)
        {
            last = caughtFish;
        }
    }

    @Override
    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(Items.BELL_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(Items.BELL_FISHING_ROD);
        if (player.isRemoved() || !player.isAlive() || !bl && !bl2 || this.squaredDistanceTo(player) > 1024.0) {
            this.discard();
            return true;
        }
        return false;
  }
}

