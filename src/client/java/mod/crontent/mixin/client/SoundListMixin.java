package mod.crontent.mixin.client;

import mod.crontent.interfaces.SoundListAccess;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(SoundManager.SoundList.class)
public class SoundListMixin implements SoundListAccess {

    @Final
    @Shadow
    Map<Identifier, WeightedSoundSet> loadedSounds;

    public Map<Identifier, WeightedSoundSet> volume_gamma$getLoadedSounds() {
        return loadedSounds;
    }
}
