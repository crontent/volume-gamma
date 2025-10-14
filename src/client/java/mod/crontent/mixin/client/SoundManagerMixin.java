package mod.crontent.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mod.crontent.interfaces.SoundListAccess;
import mod.crontent.interfaces.WeightedSoundSetAccess;
import mod.crontent.music.management.loading.MusicDataLoader;
import mod.crontent.music.definition.entries.BiomeTagMusicEntry;
import mod.crontent.music.definition.entries.MusicEntry;
import mod.crontent.music.definition.entries.SituationalMusicEntry;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static mod.crontent.music.management.loading.converting.SoundEventConverter.processSoundsForConversion;

@Mixin(SoundManager.class)
public class SoundManagerMixin {

    @ModifyReturnValue(
            method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/client/sound/SoundManager$SoundList;",
            at = @At("RETURN"))
    public SoundManager.SoundList prepare(SoundManager.SoundList original) {
        //TODO: This should prolly clear the proper music manager maps on reload
        processSoundsForConversion((SoundListAccess) original);
        return original;
    }

}
