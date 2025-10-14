package mod.crontent.interfaces;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

import java.util.HashSet;

public interface ChunkBiomeCounterAccess {
    HashSet<RegistryEntry<Biome>> gamma$getBiomesPresent();
}
