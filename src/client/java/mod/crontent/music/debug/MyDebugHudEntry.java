package mod.crontent.music.debug;

import mod.crontent.Utils;
import mod.crontent.interfaces.MusicStuffAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MyDebugHudEntry implements DebugHudEntry {


    public static final Identifier SONGGROUP = Utils.id("songgroup");
    @Override
    public void render(
            DebugHudLines lines,
            @Nullable World world,
            @Nullable WorldChunk clientChunk,
            @Nullable WorldChunk chunk
    ) {
        MinecraftClient instance = MinecraftClient.getInstance();
        lines.addLine("Exact Biome: " + ((MusicStuffAccess) instance).volume_gamma$getCurrentExactBiome());
        String inters = "";
        for (TagKey<Biome> biomeTagKey : ((MusicStuffAccess) instance).volume_gamma$getCurrentSurroundingBiomeTagsIntersection()) {
            inters = inters.concat(biomeTagKey.id().getPath()).concat(",");
        }
        lines.addLine("Daytime: " + ((MusicStuffAccess) instance).volume_gamma$getCurrentDaytime());
        lines.addLine("Situations: " + ((MusicStuffAccess) instance).volume_gamma$getCurrentSituations());
        String biomes = "";
        for (RegistryEntry<Biome> surroundingBiome : ((MusicStuffAccess) instance).volume_gamma$getCurrentSurroundingBiomes()) {
            biomes = biomes.concat(surroundingBiome.getIdAsString()).concat(", ");
        }
        lines.addLine("SurBiomes: " + biomes);
        lines.addLine("inters: " + inters);
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Songpool: vvvv");
        int i = 0;
        String buf = "";
        for (MusicSound o : ((MusicStuffAccess) instance).volume_gamma$getMusicManager().getSongPool().keySet()) {
            buf = buf + o.sound().getIdAsString().split(":")[1].split("\\.")[1] + ", ";
            if (i < 5) {
                i++;
            } else {
                songs.add(buf);
                buf = "";
                i = 0;
            }
        }
        songs.add(buf);
        lines.addLinesToSection(SONGGROUP, songs);
    }

    @Override
    public boolean canShow(boolean reducedDebugInfo) {
        // return false if your debug text
        // is not applicable with reduced debug info
        return true;
    }
}