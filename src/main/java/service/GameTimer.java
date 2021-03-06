package service;

import FxControllers.GameController;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

    public double stopGameTimer(){

        return Instant.ofEpochMilli(
                Duration.between(startGame, Instant.now()).toMillis()
        ).toEpochMilli();
    }
}
