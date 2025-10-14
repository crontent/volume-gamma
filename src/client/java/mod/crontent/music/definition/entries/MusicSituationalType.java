package mod.crontent.music.definition.entries;

import com.mojang.serialization.Lifecycle;
import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;

public record MusicSituationalType() {
    public static final Registry<MusicSituationalType> REGISTRY = new SimpleRegistry<>(
            RegistryKey.ofRegistry(Utils.id("music_situational_types")), Lifecycle.stable()
    );

    public static void initialize() {
        VolumeGamma.LOGGER.info("init Music Situational Registry");
    }
}