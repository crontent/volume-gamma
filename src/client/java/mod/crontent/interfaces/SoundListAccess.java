package mod.crontent.interfaces;

import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface SoundListAccess {
    Map<Identifier, WeightedSoundSet> volume_gamma$getLoadedSounds();
}
