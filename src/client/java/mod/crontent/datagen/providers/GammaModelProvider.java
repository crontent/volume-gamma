package mod.crontent.datagen.providers;

import mod.crontent.registries.GammaRecordItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class GammaModelProvider extends FabricModelProvider{
    public GammaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_WUOP, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_FLY, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_HARVEST, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_MELANC, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_LOOFAH, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_KENA, Models.GENERATED);
        itemModelGenerator.register(GammaRecordItems.MUSIC_DISC_BLUUPY, Models.GENERATED);
    }
}
