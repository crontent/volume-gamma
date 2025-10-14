package mod.crontent.registries;

import mod.crontent.Utils;
import mod.crontent.VolumeGamma;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class GammaSounds {
    private GammaSounds() { /*left empty on purpose*/ }

    //BACKGROUND MUSIC
    public static final SoundEvent MUSIC_FARVEL_MEMORY = registerSound("music.farvel_memory");
    public static final SoundEvent MUSIC_WIND = registerSound("music.wind");
    public static final SoundEvent MUSIC_SCRAT = registerSound("music.scrat");
    public static final SoundEvent MUSIC_LOCK = registerSound("music.lock");
    public static final SoundEvent MUSIC_TWO_NOTE_DANCE = registerSound("music.two_note_dance");
    public static final SoundEvent MUSIC_GOM = registerSound("music.gom");
    public static final SoundEvent MUSIC_FLOTSAM = registerSound("music.flotsam");
    public static final SoundEvent MUSIC_AXIOM = registerSound("music.axiom");
    public static final SoundEvent MUSIC_WATERFALL_NOVELA = registerSound("music.waterfall_novela");
    public static final SoundEvent MUSIC_MAIN_MENU = registerSound("music.main_menu");
    public static final SoundEvent MUSIC_NY = registerSound("music.ny");
    public static final SoundEvent MUSIC_BUMPY = registerSound("music.bumpy");
    public static final SoundEvent MUSIC_NOKK = registerSound("music.nokk");
    public static final SoundEvent MUSIC_GUENZBURG = registerSound("music.guenzburg");
    public static final SoundEvent MUSIC_RED_FORTRESS = registerSound("music.red_fortress");

    //VANILLA SONG INDIVIDUAL SOUND EVENTS
    //TODO: load this dymanically...
    public static final SoundEvent MUSIC_YAKUSOKU = registerSoundOfVanilla("music.yakusoku");
    public static final SoundEvent MUSIC_WET_HANDS = registerSoundOfVanilla("music.wet_hands");
    public static final SoundEvent MUSIC_WENDING = registerSoundOfVanilla("music.wending");
    public static final SoundEvent MUSIC_SHUNIJI = registerSoundOfVanilla("music.shuniji");
    public static final SoundEvent MUSIC_DRAGON_FISH = registerSoundOfVanilla("music.dragon_fish");
    public static final SoundEvent MUSIC_AXOLOTL = registerSoundOfVanilla("music.axolotl");
    public static final SoundEvent MUSIC_WATCHER = registerSoundOfVanilla("music.watcher");
    public static final SoundEvent MUSIC_SWEDEN = registerSoundOfVanilla("music.sweden");
    public static final SoundEvent MUSIC_LABYRINTHINE = registerSoundOfVanilla("music.labyrinthine");
    public static final SoundEvent MUSIC_FIREBUGS = registerSoundOfVanilla("music.firebugs");
    public static final SoundEvent MUSIC_AERIE = registerSoundOfVanilla("music.aerie");
    public static final SoundEvent MUSIC_SUBWOOFER_LULLABY = registerSoundOfVanilla("music.subwoofer_lullaby");
    public static final SoundEvent MUSIC_STAND_TALL = registerSoundOfVanilla("music.stand_tall");
    public static final SoundEvent MUSIC_PUZZLEBOX = registerSoundOfVanilla("music.puzzlebox");
    public static final SoundEvent MUSIC_POKOPOKO = registerSoundOfVanilla("music.pokopoko");
    public static final SoundEvent MUSIC_OXYGENE = registerSoundOfVanilla("music.oxygene");
    public static final SoundEvent MUSIC_OS_PIANO = registerSoundOfVanilla("music.os_piano");
    public static final SoundEvent MUSIC_ONE_MORE_DAY = registerSoundOfVanilla("music.one_more_day");
    public static final SoundEvent MUSIC_WARMTH = registerSoundOfVanilla("music.warmth");
    public static final SoundEvent MUSIC_SO_BELOW = registerSoundOfVanilla("music.so_below");
    public static final SoundEvent MUSIC_RUBEDO = registerSoundOfVanilla("music.rubedo");
    public static final SoundEvent MUSIC_DEAD_VOXEL = registerSoundOfVanilla("music.dead_voxel");
    public static final SoundEvent MUSIC_CHRYSOPOEIA = registerSoundOfVanilla("music.chrysopoeia");
    public static final SoundEvent MUSIC_CONCRETE_HALLS = registerSoundOfVanilla("music.concrete_halls");
    public static final SoundEvent MUSIC_BALLAD_OF_THE_CATS = registerSoundOfVanilla("music.ballad_of_the_cats");
    public static final SoundEvent MUSIC_MINECRAFT = registerSoundOfVanilla("music.minecraft");
    public static final SoundEvent MUSIC_MICE_ON_VENUS = registerSoundOfVanilla("music.mice_on_venus");
    public static final SoundEvent MUSIC_LIVING_MICE = registerSoundOfVanilla("music.living_mice");
    public static final SoundEvent MUSIC_LILYPAD = registerSoundOfVanilla("music.lilypad");
    public static final SoundEvent MUSIC_LEFT_TO_BLOOM = registerSoundOfVanilla("music.left_to_bloom");
    public static final SoundEvent MUSIC_KOMOREBI = registerSoundOfVanilla("music.komorebi");
    public static final SoundEvent MUSIC_KEY = registerSoundOfVanilla("music.key");
    public static final SoundEvent MUSIC_INFINITE_AMETHYST = registerSoundOfVanilla("music.infinite_amethyst");
    public static final SoundEvent MUSIC_HAGGSTROM = registerSoundOfVanilla("music.haggstrom");
    public static final SoundEvent MUSIC_FLOATING_DREAM = registerSoundOfVanilla("music.floating_dream");
    public static final SoundEvent MUSIC_FIREFLIES = registerSoundOfVanilla("music.fireflies");
    public static final SoundEvent MUSIC_FEATHERFALL = registerSoundOfVanilla("music.featherfall");
    public static final SoundEvent MUSIC_ENDLESS = registerSoundOfVanilla("music.endless");
    public static final SoundEvent MUSIC_THE_END = registerSoundOfVanilla("music.the_end");
    public static final SoundEvent MUSIC_BOSS = registerSoundOfVanilla("music.boss");
    public static final SoundEvent MUSIC_ALPHA = registerSoundOfVanilla("music.alpha");
    public static final SoundEvent MUSIC_ELD_UNKNOWN = registerSoundOfVanilla("music.eld_unknown");
    public static final SoundEvent MUSIC_ECHO_IN_THE_WIND = registerSoundOfVanilla("music.echo_in_the_wind");
    public static final SoundEvent MUSIC_DRY_HANDS = registerSoundOfVanilla("music.dry_hands");
    public static final SoundEvent MUSIC_DEEPER = registerSoundOfVanilla("music.deeper");
    public static final SoundEvent MUSIC_DANNY = registerSoundOfVanilla("music.danny");
    public static final SoundEvent MUSIC_CRESCENT_DUNES = registerSoundOfVanilla("music.crescent_dunes");
    public static final SoundEvent MUSIC_TASWELL = registerSoundOfVanilla("music.taswell");
    public static final SoundEvent MUSIC_HAUNT_MUSKIE = registerSoundOfVanilla("music.haunt_muskie");
    public static final SoundEvent MUSIC_DREITON = registerSoundOfVanilla("music.dreiton");
    public static final SoundEvent MUSIC_BLIND_SPOTS = registerSoundOfVanilla("music.blind_spots");
    public static final SoundEvent MUSIC_BIOME_FEST = registerSoundOfVanilla("music.biome_fest");
    public static final SoundEvent MUSIC_ARIA_MATH = registerSoundOfVanilla("music.aria_math");
    public static final SoundEvent MUSIC_COMFORTING_MEMORIES = registerSoundOfVanilla("music.comforting_memories");
    public static final SoundEvent MUSIC_CLARK = registerSoundOfVanilla("music.clark");
    public static final SoundEvent MUSIC_BROMELIAD = registerSoundOfVanilla("music.bromeliad");
    public static final SoundEvent MUSIC_BROKEN_CLOCKS = registerSoundOfVanilla("music.broken_clocks");
    public static final SoundEvent MUSIC_BELOW_AND_ABOVE = registerSoundOfVanilla("music.below_and_above");
    public static final SoundEvent MUSIC_ANCESTRY = registerSoundOfVanilla("music.ancestry");
    public static final SoundEvent MUSIC_AN_ORDINARY_DAY = registerSoundOfVanilla("music.an_ordinary_day");
    public static final SoundEvent MUSIC_A_FAMILIAR_ROOM = registerSoundOfVanilla("music.a_familiar_room");
    public static final SoundEvent MUSIC_MUTATION = registerSoundOfVanilla("music.mutation");
    public static final SoundEvent MUSIC_MOOG_CITY_2 = registerSoundOfVanilla("music.moog_city_2");
    public static final SoundEvent MUSIC_FLOATING_TREES = registerSoundOfVanilla("music.floating_trees");
    public static final SoundEvent MUSIC_BEGINNING_2 = registerSoundOfVanilla("music.beginning_2");


    //MUSIC DISCS
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_WUOP = registerReference("music_disc.wuop");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_FLY = registerReference("music_disc.fly");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_HARVEST = registerReference("music_disc.harvest");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_MELANC = registerReference("music_disc.melanc");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_LOOFAH = registerReference("music_disc.loofah");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_KENA = registerReference("music_disc.kena");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_BLUUPY = registerReference("music_disc.bluupy");
    //JUKEBOX KEYS
    public static final RegistryKey<JukeboxSong> WUOP =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.wuop"));
    public static final RegistryKey<JukeboxSong> FLY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.fly"));
    public static final RegistryKey<JukeboxSong> HARVEST =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.harvest"));
    public static final RegistryKey<JukeboxSong> MELANC =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.melanc"));
    public static final RegistryKey<JukeboxSong> LOOFAH =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.loofah"));
    public static final RegistryKey<JukeboxSong> KENA =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.kena"));
    public static final RegistryKey<JukeboxSong> BLUUPY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Utils.id("music_disc.bluupy"));


    private static SoundEvent registerSound(String id){
        Identifier thisId = Utils.id(id);
        return Registry.register(Registries.SOUND_EVENT, thisId , SoundEvent.of(thisId));
    }


    private static SoundEvent registerSoundOfVanilla(String id) {
        Identifier thisId = Identifier.ofVanilla(id);
        return Registry.register(Registries.SOUND_EVENT, thisId , SoundEvent.of(thisId));
    }

    public static void initialize(){
        VolumeGamma.LOGGER.info("Registering " + VolumeGamma.MOD_NAME + " sounds");

    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String name) {
        Identifier id = Utils.id(name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

}
