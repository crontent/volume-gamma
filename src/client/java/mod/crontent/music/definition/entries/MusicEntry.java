package mod.crontent.music.definition.entries;

import com.mojang.serialization.Codec;
import mod.crontent.VolumeGammaClient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public abstract class MusicEntry<T> {
    //TODO: rough hack to make our music more likely (not sure if even works)
    public static final int DEFAULT_WEIGHT = 3;
    //TODO: This is actually never taken into account anywhere
    protected int weight;
    protected final String name;
    
    public static final Codec<MusicEntry> CODEC = MusicEntryType.REGISTRY.getCodec()
            .dispatch(MusicEntry::getType, MusicEntryType::codec);

    public MusicEntry(String name) {
        this.weight = DEFAULT_WEIGHT;
        this.name = name;
    }

    public MusicEntry(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public abstract MusicEntryType<?> getType();

    public abstract boolean matchesGivenCondition(T condition);

    public Identifier getId() {
        return Identifier.of(name);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof MusicEntry otherEntry
                && otherEntry.name.equals(this.name)
                && otherEntry.weight == this.weight
                && otherEntry.getType().equals(this.getType()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, getType());
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }


    public MusicSound getMusicType(Identifier id) {
        Identifier prefixedId = Identifier.of(id.getNamespace(), "music.".concat(id.getPath()));
        RegistryEntry<SoundEvent> soundEvent = getSoundEventRegistryEntry(prefixedId);
        //TODO: polymorphism
        MusicSound ms = new MusicSound(soundEvent, 12000, 24000, false);
        if (this instanceof SituationalMusicEntry e) {
            switch (e.getSituation()) {
                case MENU -> ms = new MusicSound(soundEvent, 20, 600, true);
                //This seems unused as credits music is provided outside our mod
                case CREDITS -> new MusicSound(soundEvent, 0, 0, true);
                case DRAGON -> new MusicSound(soundEvent, 0, 0, true);
                case END -> new MusicSound(soundEvent, 6000, 24000, true);
            }
        }
        return ms;
        //return new MusicSound(soundEvent, 12000, 24000, false);
    }

    public static @NotNull RegistryEntry<SoundEvent> getSoundEventRegistryEntry(Identifier id) {
        //TODO: proper handling of invalid sound
        Optional<RegistryEntry.Reference<SoundEvent>> entry = Registries.SOUND_EVENT.getEntry(id);
        if(entry.isEmpty()) VolumeGammaClient.LOGGER.warn("Sound Entry of ID {} is nonexistant.", id);
        return entry.isPresent()? entry.get() : Registries.SOUND_EVENT.getEntry(SoundEvents.INTENTIONALLY_EMPTY);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
