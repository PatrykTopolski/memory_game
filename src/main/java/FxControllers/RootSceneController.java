package FxControllers;

import FxComponents.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class RootSceneController {
    List<Score> scores;
    private  static double WINDOW_WIDTH = 1400;
    private  static double WINDOW_HEIGHT = 500;
    private Parent root;
    private Stage primaryStage;
    private volatile Scene mainMenu;


    public void startGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/chose_cards.fxml"));
        Parent rootNumbers = (Parent) loader.load();
        NumberController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setLoader(loader);
        controller.setRootController(this);
        Stage numbersStage = new Stage();
        numbersStage.setTitle("set number of cards");
        controller.setThisStage(numbersStage);
        numbersStage.setScene(new Scene(rootNumbers, 600, 400));
        numbersStage.show();
    }



    public void goScore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/score.fxml"));
        Parent score = (Parent) loader.load();
        ScoreController scoreController = loader.getController();
        scoreController.setSceneController(this);
        scoreController.setScores(scores);
        setScore(loader);
        primaryStage.setScene(new Scene(score, 600, 400));
        primaryStage.show();
    }

    private void setScore(FXMLLoader loader){
        VBox scrollPane = (VBox) loader.getNamespace().get("scrollScores");
        scores.forEach(score ->{
            scrollPane.getChildren().add(new Text(score.getName()));
        });

    }

    public void setMainMenuScene(){
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }


}
