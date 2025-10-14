package mod.crontent.interfaces;

import mod.crontent.music.management.GammaMusicManager;
import mod.crontent.music.management.MusicManagerEnums;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import java.util.HashSet;

public interface MusicStuffAccess {
        public HashSet<MusicManagerEnums.SituationalType> volume_gamma$getCurrentSituations();

        public MusicManagerEnums.Daytime volume_gamma$getCurrentDaytime();

        public RegistryEntry<Biome> volume_gamma$getCurrentExactBiome();

        public HashSet<TagKey<Biome>> volume_gamma$getCurrentSurroundingBiomeTagsIntersection();

        public HashSet<RegistryEntry<Biome>> volume_gamma$getCurrentSurroundingBiomes();

        public GammaMusicManager volume_gamma$getMusicManager();

        public MusicInstance volume_gamma$getCurrentMusicInstance();
}
