package mod.crontent.mixin.client;

import mod.crontent.interfaces.WeightedSoundSetAccess;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;
import net.minecraft.client.sound.WeightedSoundSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(WeightedSoundSet.class)
public class WeightedSoundSetMixin implements WeightedSoundSetAccess {

    @Final
    @Shadow
    private List<SoundContainer<Sound>> sounds;

    public List<SoundContainer<Sound>> volume_gamma$getSounds() {
        return sounds;
    }
}
