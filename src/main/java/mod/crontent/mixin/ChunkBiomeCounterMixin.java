package mod.crontent.mixin;

import mod.crontent.interfaces.ChunkBiomeCounterAccess;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;

@Mixin(Chunk.class)
public abstract class ChunkBiomeCounterMixin implements ChunkBiomeCounterAccess {
    @Unique
    private HashSet<RegistryEntry<Biome>> biomesPresent = new HashSet<>();


    public HashSet<RegistryEntry<Biome>> gamma$getBiomesPresent() {
        if (biomesPresent.isEmpty()) {
            for (ChunkSection section : ((Chunk) (Object) this).getSectionArray()) {
                section.getBiomeContainer().forEachValue(biomesPresent::add);
            }
        }
        return biomesPresent;
    }
}
