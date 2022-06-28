package utils;

import FxComponents.Card;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CardsAfterLife implements Runnable{

    Card card1;
    Card card2;

    public CardsAfterLife(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    @Override
    public void run() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            System.out.println("flipping");
            card1.flip();
            card2.flip();
        };
        executor.schedule(task, 2, TimeUnit.SECONDS);


    }


}
