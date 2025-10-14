package mod.crontent.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import mod.crontent.interfaces.MusicStuffAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicInstance;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MusicTracker.class)
public class MusicTrackerMixin {


//    @ModifyExpressionValue(
//            method = "tick",
//            at = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/client/sound/MusicInstance;shouldReplace(Lnet/minecraft/client/sound/SoundInstance;)Z")
//    )

    @WrapOperation(
            method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/sound/MusicInstance;shouldReplace(Lnet/minecraft/client/sound/SoundInstance;)Z")
    )
    public boolean tick(MusicInstance instance, SoundInstance sound, Operation<Boolean> original){
        //TODO: fix this problem structurally
        //This is a really ugly hack where we assume, when the music has been switched to a replace-wanting song, that that song should never itself get skipped
        //Unsure what this will cause. First impact is mainly the menu, where the now unskippable song gets skipped form somewhere else.
        //Not sure how this is gonna affect the other replace-wanting songs
        return original.call(instance, sound) && !((MusicStuffAccess) MinecraftClient.getInstance()).volume_gamma$getCurrentMusicInstance().music().replaceCurrentMusic();
    }
}
