package com.fishingbell;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FishingBell implements ModInitializer {
    public static final EntityType<BellFishingBobberEntity> BELL_FISHING_BOBBER =
		Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("fishingbell", "bell_fishing_bobber"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<BellFishingBobberEntity>)BellFishingBobberEntity::new).disableSaving().disableSummon().dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(5).build()
		);

	public static final Item BELL_FISHING_ROD =
		Registry.register(Registries.ITEM,
		new Identifier("fishingbell", "bell_fishing_rod"),
		new BellFishingRodItem(new FabricItemSettings().maxCount(1).maxDamage(64))
	);

	@Override
	public void onInitialize() {}
}