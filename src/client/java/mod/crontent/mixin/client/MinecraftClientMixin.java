package mod.crontent.mixin.client;

import mod.crontent.Utils;
import mod.crontent.interfaces.ChunkBiomeCounterAccess;
import mod.crontent.interfaces.MusicStuffAccess;
import mod.crontent.music.management.loading.BiomeTagMapper;
import mod.crontent.music.management.GammaMusicManager;
import mod.crontent.music.management.loading.MusicDataLoader;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nullables;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.HashSet;

import static mod.crontent.music.management.MusicManagerEnums.Daytime;
import static mod.crontent.music.management.MusicManagerEnums.SituationalType;


@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements MusicStuffAccess {
    @Shadow
    @Nullable
    public ClientWorld world;

    @Unique
    MusicInstance currentMusicInstance = new MusicInstance(
            new MusicSound(Registries.SOUND_EVENT.getEntry(SoundEvents.INTENTIONALLY_EMPTY), 10, 10, false));


    //TODO: remove or refactor for f3 screen
    @Override
    public HashSet<SituationalType> volume_gamma$getCurrentSituations() {
        return GammaMusicManager.INSTANCE.getSituations();
    }
    @Override
    public Daytime volume_gamma$getCurrentDaytime() {
        return GammaMusicManager.INSTANCE.getDaytime();
    }
    @Override
    public RegistryEntry<Biome> volume_gamma$getCurrentExactBiome() {
        return GammaMusicManager.INSTANCE.getExactBiome();
    }
    @Override
    public HashSet<TagKey<Biome>> volume_gamma$getCurrentSurroundingBiomeTagsIntersection() {
        return GammaMusicManager.INSTANCE.getBiomeTagIntersection();
    }
    @Override
    public HashSet<RegistryEntry<Biome>> volume_gamma$getCurrentSurroundingBiomes() {
        return GammaMusicManager.INSTANCE.getBiomesSurrounding();
    }

    @Override
    public GammaMusicManager volume_gamma$getMusicManager() {
        return GammaMusicManager.INSTANCE;
    }

    @Override
    public MusicInstance volume_gamma$getCurrentMusicInstance() {
        return currentMusicInstance;
    }


    /**
     * @author ahhaha
     * @reason fuck you
     */
    @Overwrite
    public MusicInstance getMusicInstance() {

        //this can only be a value when credits are rolling
        MusicSound musicSound = (MusicSound) Nullables.map(((MinecraftClient) (Object) this).currentScreen, Screen::getMusic);
        if (musicSound != null) {
            return new MusicInstance(musicSound);
        } else {
            fillPlayConditions();

            //TODO:
//            if (GammaMusicManager.INSTANCE.collectUsableEntries()) {
//                GammaMusicManager.INSTANCE.getUsableEntries().forEach(GammaMusicManager.INSTANCE::putMusicEntry);
//                //Only return new music instance when situation actually Changed
//            }
            GammaMusicManager.INSTANCE.collectUsableEntries();
            GammaMusicManager.INSTANCE.getUsableEntries().forEach(GammaMusicManager.INSTANCE::putMusicEntry);
            currentMusicInstance = GammaMusicManager.INSTANCE.getRandomMusicInstance();
            return currentMusicInstance;
        }

//        float f = 1;
//        return new MusicInstance(MusicType.UNDERWATER, f);
    }

    @Unique
    private void fillPlayConditions() {
        //TODO: Should probably do this on a lower frequency or even only on demand
        GammaMusicManager.INSTANCE.reset();

        if (((MinecraftClient) (Object) this).player != null) {
            ClientPlayerEntity player = ((MinecraftClient) (Object) this).player;
            World world = player.getEntityWorld();

            //TODO: Caveat: Modded dimension which might have time cycles are left out of daylight condition usage
            if ((world.getRegistryKey() == World.OVERWORLD)) {
                GammaMusicManager.INSTANCE.setDaytime(world.getTimeOfDay());
            } else GammaMusicManager.INSTANCE.setDaytime(-1);

            GammaMusicManager.INSTANCE.deductSituations(world, player);
            GammaMusicManager.INSTANCE.setExactBiome(world.getBiome(player.getBlockPos()));


            /* Get Surrounding Biomes */

            ChunkPos currentChunkPos = world.getChunk(player.getBlockPos()).getPos();
            //TODO: midpoint circle algo
            int radius = 2;

            //TODO: only take into account a percentage
            for (int x = currentChunkPos.x - radius; x <= currentChunkPos.x + radius; x++) {
                for (int z = currentChunkPos.z - radius; z <= currentChunkPos.z + radius; z++) {
                    HashSet<RegistryEntry<Biome>> present = ((ChunkBiomeCounterAccess) world.getChunk(x, z)).gamma$getBiomesPresent();
                    present.removeIf(biomeRegistryEntry -> biomeRegistryEntry.isIn(ConventionalBiomeTags.IS_CAVE));
                    GammaMusicManager.INSTANCE.addBiomesSurrounding(present);
                }
            }

            ArrayList<HashSet<TagKey<Biome>>> surroundingBiomeTags = new ArrayList<>();

            GammaMusicManager.INSTANCE.getBiomesSurrounding().forEach(biomeRegistryEntry -> {
                HashSet<TagKey<Biome>> tags = BiomeTagMapper.lookup(biomeRegistryEntry);
                surroundingBiomeTags.add(tags);
                //TODO: really general tags like is_overworld (used for game sounds) should only be used IF they are the single available tag, to not skew selection
                //This is achieved below in a really hacky way, assuming game music is stored in c:is_overworld
            });

            HashSet<TagKey<Biome>> biomeTagIntersection = Utils.findIntersection(new HashSet<>(), surroundingBiomeTags.toArray(new HashSet[0]));
            for (TagKey<Biome> biomeTagKey : biomeTagIntersection) {
                TagKey<Biome> overworldTag = TagKey.of(RegistryKeys.BIOME, Identifier.of("c", "is_overworld"));
                if(!biomeTagKey.equals(overworldTag) && MusicDataLoader.INSTANCE.definedTagKeys.contains(biomeTagKey)){
                    biomeTagIntersection.remove(overworldTag);
                    break;
                }
            }
            GammaMusicManager.INSTANCE.setBiomeTagIntersection(biomeTagIntersection);

        } else {
            GammaMusicManager.INSTANCE.addSituation(SituationalType.MENU);
        }
    }


}
