package mod.crontent;

import net.minecraft.util.Identifier;

import java.util.Collection;

public class Utils {

    public static Identifier id(String id) {
        return Identifier.of(VolumeGamma.MOD_ID, id);
    }

    public static <T, C extends Collection<T>> C findIntersection(C newCollection, Collection<T>... collections) {
        boolean first = true;
        for (Collection<T> collection : collections) {
            if (first) {
                newCollection.addAll(collection);
                first = false;
            } else {
                newCollection.retainAll(collection);
            }
        }
        return newCollection;
    }
}