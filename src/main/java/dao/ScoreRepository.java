package dao;

import FxComponents.Score;

import java.io.IOException;
import java.util.List;

public interface ScoreRepository {

    void saveAll(List<Score> t) throws IOException;
    List<Score> loadAll() throws IOException;
}
