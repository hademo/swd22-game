package at.compus02.swd.ss2022.game.gameobjects;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import at.compus02.swd.ss2022.game.gameobjects.factories.GameObjectFactory.GameObjectType;
import at.compus02.swd.ss2022.game.map.Map;
import at.compus02.swd.ss2022.game.movement.MovementStrategy;
import at.compus02.swd.ss2022.repository.AssetRepository;
import at.compus02.swd.ss2022.repository.AssetRepository.TextureType;

public class Enemy extends MovingGameObject {
    private MovementStrategy movementStrategy;

    public Enemy(MovementStrategy movementStrategy) {
        super();
        Texture texture = AssetRepository.getInstance().getTexture(TextureType.PLAYER);
        setSprite(new Sprite(texture));
        setColor(Color.RED.cpy());
        this.movementStrategy = movementStrategy;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.ENEMY;
    }

    @Override
    public void setPosition(float x, float y) {
        List<Tile> tiles = Map.getInstance().getOverlappingTiles(getSprite().getBoundingRectangle().setPosition(x, y));

        if (tiles.stream().anyMatch(tile -> tile.getGameObjectType() == GameObjectType.TILE_WALL
                || tile.getGameObjectType() == GameObjectType.TILE_WATER)) {
            return;
        }

        super.setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.movementStrategy.act(this, delta);
    }
}
