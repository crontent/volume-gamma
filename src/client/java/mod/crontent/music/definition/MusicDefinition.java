package mod.crontent.music.definition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.crontent.music.definition.entries.MusicEntry;

import java.util.List;

//TODO: this should centralize information like ???
public record MusicDefinition(List<MusicEntry> playConditions)  {

    public static final Codec<MusicDefinition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(MusicEntry.CODEC.listOf().fieldOf("play_conditions").forGetter(MusicDefinition::playConditions)
                    )
                    .apply(instance, MusicDefinition::new)
    );
}
