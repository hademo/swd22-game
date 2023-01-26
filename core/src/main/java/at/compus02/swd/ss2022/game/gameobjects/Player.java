package at.compus02.swd.ss2022.game.gameobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import at.compus02.swd.ss2022.game.gameobjects.factories.EnemyFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.TileFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.GameObjectFactory.GameObjectType;
import at.compus02.swd.ss2022.game.map.Map;
import at.compus02.swd.ss2022.repository.AssetRepository;
import at.compus02.swd.ss2022.repository.AssetRepository.TextureType;

public class Player extends MovingGameObject {
    private boolean berserkerModeActive;
    private float health = 100;
    private int killCount = 0;

    public Player() {
        super();
        Texture texture = AssetRepository.getInstance().getTexture(TextureType.PLAYER);
        setSprite(new Sprite(texture));
        setBerserkerModeActive(false);
    }

    public boolean isBerserkerModeActive() {
        return this.berserkerModeActive;
    }

    public boolean getBerserkerModeActive() {
        return this.berserkerModeActive;
    }

    public void setBerserkerModeActive(boolean berserkerModeActive) {
        this.berserkerModeActive = berserkerModeActive;
        if (this.berserkerModeActive == false) {
            this.setParticleEffect(null);
            return;
        }

        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("flames.particles"), Gdx.files.internal(""));
        particleEffect.getEmitters().first().setPosition(this.getSprite().getX(), this.getSprite().getY());
        particleEffect.start();
        this.setParticleEffect(particleEffect);

        this.detectEnemyHit();
    }

    private void detectEnemyHit() {
        Rectangle rectangle = getSprite().getBoundingRectangle().setPosition(getX(), getY());
        Enemy[] enemies = EnemyFactory.getInstance().getObjects();

        for (Enemy enemy : enemies) {
            if (enemy.getSprite().getBoundingRectangle().overlaps(rectangle)) {
                EnemyFactory.getInstance().remove(enemy);
                killCount++;
                notifyGameObjectObservers();
                return;
            }
        }
    }

    @Override
    public void setPosition(float x, float y) {
        // Get all tiles overlapping with new sprite position
        List<Tile> tiles = Map.getInstance().getOverlappingTiles(getSprite().getBoundingRectangle().setPosition(x, y));

        // Prevent walking on walls
        if (tiles.stream().anyMatch(tile -> tile.getGameObjectType() == GameObjectType.TILE_WALL)) {
            return;
        }

        // Prevent walking on water
        if (tiles.stream().anyMatch(tile -> tile.getGameObjectType() == GameObjectType.TILE_WATER)) {
            // If berserker mode is active, water turns into gravel and it turns off
            if (isBerserkerModeActive()) {
                setBerserkerModeActive(false);
                tiles.stream().filter(tile -> tile.getGameObjectType() == GameObjectType.TILE_WATER)
                        .forEach(waterTile -> {
                            Tile gravelTile = TileFactory.getInstance().create(GameObjectType.TILE_GRAVEL);
                            gravelTile.setPosition(waterTile.getSprite().getX(), waterTile.getSprite().getY());
                            Map.getInstance().removeTile(waterTile);
                            Map.getInstance().addTile(gravelTile);
                        });
            } else {
                return;
            }
        }

        super.setPosition(x, y);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.PLAYER;
    }

    public float getHealth() {
        return health;
    }

    public void hit() {
        float newHealth = getHealth();
        newHealth -= 0.2f;

        if (newHealth < 0) {
            newHealth = 0;
        }
        health = newHealth;
        this.notifyGameObjectObservers();
    }

    public void killEnemy() {
        killCount++;
        this.notifyGameObjectObservers();
    }

    public int getKillCount() {
        return killCount;
    }
}
