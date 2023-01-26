package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import at.compus02.swd.ss2022.game.enemy.EnemyManager;
import at.compus02.swd.ss2022.game.gameobjects.factories.EnemyFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.ProjectileFactory;
import at.compus02.swd.ss2022.game.gameobjects.factories.GameObjectFactory.GameObjectType;
import at.compus02.swd.ss2022.repository.AssetRepository;
import at.compus02.swd.ss2022.repository.AssetRepository.TextureType;

public class Projectile extends MovingGameObject {
    public Projectile() {
        super();
        Texture texture = AssetRepository.getInstance().getTexture(TextureType.PROJECTILE);
        setSprite(new Sprite(texture));
        setMovementSpeedMultiplier(150);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.PROJECTILE;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        detectEnemyHit();
    }

    private void detectEnemyHit() {
        Rectangle rectangle = getSprite().getBoundingRectangle().setPosition(getX(), getY());
        Enemy hitEnemy = EnemyManager.getInstance().getOverlappingEnemy(rectangle);

        if (hitEnemy != null) {
            EnemyFactory.getInstance().remove(hitEnemy);
            ProjectileFactory.getInstance().remove(this);
            PlayerFactory.getInstance().getObjects()[0].killEnemy();
            return;
        }
    }
}
