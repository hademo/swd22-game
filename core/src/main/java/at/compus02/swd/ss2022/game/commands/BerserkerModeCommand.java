package at.compus02.swd.ss2022.game.commands;

import java.util.Timer;
import java.util.TimerTask;

import at.compus02.swd.ss2022.game.gameobjects.Player;

public class BerserkerModeCommand implements Command {
    private Player player;
    private long disabledUntil;

    private static final int DURATION_MS = 5000;
    private static final int COOLDOWN_MS = 10000;

    public BerserkerModeCommand(Player player) {
        this.player = player;
        this.disabledUntil = 0;
    }

    @Override
    public void execute() {
        if (this.disabledUntil < System.currentTimeMillis()) {
            this.disabledUntil = System.currentTimeMillis() + DURATION_MS + COOLDOWN_MS;
            this.player.setBerserkerModeActive(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    player.setBerserkerModeActive(false);
                }
            }, DURATION_MS);
        }
    }

    @Override
    public void stop() {
        //
    }

}
