package mod.crontent.music.definition.entries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.crontent.music.management.MusicManagerEnums;

public class DaytimeMusicEntry extends MusicEntry<MusicManagerEnums.Daytime> {
    public static final MapCodec<DaytimeMusicEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.fieldOf("name").forGetter(DaytimeMusicEntry::getName)
            ).apply(instance, DaytimeMusicEntry::new)
    );

    public DaytimeMusicEntry(String name) {
        super(name);
    }

    public DaytimeMusicEntry(int weight, String name) {
        super(weight, name);
    }

    @Override
    public MusicEntryType<?> getType() {
        return MusicEntryTypes.DAYTIME;
    }

    @Override
    public boolean matchesGivenCondition(MusicManagerEnums.Daytime condition) {
        return false;
    }

    public String getName() {
        return name;
    }

    public MusicManagerEnums.Daytime getDaytime() {
        return MusicManagerEnums.Daytime.valueOf(name);
    }
}
