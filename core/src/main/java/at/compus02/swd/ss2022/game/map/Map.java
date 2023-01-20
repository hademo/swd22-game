package at.compus02.swd.ss2022.game.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Tile;

public class Map {
    private List<Tile> tiles = null;

    private static Map instance;

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    private Map() {
        this.tiles = new ArrayList<>();
    }

    public GameObject[] getObjects() {
        Tile[] tileArray = new Tile[this.tiles.size()];
        tileArray = this.tiles.toArray(tileArray);

        return tileArray;
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        this.tiles.remove(tile);
    }

    public List<Tile> getOverlappingTiles(Rectangle r) {
        List<Tile> tiles = new ArrayList<>();
        for (Tile tile : getTiles()) {
            if (tile.getSprite().getBoundingRectangle().overlaps(r)) {
                tiles.add(tile);
            }
        }
        return tiles;
    }
}
