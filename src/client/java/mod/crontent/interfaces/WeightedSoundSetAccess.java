package mod.crontent.interfaces;

import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;

import java.util.List;

public interface WeightedSoundSetAccess {

    List<SoundContainer<Sound>> volume_gamma$getSounds();
}
