package mod.crontent.music.definition.entries;

import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;
import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;

public record MusicEntryType<T extends MusicEntry>(MapCodec<T> codec) {

    public static final Registry<MusicEntryType<?>> REGISTRY = new SimpleRegistry<>(
            RegistryKey.ofRegistry(Utils.id("music_entry_type")), Lifecycle.stable()
    );

    public static void initialize() {
        VolumeGamma.LOGGER.info("init Music Entry Registry");
    }
}
