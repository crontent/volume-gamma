package mod.crontent;

import mod.crontent.music.debug.MyDebugHudEntry;
import mod.crontent.music.definition.MusicDefinition;
import mod.crontent.music.definition.entries.MusicEntryType;
import mod.crontent.music.definition.entries.MusicEntryTypes;
import mod.crontent.music.definition.entries.MusicSituationalType;
import mod.crontent.music.management.loading.BiomeTagMapper;
import mod.crontent.music.management.loading.MusicDataLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.registry.*;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VolumeGammaClient implements ClientModInitializer {
    public static final String MOD_ID = "volume_gamma";
    public static final String MOD_NAME = "Volume Gamma";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static final RegistryKey<Registry<MusicDefinition>> MUSIC_DEFINITION_KEY = RegistryKey.ofRegistry(Utils.id("music_definition"));
    public static final RegistryKey<Registry<MusicEntryType>> MUSIC_ENTRY_TYPE_KEY = RegistryKey.ofRegistry(Utils.id("music_entry_type"));


    @Override
    public void onInitializeClient() {
        MusicEntryType.initialize();
        MusicEntryTypes.initialize();
        MusicSituationalType.initialize();

        //DynamicRegistries.registerSynced(MUSIC_DEFINITION_KEY, MusicDefinition.CODEC)

        ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(MusicDataLoader.ID, MusicDataLoader.INSTANCE);

        DebugHudEntries.register(Utils.id("gamma_music"), new MyDebugHudEntry());

        CommonLifecycleEvents.TAGS_LOADED.register(BiomeTagMapper::generateMap);
    }
}