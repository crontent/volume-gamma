package mod.crontent.music.definition.entries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.crontent.VolumeGammaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeMusicEntry extends MusicEntry<Biome> {
    public static final MapCodec<BiomeMusicEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter(BiomeMusicEntry::getName)
            ).apply(instance, BiomeMusicEntry::new)
    );

    public BiomeMusicEntry(String name) {
        super(name);
    }

    public BiomeMusicEntry(int weight, String name) {
        super(weight, name);
    }

    @Override
    public MusicEntryType<?> getType() {
        return MusicEntryTypes.BIOME;
    }

    @Override
    public boolean matchesGivenCondition(Biome condition) {
        return false;
    }

    public String getName() {
        return name;
    }

    public RegistryEntry<Biome> getBiomeRef() {
        try {
            if (MinecraftClient.getInstance().world != null) {
                Registry<Biome> registry = MinecraftClient.getInstance().world.getRegistryManager().getOrThrow(RegistryKeys.BIOME);
                return registry.getEntry(registry.get(Identifier.of(name)));
            }
            return null;
        } catch (Exception e) {
            VolumeGammaClient.LOGGER.error("No biome found for music entry of {}", this.name);
            return null;
        }
    }
}