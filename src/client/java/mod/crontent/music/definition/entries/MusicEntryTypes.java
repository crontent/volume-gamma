package mod.crontent.music.definition.entries;

import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.minecraft.registry.Registry;

public class MusicEntryTypes {

    public static final MusicEntryType<BiomeTagMusicEntry> BIOME_TAG = register("biome_tag", new MusicEntryType<>(BiomeTagMusicEntry.CODEC));
    public static final MusicEntryType<BiomeMusicEntry> BIOME = register("biome", new MusicEntryType<>(BiomeMusicEntry.CODEC));
    public static final MusicEntryType<SituationalMusicEntry> SITUATIONAL = register("situational",  new MusicEntryType<>(SituationalMusicEntry.CODEC));
    public static final MusicEntryType<DaytimeMusicEntry> DAYTIME = register("daytime", new MusicEntryType<>(DaytimeMusicEntry.CODEC));

    private static <T extends MusicEntry> MusicEntryType<T> register(String id, MusicEntryType<T> musicEntryType) {
        return Registry.register(MusicEntryType.REGISTRY, Utils.id(id),musicEntryType);
    }


    public static void initialize() {
        VolumeGamma.LOGGER.info("init Music Entry Registry");
    }
}
