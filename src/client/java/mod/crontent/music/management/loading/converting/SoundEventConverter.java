package mod.crontent.music.management.loading.converting;

import mod.crontent.interfaces.SoundListAccess;
import mod.crontent.interfaces.WeightedSoundSetAccess;
import mod.crontent.mixin.client.SoundManagerAnonymousClassMixin;
import mod.crontent.music.definition.entries.BiomeTagMusicEntry;
import mod.crontent.music.definition.entries.MusicEntry;
import mod.crontent.music.definition.entries.SituationalMusicEntry;
import mod.crontent.music.management.loading.MusicDataLoader;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Unique;

public class SoundEventConverter {

    //TODO: Take into account actual weight as defined in the sound
    public static void processSoundsForConversion(SoundListAccess original) {
        original.volume_gamma$getLoadedSounds().forEach((identifier, weightedSoundSet) -> {
            if (identifier.getPath().startsWith("music.")) {
                if (identifier.equals(Identifier.of("music.game"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new BiomeTagMusicEntry(1, "c:is_overworld"))
                            );
                } else if (identifier.equals(Identifier.of("music.menu"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new SituationalMusicEntry(1, "MENU"))
                            );
                } else if (identifier.equals(Identifier.of("music.creative"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new SituationalMusicEntry(1, "CREATIVE"))
                            );
                } else if (identifier.equals(Identifier.of("music.under_water"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new SituationalMusicEntry(1, "UNDERWATER"))
                            );
                }else if (identifier.equals(Identifier.of("music.dragon"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new SituationalMusicEntry(1, "DRAGON"))
                            );
                }else if (identifier.equals(Identifier.of("music.end"))) {
                    ((WeightedSoundSetAccess) weightedSoundSet).volume_gamma$getSounds()
                            .forEach(soundSoundContainer -> processSoundEvent(soundSoundContainer, new SituationalMusicEntry(1, "END"))
                            );
                }else if (identifier.getPath().startsWith("music.")){
                    String biomePart = identifier.getPath().substring(identifier.getPath().lastIndexOf("music.") + 6);
                    //TODO: implement logic for biome based musics
                    //This is accomplished with custom music defs for now
                }
            }
        });
    }

    private static <T extends MusicEntry> void processSoundEvent(SoundContainer<Sound> soundSoundContainer, T entry) {
        if (soundSoundContainer instanceof Sound sound) {
            buildAndPut(entry, sound);
        } else //noinspection ReferenceToMixin
            if (soundSoundContainer instanceof SoundManagerAnonymousClassMixin song) {
            if (song.volume_gamma$field_5595().getRegistrationType() == Sound.RegistrationType.SOUND_EVENT) {
                //TODO: can we be sure at this point that the sound event that is asked for exists?
                WeightedSoundSet soundEventSet = ((SoundListAccess) song.volume_gamma$field_5597()).volume_gamma$getLoadedSounds().get(song.volume_gamma$field_5595().getIdentifier());
                ((WeightedSoundSetAccess) soundEventSet).volume_gamma$getSounds().forEach(soundContainer -> {
                    if (soundContainer instanceof Sound sound) {
                        buildAndPut(entry, sound);
                    }
                });

//                                        Map<Identifier, WeightedSoundSet> filtered = ((SoundListAccess) song.volume_gamma$field_5597()).getLoadedSounds().entrySet()
//                                                .stream()
//                                                .filter(identifierWeightedSoundSetEntry ->
//                                                    identifierWeightedSoundSetEntry.getKey().equals(song.volume_gamma$field_5595().getIdentifier()))
//                                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//                                        System.out.println(filtered);
            }
        }
    }

    private static <T extends MusicEntry> void buildAndPut(T entry, Sound sound) {
        Identifier fullSongId = sound.getSound(Random.create()).getIdentifier();
        String[] nameParts = fullSongId.getPath().split("/");
        String songName = nameParts[nameParts.length - 1];
        entry.setWeight(sound.getWeight());
        MusicDataLoader.INSTANCE.putIntoGenTag(Identifier.of(fullSongId.getNamespace(), songName), entry);
    }
}
