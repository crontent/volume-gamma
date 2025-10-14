package mod.crontent.music.definition.entries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.crontent.music.management.MusicManagerEnums;

public class SituationalMusicEntry extends MusicEntry<MusicManagerEnums.SituationalType> {
    public static final MapCodec<SituationalMusicEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter(SituationalMusicEntry::getName)
            ).apply(instance, SituationalMusicEntry::new)
    );


    public SituationalMusicEntry(String name) {
        super(name);
    }

    public SituationalMusicEntry(int weight, String name) {
        super(weight, name);
    }

    @Override
    public MusicEntryType<?> getType() {
        return MusicEntryTypes.SITUATIONAL;
    }

    @Override
    public boolean matchesGivenCondition(MusicManagerEnums.SituationalType condition) {
        return false;
    }


    public String getName() {
        return name;
    }

    public MusicManagerEnums.SituationalType getSituation() {
        return MusicManagerEnums.SituationalType.valueOf(name);
    }
}
