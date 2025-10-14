package mod.crontent.mixin.client;

import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.client.sound.SoundManager$SoundList$1")
public interface SoundManagerAnonymousClassMixin {
    /**
     * @return The SoundList object of the Anon Class, containing all infos about the loaded Sounds
     */
    @Accessor("field_5597")
    SoundManager.SoundList volume_gamma$field_5597();

    /**
     * @return The Sound itself for the Anon Class, which gives info about id and regType
     */
    @Accessor("field_5595")
    Sound volume_gamma$field_5595();
}
