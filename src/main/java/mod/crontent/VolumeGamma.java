package mod.crontent;

import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import mod.crontent.events.GammaEventListeners;
import mod.crontent.registries.GammaRecordItems;
import mod.crontent.registries.GammaSounds;
import mod.crontent.registries.GammaItemTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class VolumeGamma implements ModInitializer {
	public static final String MOD_ID = "volume_gamma";
    public static final String MOD_NAME = "Volume Gamma";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Init" + MOD_NAME);

        GammaSounds.initialize();
        GammaRecordItems.initialize();
        GammaEventListeners.initialize();
        GammaItemTags.initialize();
        GammaVillagerTrades.registerCustomTrades();
	}
}
