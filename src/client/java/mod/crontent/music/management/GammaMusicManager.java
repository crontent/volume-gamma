package mod.crontent.music.management;

import mod.crontent.music.management.loading.MusicDataLoader;
import mod.crontent.music.definition.entries.*;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static mod.crontent.music.management.MusicManagerEnums.*;

public class GammaMusicManager {
    public static final GammaMusicManager INSTANCE = new GammaMusicManager();
    private final Random randomInstance = Random.create();
    HashSet<SituationalType> situations;
    Daytime daytime;
    RegistryEntry<Biome> exactBiome;
    HashSet<TagKey<Biome>> biomeTagIntersection = new HashSet<>();
    HashSet<RegistryEntry<Biome>> biomesSurrounding = new HashSet<>();


    private Map<MusicEntry, Set<Identifier>> usableEntries = new HashMap<>();


    //TODO: only apply for height dependent biomes
    Predicate<RegistryEntry<Biome>> shouldBiomeIncludedInTags = biome -> !biome.isIn(ConventionalBiomeTags.IS_CAVE);

    public HashMap<MusicSound, Integer> songPool;


    public GammaMusicManager() {
        setAllToEmpty();
    }

    private void setAllToEmpty() {
        reset();

    }

    public void reset() {
        this.situations = new HashSet<>();
        this.daytime = Daytime.NONAPPLICABLE;
        this.exactBiome = null;
        this.biomeTagIntersection = new HashSet<>();
        this.biomesSurrounding = new HashSet<>();
        this.songPool = new HashMap<>();
    }

    public HashSet<SituationalType> getSituations() {
        return situations;
    }

    public Daytime getDaytime() {
        return daytime;
    }

    public RegistryEntry<Biome> getExactBiome() {
        return exactBiome;
    }

    public HashSet<TagKey<Biome>> getBiomeTagIntersection() {
        return biomeTagIntersection;
    }

    public HashSet<RegistryEntry<Biome>> getBiomesSurrounding() {
        return biomesSurrounding;
    }

    public HashMap<MusicSound, Integer> getSongPool() {
        return songPool;
    }

    public Map<MusicEntry, Set<Identifier>> getUsableEntries() {
        return usableEntries;
    }

    public void addSituation(SituationalType situation) {
        this.situations.add(situation);
    }

    public void setDaytime(long daytime) {
        this.daytime = Daytime.getDaytime(daytime);
    }

    public void setExactBiome(RegistryEntry<Biome> exactBiome) {
        this.exactBiome = exactBiome;
    }

    public void setBiomeTagIntersection(HashSet<TagKey<Biome>> biomeTagIntersection) {
        this.biomeTagIntersection = biomeTagIntersection;
    }

    public void addBiomesSurrounding(HashSet<RegistryEntry<Biome>> biomesSurrounding) {
        this.biomesSurrounding.addAll(biomesSurrounding);
    }

    public void addBiomesSurrounding(RegistryEntry<Biome> biomesSurrounding) {
        this.biomesSurrounding.add(biomesSurrounding);
    }

    public void deductSituations(World world, ClientPlayerEntity player) {
        if (world.getRegistryKey() == World.END) {
            if (MinecraftClient.getInstance().inGameHud.getBossBarHud().shouldPlayDragonMusic()) {
                addSituation(SituationalType.DRAGON);
            } else {
                addSituation(SituationalType.END);
            }
        }
        if (world.getRegistryKey() != World.NETHER && player.getAbilities().creativeMode && player.getAbilities().allowFlying) {
            addSituation(SituationalType.CREATIVE);
        }
        if (player.isSubmergedInWater()) {
            //TODO: better underwater detection
            addSituation(SituationalType.UNDERWATER);
        }

        //TODO: This is crude, might be better to work with POI or structure but it's not accessible on client
        Iterable<Entity> listOfEntities = ((ClientWorld) world).getEntities();
        for (Entity renderedEntity : listOfEntities) {
            if (renderedEntity instanceof VillagerEntity v && v.distanceTo(player) <= 10f) {
                addSituation(SituationalType.VILLAGE);
            }
        }
    }

    /**
     * @return if the usableEntries actually changed from before
     */
    public boolean collectUsableEntries() {
        Map<MusicEntry, Set<Identifier>> newUsableEntries = new HashMap<>();

        if (!biomesSurrounding.isEmpty()) {
            newUsableEntries = MusicDataLoader.INSTANCE.biomeTagMap.entrySet().stream()
                    .filter(entry ->
                            biomeTagIntersection.contains(((BiomeTagMusicEntry) entry.getKey()).getTagKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (exactBiome != null) {
            newUsableEntries.putAll(MusicDataLoader.INSTANCE.biomeMap.entrySet().stream()
                    .filter(entry ->
                            exactBiome.equals(((BiomeMusicEntry) entry.getKey()).getBiomeRef()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }

        if (!situations.isEmpty()) {
            newUsableEntries.putAll(MusicDataLoader.INSTANCE.situationalMap.entrySet().stream()
                    .filter(entry ->
                            situations.contains(((SituationalMusicEntry) entry.getKey()).getSituation()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }

        if (daytime != Daytime.NONAPPLICABLE) {
            newUsableEntries.putAll(MusicDataLoader.INSTANCE.daytimeMap.entrySet().stream()
                    .filter(entry ->
                            daytime.equals(((DaytimeMusicEntry) entry.getKey()).getDaytime()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            );
        }

//        if(newUsableEntries.equals(usableEntries)){
//            return false;
//        }
        usableEntries = newUsableEntries;
        return true;
    }

    public MusicInstance getRandomMusicInstance() {
        //return new MusicInstance(MusicType.UNDERWATER, 1);
        MusicSound selection = getRandomWeightedElement(songPool);
        if (selection == null) selection = new MusicSound(Registries.SOUND_EVENT.getEntry(SoundEvents.INTENTIONALLY_EMPTY), 10, 10, false);
        return new MusicInstance(selection, 1f);
    }

    public void putMusicEntry(MusicEntry musicEntry, Set<Identifier> songEvents) {
        for (Identifier songEvent : songEvents) {
            MusicSound musicType = musicEntry.getMusicType(songEvent);
            int weight = musicEntry.getWeight();

            if (!songPool.containsKey(musicType)) {
                songPool.put(musicType, weight);
            } else {
                songPool.put(musicType, songPool.get(musicType) + weight);
            }
        }
    }

    /**
     *
     * @param objects: A Map containing the objects to choose from, where the keys represent the objects and the values represent their respective weights.
     * @param <T>      A randomly selected element from the Map, or null if the Map is empty.
     *                           TODO: this assumes positive values for weight
     * @return Returns a randomly selected element from a Map, with selection probabilities proportional to each element's weight.
     */
    public <T> T getRandomWeightedElement(Map<T, Integer> objects) {
        if (objects.isEmpty()) return null;

        int sum = objects.values().stream().mapToInt(Integer::intValue).sum();
        int random = randomInstance.nextInt(sum);
        for (T item : objects.keySet()) {
            random -= objects.get(item);
            if (random < 0) {
                return item;
            }
        }
        return null;
    }
}
