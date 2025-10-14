package mod.crontent.music.definition.entries;

import mod.crontent.Utils;
import net.minecraft.registry.Registry;

public class MusicSituationalTypes {

    public static final MusicSituationalType CREATIVE = register("creative", new MusicSituationalType());
    public static final MusicSituationalType DRAGON = register("dragon", new MusicSituationalType());
    public static final MusicSituationalType END = register("end", new MusicSituationalType());
    public static final MusicSituationalType NETHER = register("nether", new MusicSituationalType());
    public static final MusicSituationalType CREDITS = register("credits", new MusicSituationalType());
    public static final MusicSituationalType MENU = register("menu", new MusicSituationalType());
    public static final MusicSituationalType UNDERWATER = register("underwater", new MusicSituationalType());
    //TODO: cave algor
    public static final MusicSituationalType CAVE = register("cave", new MusicSituationalType());


    private static MusicSituationalType register(String id, MusicSituationalType musicSituationalType) {
        return Registry.register(MusicSituationalType.REGISTRY, Utils.id(id), musicSituationalType);
    }
}
