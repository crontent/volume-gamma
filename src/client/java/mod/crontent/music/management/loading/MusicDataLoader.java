package mod.crontent.music.management.loading;

import mod.crontent.Utils;
import mod.crontent.music.definition.MusicDefinition;
import mod.crontent.music.definition.entries.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.biome.Biome;

import java.util.*;

public class MusicDataLoader extends JsonDataLoader<MusicDefinition> {
    public static final MusicDataLoader INSTANCE = new MusicDataLoader();
    public static final Identifier ID = Utils.id("music_definition_manager");
    public static final String DATA_TYPE = "music_definitions";

    private Map<Identifier, MusicDefinition> musicDefinitions;
    //Identifier here is the Sound event id
    //TODO: this should prolly be private and gotten via getter
    public final Map<MusicEntry, Set<Identifier>> biomeMap = new HashMap<>();
    public final Map<MusicEntry, Set<Identifier>> biomeTagMap = new HashMap<>();
    public final Map<MusicEntry, Set<Identifier>> situationalMap = new HashMap<>();
    public final Map<MusicEntry, Set<Identifier>> daytimeMap = new HashMap<>();

    public final Map<MusicEntry, Set<Identifier>> generatedBiomeMap = new HashMap<>();
    public final Map<MusicEntry, Set<Identifier>> generatedBiomeTagMap = new HashMap<>();
    public final Map<MusicEntry, Set<Identifier>> generatedSituationalMap = new HashMap<>();

    public final Set<TagKey<Biome>> definedTagKeys = new HashSet<>();


    public MusicDataLoader() {
        super(MusicDefinition.CODEC, ResourceFinder.json(DATA_TYPE));
    }

    @Override
    public void apply(Map<Identifier, MusicDefinition> prepared, ResourceManager manager, Profiler profiler) {
        musicDefinitions = Map.copyOf(prepared);
        calcReverseMap();
    }

    private void calcReverseMap() {
        biomeMap.clear();
        biomeMap.putAll(generatedBiomeMap);

        biomeTagMap.clear();
        biomeTagMap.putAll(generatedBiomeTagMap);
        definedTagKeys.clear();

        situationalMap.clear();
        situationalMap.putAll(generatedSituationalMap);

        daytimeMap.clear();
        musicDefinitions.forEach((identifier, musicDefinition) ->
        {
            musicDefinition.playConditions().forEach(currentEntry ->
            {
                if(currentEntry.getType().equals(MusicEntryTypes.BIOME)){
                    putCurrentEntryAndId(biomeMap, identifier, currentEntry);
                }else if(currentEntry.getType().equals(MusicEntryTypes.BIOME_TAG)){
                    putCurrentEntryAndId(biomeTagMap, identifier, currentEntry);
                    definedTagKeys.add(((BiomeTagMusicEntry) currentEntry).getTagKey());
                }else if(currentEntry.getType().equals(MusicEntryTypes.SITUATIONAL)){
                    putCurrentEntryAndId(situationalMap, identifier, currentEntry);
                }else if(currentEntry.getType().equals(MusicEntryTypes.DAYTIME)){
                    putCurrentEntryAndId(daytimeMap, identifier, currentEntry);
                }
            });
        });
    }

    private static void putCurrentEntryAndId(Map<MusicEntry, Set<Identifier>> map, Identifier id, MusicEntry currentEntry) {
        map.computeIfAbsent(currentEntry, musicEntry -> new HashSet<>());
        map.get(currentEntry).add(id);
    }

    public void putIntoGenTag(Identifier id, MusicEntry entry){
        switch(entry){
            case BiomeTagMusicEntry castEntry -> putCurrentEntryAndId(generatedBiomeTagMap, id, castEntry);
            case SituationalMusicEntry castEntry -> putCurrentEntryAndId(generatedSituationalMap, id, castEntry);
            case BiomeMusicEntry castEntry -> putCurrentEntryAndId(generatedBiomeMap, id, castEntry);
            default -> throw new IllegalStateException("Unexpected value: " + entry);
        }
    }


    public boolean contains(Identifier id){
        return musicDefinitions.containsKey(id);
    }

    public MusicDefinition get(Identifier id){
        MusicDefinition d = musicDefinitions.get(id);
        if (d == null) {
            throw new IllegalArgumentException("Cannot find music definition for '%s', it will not play".formatted(id));
        }
        return d;
    }

    public Set<Identifier> getKeySet() {
        return musicDefinitions.keySet();
    }


}
