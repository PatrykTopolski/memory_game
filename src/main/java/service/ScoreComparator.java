package service;

import FxComponents.Score;

public class ScoreComparator {

    public static int compareCardsNumberBlind(Score a, Score b) {
        return Integer.compare(  a.getCardsDiscoveredFirstTime(), b.getCardsDiscoveredFirstTime());

    }

    public static int compareTime(Score a, Score b) {
        return Double.compare(  b.getTimeInMilis(), a.getTimeInMilis());

    }
}
