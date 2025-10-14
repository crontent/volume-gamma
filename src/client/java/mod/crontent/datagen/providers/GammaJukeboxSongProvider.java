package mod.crontent.datagen.providers;

import mod.crontent.registries.GammaSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.concurrent.CompletableFuture;

public class GammaJukeboxSongProvider extends FabricDynamicRegistryProvider {
    public GammaJukeboxSongProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Jukebox Songs";
    }

    @Override
    protected void configure(WrapperLookup registries, Entries entries) {
        register(entries, GammaSounds.WUOP, GammaSounds.MUSIC_WUOP, 194, 1);
        register(entries, GammaSounds.FLY, GammaSounds.MUSIC_FLY, 173, 1);
        register(entries, GammaSounds.HARVEST, GammaSounds.MUSIC_HARVEST, 165, 1);
        register(entries, GammaSounds.MELANC, GammaSounds.MUSIC_MELANC, 229, 1);
        register(entries, GammaSounds.LOOFAH, GammaSounds.MUSIC_LOOFAH, 81, 1);
        register(entries, GammaSounds.KENA, GammaSounds.MUSIC_KENA, 142, 1);
        register(entries, GammaSounds.BLUUPY, GammaSounds.MUSIC_BLUUPY, 196, 1);
    }

    private void register(
            Entries entries,
            RegistryKey<JukeboxSong> key,
            RegistryEntry.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        entries.add(key, new JukeboxSong(soundEvent, Text.translatable(Util.createTranslationKey("jukebox_song", key.getValue())), lengthInSeconds, comparatorOutput));
    }

}
