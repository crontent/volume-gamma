package mod.crontent.events;

import mod.crontent.registries.GammaItemTags;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class GammaEventListeners {

    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            RegistryEntryLookup<EntityType<?>> registryEntryLookup = registries.getOrThrow(RegistryKeys.ENTITY_TYPE);

            if (source.isBuiltin()) {
                if (Identifier.ofVanilla("entities/pillager").equals(key.getValue())) {
                    LootPool.Builder pool = LootPool.builder()
                            .with(TagEntry.expandBuilder(GammaItemTags.PILLAGER_DROP_MUSIC_DISCS))
                            .conditionally(
                                    EntityPropertiesLootCondition.builder(
                                            LootContext.EntityReference.ATTACKER, EntityPredicate.Builder.create().type(registryEntryLookup, EntityType.WITCH)
                                    )
                            );
                    tableBuilder.pool(pool);
                }
                if (Identifier.ofVanilla( "entities/zombie_villager").equals(key.getValue())) {
                    LootPool.Builder pool = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(TagEntry.expandBuilder(GammaItemTags.ZOMBIE_VILLAGER_DROP_MUSIC_DISCS))
                            .conditionally(RandomChanceLootCondition.builder(.33f))
                            .conditionally(
                                    EntityPropertiesLootCondition.builder(
                                            LootContext.EntityReference.ATTACKER, EntityPredicate.Builder.create().type(registryEntryLookup, EntityType.IRON_GOLEM)
                                    )
                            );
                    tableBuilder.pool(pool);
                }
                if (Identifier.ofVanilla("chests/simple_dungeon").equals(key.getValue())) {
//                    tableBuilder.modifyPools(builder -> {
//                        LootPool pool = builder.build();
//                        for (LootPoolEntry entry : pool.entries) {
//                            System.out.println(((ItemEntryAccessor) entry).getItem().getIdAsString());
//                            System.out.println(((ItemEntryAccessor) entry).getItem().isIn(ConventionalItemTags.MUSIC_DISCS));
//                            if (entry instanceof ItemEntryAccessor itemEntry && itemEntry.getItem().isIn(ConventionalItemTags.MUSIC_DISCS)){
//                                System.out.println("We are inserting");
//                                builder.with(TagEntry.expandBuilder(GammaItemTags.ZOMBIE_VILLAGER_DROP_MUSIC_DISCS));
//                                break;
//                            }
//                        }
//                    });
                    LootPool.Builder pool = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(TagEntry.expandBuilder(GammaItemTags.LOOT_CHEST_MUSIC_DISCS))
                            .conditionally(RandomChanceLootCondition.builder(.02f));
                    tableBuilder.pool(pool);
                }
                
                if (key.getValue().getPath().contains("chests/village/village_")) {
                    //TODO: Does this actually work?
                    LootPool.Builder pool = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(TagEntry.expandBuilder(GammaItemTags.VILLAGE_CHEST_MUSIC_DISCS).weight(10))
                            .with(TagEntry.expandBuilder(GammaItemTags.RARE_VILLAGE_CHEST_MUSIC_DISCS).weight(1))
                            .conditionally(RandomChanceLootCondition.builder(.01f));
                    tableBuilder.pool(pool);
                }
            }

        });

    }
}
