package com.fishingbell;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;

import static com.fishingbell.FishingBell.BELL_FISHING_BOBBER;
import static com.fishingbell.FishingBell.BELL_FISHING_ROD;

public class FishingBellClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(BELL_FISHING_ROD, new Identifier("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null || !(livingEntity instanceof PlayerEntity)) {
				return 0.0F;
			}

			if (livingEntity.getMainHandStack() == itemStack)
			{
				return livingEntity.getMainHandStack().isOf(BELL_FISHING_ROD) && ((PlayerEntity)livingEntity).fishHook != null ? 1.0F : 0.0F;
			}
			else if (livingEntity.getOffHandStack() == itemStack)
			{
				return livingEntity.getOffHandStack().isOf(BELL_FISHING_ROD) && ((PlayerEntity)livingEntity).fishHook != null ? 1.0F : 0.0F;
			}

			return 0.0F;
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.add(BELL_FISHING_ROD);
		});

		EntityRendererRegistry.register(BELL_FISHING_BOBBER, (context) -> new BellFishingBobberEntityRenderer(context));
	}
}