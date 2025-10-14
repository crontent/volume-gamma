package mod.crontent.datagen.providers;

import mod.crontent.registries.GammaItemTags;
import mod.crontent.registries.GammaRecordItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class GammaTagProvider extends FabricTagProvider.ItemTagProvider {

    public GammaTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_FLY)
                .add(GammaRecordItems.MUSIC_DISC_BLUUPY);

        valueLookupBuilder(GammaItemTags.PILLAGER_DROP_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_HARVEST);

        valueLookupBuilder(GammaItemTags.ZOMBIE_VILLAGER_DROP_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_LOOFAH);

        valueLookupBuilder(GammaItemTags.LOOT_CHEST_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_WUOP)
                .add(GammaRecordItems.MUSIC_DISC_FLY)
                .add(GammaRecordItems.MUSIC_DISC_BLUUPY);

        valueLookupBuilder(GammaItemTags.VILLAGE_CHEST_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_MELANC)
                .add(GammaRecordItems.MUSIC_DISC_LOOFAH)
                .add(GammaRecordItems.MUSIC_DISC_KENA);


        valueLookupBuilder(GammaItemTags.RARE_VILLAGE_CHEST_MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_HARVEST);



        valueLookupBuilder(ConventionalItemTags.MUSIC_DISCS)
                .add(GammaRecordItems.MUSIC_DISC_WUOP)
                .add(GammaRecordItems.MUSIC_DISC_FLY)
                .add(GammaRecordItems.MUSIC_DISC_HARVEST)
                .add(GammaRecordItems.MUSIC_DISC_MELANC)
                .add(GammaRecordItems.MUSIC_DISC_LOOFAH)
                .add(GammaRecordItems.MUSIC_DISC_KENA)
                .add(GammaRecordItems.MUSIC_DISC_BLUUPY);

    }
}