package at.compus02.swd.ss2022.game.movement;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;

public class FollowPlayerMovementStrategy implements MovementStrategy {
    private GameObject player;

    public FollowPlayerMovementStrategy(Player player) {
        this.player = player;
    }

    @Override
    public void act(GameObject gameObject, float delta) {
        float deltaX = (this.player.getX() > gameObject.getX() ? 1 : -1) * 0.8f;
        float deltaY = (this.player.getY() > gameObject.getY() ? 1 : -1) * 0.8f;

        float newX = gameObject.getX() + deltaX;
        float newY = gameObject.getY() + deltaY;

        gameObject.setPosition(newX, newY);
    }
}
