package at.compus02.swd.ss2022.game.observers.position;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;
import at.compus02.swd.ss2022.game.observers.GameObjectObserver;
import at.compus02.swd.ss2022.logger.Logger;;

public class PlayerStatsObserver implements GameObjectObserver {
    private final Logger logger;

    public PlayerStatsObserver(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void update(GameObject gameObject) {
        Player player = (Player) gameObject;
        this.logger.log(String.format("Health: %.0f, Kills: %d", player.getHealth(),
                player.getKillCount()));
    }
}