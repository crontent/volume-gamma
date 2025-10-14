package mod.crontent.music.management.loading;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import java.util.*;

public class BiomeTagMapper {
    public static HashMap<RegistryEntry<Biome>, Set<TagKey<Biome>>> BIOME_TAG_MAP;

    public static void generateMap(DynamicRegistryManager registryManager, boolean b) {
        BIOME_TAG_MAP = new HashMap<>();
        registryManager.getOrThrow(RegistryKeys.BIOME).streamTags().forEach(registryEntries -> {
            TagKey<Biome> tag = registryEntries.getTag();
            registryEntries.forEach(biomeRegistryEntry -> {
                putCurrentEntryAndId(BIOME_TAG_MAP, tag, biomeRegistryEntry);
            });
        });
    }

    private static void putCurrentEntryAndId(Map<RegistryEntry<Biome>, Set<TagKey<Biome>>> map, TagKey<Biome> currentKey, RegistryEntry<Biome> currentEntry) {
        map.putIfAbsent(currentEntry, new HashSet<>());
        map.get(currentEntry).add(currentKey);
    }

    public static HashSet<TagKey<Biome>> lookup(RegistryEntry<Biome> biomeRegistryEntry) {
        return (HashSet<TagKey<Biome>>) BIOME_TAG_MAP.get(biomeRegistryEntry);
    }
}
