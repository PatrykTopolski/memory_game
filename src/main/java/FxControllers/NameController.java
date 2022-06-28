package FxControllers;

import FxComponents.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

@Setter
public class NameController {

    private  static double WINDOW_WIDTH = 1400;
    private  static double WINDOW_HEIGHT = 500;
    private Stage thisStage;
    private FXMLLoader loader;

    private RootSceneController rootController;

    public void confirmName(){
        TextField textNumber = (TextField) loader.getNamespace().get("confirmName");
        String name = textNumber.getText();
        rootController.getScores().add(Score.builder()
                        .name(name)
                .build());
        thisStage.close();
        rootController.setMainMenuScene();
    }
}
