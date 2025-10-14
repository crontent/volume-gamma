package mod.crontent;

import mod.crontent.registries.GammaRecordItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class GammaVillagerTrades {

    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 12),
                            new ItemStack(GammaRecordItems.MUSIC_DISC_KENA, 1),
                            1, 25, 0.2f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 8),
                            new ItemStack(GammaRecordItems.MUSIC_DISC_MELANC, 1),
                            1, 25, 0.2f));
                });
        TradeOfferHelper.registerWanderingTraderOffers(wanderingTraderOffersBuilder -> {
            wanderingTraderOffersBuilder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_SPECIAL_ITEMS_POOL,
                    (entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 18),
                            new ItemStack(GammaRecordItems.MUSIC_DISC_KENA, 1),
                            1,  1, 0.2f)
            );
            wanderingTraderOffersBuilder.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_SPECIAL_ITEMS_POOL,
                    (entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 12),
                            new ItemStack(GammaRecordItems.MUSIC_DISC_MELANC, 1),
                            1,  1, 0.2f)
            );
        });
    }
}
