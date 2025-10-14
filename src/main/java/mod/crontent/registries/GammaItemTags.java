package mod.crontent.registries;

import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class GammaItemTags {

    private GammaItemTags() { /*left empty on purpose*/ }

    public static final TagKey<Item> PILLAGER_DROP_MUSIC_DISCS = TagKey.of(RegistryKeys.ITEM, Utils.id("pillager_drop_music_discs"));
    public static final TagKey<Item> ZOMBIE_VILLAGER_DROP_MUSIC_DISCS = TagKey.of(RegistryKeys.ITEM, Utils.id("zombie_villager_drop_music_discs"));
    public static final TagKey<Item> VILLAGE_CHEST_MUSIC_DISCS = TagKey.of(RegistryKeys.ITEM, Utils.id("village_chest_music_discs"));
    public static final TagKey<Item> RARE_VILLAGE_CHEST_MUSIC_DISCS = TagKey.of(RegistryKeys.ITEM, Utils.id("rare_village_chest_music_discs"));
    public static final TagKey<Item> LOOT_CHEST_MUSIC_DISCS = TagKey.of(RegistryKeys.ITEM, Utils.id("loot_chest_music_discs"));

    public static void initialize(){
        VolumeGamma.LOGGER.info("Registering " + VolumeGamma.MOD_NAME + " tags");

    }
}
