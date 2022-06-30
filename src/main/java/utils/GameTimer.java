package utils;

import FxControllers.GameController;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class GameTimer implements Runnable{

    private final Instant startGame = Instant.now();;
    private Instant stopGame;
    private final GameController gameController;
    boolean gameIsWorking = true;


    @Override
    public void run() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameController.displayTime(Duration.between(startGame, Instant.now()));
            }
        }, 0, 1000);

    }

    public void stopGameTimer(){
        gameIsWorking = false;
        stopGame = Instant.now();
    }
}
