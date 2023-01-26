package at.compus02.swd.ss2022.game.gameobjects;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import at.compus02.swd.ss2022.game.gameobjects.factories.EnemyFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.GameObjectFactory.GameObjectType;
import at.compus02.swd.ss2022.game.map.Map;
import at.compus02.swd.ss2022.game.movement.MovementStrategy;
import at.compus02.swd.ss2022.game.movement.NullMovementStrategy;
import at.compus02.swd.ss2022.repository.AssetRepository;
import at.compus02.swd.ss2022.repository.AssetRepository.TextureType;

public class Enemy extends MovingGameObject {
    private MovementStrategy movementStrategy;

    public Enemy() {
        super();
        Texture texture = AssetRepository.getInstance().getTexture(TextureType.PLAYER);
        setSprite(new Sprite(texture));
        setColor(Color.RED.cpy());
        this.movementStrategy = new NullMovementStrategy();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.ENEMY;
    }

    @Override
    public void setPosition(float x, float y) {
        Rectangle rectangle = getSprite().getBoundingRectangle().setPosition(x, y);
        List<Tile> tiles = Map.getInstance().getOverlappingTiles(rectangle);

        if (tiles.stream().anyMatch(tile -> tile.getGameObjectType() == GameObjectType.TILE_WALL
                || tile.getGameObjectType() == GameObjectType.TILE_WATER)) {
            return;
        }

        super.setPosition(x, y);
        detectPlayerHit(rectangle);
    }

    private void detectPlayerHit(Rectangle rectangle) {
        Player player = PlayerFactory.getInstance().getObjects()[0];

        if (player.getSprite().getBoundingRectangle().overlaps(rectangle)) {
            player.hit();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.movementStrategy.act(this, delta);
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void die() {
        EnemyFactory.getInstance().remove(this);
    }
}
