package mod.crontent.music.definition.entries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BiomeTagMusicEntry extends MusicEntry<TagKey<Biome>> {
    public static final MapCodec<BiomeTagMusicEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter(BiomeTagMusicEntry::getName)
                    ).apply(instance, BiomeTagMusicEntry::new)
    );

    public BiomeTagMusicEntry(String name) {
        super(name);
    }

    public BiomeTagMusicEntry(int weight, String name) {
        super(weight, name);
    }

    public TagKey<Biome> getTagKey(){
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(name));
    }

    @Override
    public MusicEntryType<?> getType() {
        return MusicEntryTypes.BIOME_TAG;
    }

    @Override
    public boolean matchesGivenCondition(TagKey<Biome> condition) {
        return false;
    }
}
