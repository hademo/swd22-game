package at.compus02.swd.ss2022.game.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

import at.compus02.swd.ss2022.game.gameobjects.Enemy;

public class EnemyManager {
    private static EnemyManager instance = new EnemyManager();

    private List<Enemy> enemies = new ArrayList<>();

    private EnemyManager() {
    }

    public static EnemyManager getInstance() {
        if (instance == null) {
            instance = new EnemyManager();
        }

        return instance;
    }

    public void add(Enemy enemy) {
        enemies.add(enemy);
    }

    public boolean remove(Enemy enemy) {
        return enemies.remove(enemy);
    }

    public Enemy getOverlappingEnemy(Rectangle rectangle) {
        for (Enemy enemy : enemies) {
            if (enemy.getSprite().getBoundingRectangle().overlaps(rectangle)) {
                return enemy;
            }
        }
        return null;
    }
}
