package island.model;

import island.settings.Config;

public class Island {

    private final Location[][] locations;

    public Island() {
        locations = new Location[Config.HEIGHT][Config.WIDTH];
        for (int y = 0; y < Config.HEIGHT; y++) {
            for (int x = 0; x < Config.WIDTH; x++) {
                locations[y][x] = new Location();
            }
        }
    }

    public Location getLocation(int x, int y) {
        return locations[y][x];
    }

    public int getWidth() {
        return Config.WIDTH;
    }

    public int getHeight() {
        return Config.HEIGHT;
    }
}
