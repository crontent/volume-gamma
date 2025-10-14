package mod.crontent;

import mod.crontent.datagen.providers.GammaTagProvider;
import mod.crontent.datagen.providers.GammaJukeboxSongProvider;
import mod.crontent.datagen.providers.GammaModelProvider;
import mod.crontent.datagen.providers.GammaLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VolumeGammaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(GammaJukeboxSongProvider::new);
        pack.addProvider(GammaModelProvider::new);
        pack.addProvider(GammaLootTableProvider::new);
        pack.addProvider(GammaTagProvider::new);
	}
}