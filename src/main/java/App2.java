import FxControllers.RootSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App2 extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/main_menu.fxml"));
        Parent root = (Parent) loader.load();
        RootSceneController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setRoot(root);
        controller.setScores(new ArrayList<>());
        primaryStage.setTitle("Memory");
        Scene menu = new Scene(root, 960, 500);
        controller.setMainMenu(menu);
        primaryStage.setScene(menu);
        primaryStage.show();
    }
}
