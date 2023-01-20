package at.compus02.swd.ss2022.game.movement;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;

public class RandomMovementStrategy implements MovementStrategy {
    private float deltaAccumulator = 0.0f;
    float deltaX = randomNumber();
    float deltaY = randomNumber();

    @Override
    public void act(GameObject gameObject, float delta) {
        deltaAccumulator += delta;

        gameObject.setPosition(gameObject.getX() + deltaX, gameObject.getY() + deltaY);

        if (deltaAccumulator > 1.0f) {
            deltaX = randomNumber();
            deltaY = randomNumber();
            deltaAccumulator = 0.0f;
        }
    }

    private float randomNumber() {
        float sign = Math.random() > 0.5 ? 1 : -1;
        return sign;
    }
}
