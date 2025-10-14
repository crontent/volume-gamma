package mod.crontent.registries;

import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

public class GammaRecordItems {


    private GammaRecordItems() { /*left empty on purpose*/ }

    public static final Item MUSIC_DISC_WUOP =
            registerRecord("music_disc_wuop", new Item.Settings().rarity(Rarity.RARE), GammaSounds.WUOP);
    public static final Item MUSIC_DISC_FLY =
            registerRecord("music_disc_fly", new Item.Settings().rarity(Rarity.RARE), GammaSounds.FLY);
    public static final Item MUSIC_DISC_HARVEST =
            registerRecord("music_disc_harvest", new Item.Settings().rarity(Rarity.RARE), GammaSounds.HARVEST);
    public static final Item MUSIC_DISC_MELANC =
            registerRecord("music_disc_melanc", new Item.Settings().rarity(Rarity.RARE), GammaSounds.MELANC);
    public static final Item MUSIC_DISC_LOOFAH =
            registerRecord("music_disc_loofah", new Item.Settings().rarity(Rarity.RARE), GammaSounds.LOOFAH);
    public static final Item MUSIC_DISC_KENA =
            registerRecord("music_disc_kena", new Item.Settings().rarity(Rarity.RARE), GammaSounds.KENA);
    public static final Item MUSIC_DISC_BLUUPY =
            registerRecord("music_disc_bluupy", new Item.Settings().rarity(Rarity.RARE), GammaSounds.BLUUPY);

    public static void initialize() {
        VolumeGamma.LOGGER.info("Registering " + VolumeGamma.MOD_NAME + " items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_WUOP));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_FLY));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_HARVEST));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_MELANC));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_LOOFAH));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_KENA));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((ig) -> ig.add(MUSIC_DISC_BLUUPY));
    }

    public static Item registerRecord(String id, Item.Settings settings, RegistryKey<JukeboxSong> jukeboxKey){
        return Registry.register(Registries.ITEM, Utils.id(id),
                new Item(settings
                        .jukeboxPlayable(jukeboxKey)
                        .maxCount(1)
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Utils.id(id)))));
    }
}
