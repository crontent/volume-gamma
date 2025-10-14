package mod.crontent.music.management;

public class MusicManagerEnums {
    //TODO: Merge this with MusicSituationalType???
    public enum SituationalType {
        CREATIVE,
        DRAGON,
        END,
        NETHER,
        CREDITS,
        MENU,
        UNDERWATER,
        VILLAGE,
        CAVE
    }

    public enum Daytime {
        NIGHT,
        DAWN,
        DAY,
        DUSK,
        NONAPPLICABLE;

        //TODO: Check for validity re: Daytime / Daytime Cycle. Here we assume 0 = 6:00, which should at least roughly work out
        public static Daytime getDaytime(long time) {
            if (time < 0) return Daytime.NONAPPLICABLE;
            if (time > 13000 && time <= 23000) return Daytime.NIGHT;
            else if (time > 23000 || (time >= 0 && time <= 1000)) return Daytime.DAWN;
            else if (time > 1000 && time <= 11000) return Daytime.DAY;
            else if (time > 11000 && time <= 13000) return Daytime.DUSK;
            else return Daytime.NONAPPLICABLE;
        }
    }

}
