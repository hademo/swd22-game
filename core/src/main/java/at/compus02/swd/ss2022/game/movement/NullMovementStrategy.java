package at.compus02.swd.ss2022.game.movement;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;

public class NullMovementStrategy implements MovementStrategy {
    @Override
    public void act(GameObject gameObject, float delta) {
    }
}
