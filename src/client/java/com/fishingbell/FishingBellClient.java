package com.fishingbell;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;

import static com.fishingbell.FishingBell.BELL_FISHING_ROD;

public class FishingBellClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(BELL_FISHING_ROD, new Identifier("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
			if (livingEntity == null) {
				return 0.0F;
			}

			return ((PlayerEntity)livingEntity).fishHook != null ? 1.0F : 0.0F;
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.add(BELL_FISHING_ROD);
		});
	}
}