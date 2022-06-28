package FxControllers;

import FxComponents.Score;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
public class ScoreController {

    private RootSceneController sceneController;

    List<Score> scores;

    public void goBack() throws IOException {
        sceneController.setMainMenuScene();
    }
}
