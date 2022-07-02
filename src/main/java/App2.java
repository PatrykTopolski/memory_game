import FxControllers.RootSceneController;
import dao.ScoreRepository;
import dao.ScoreRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static FxControllers.RootSceneController.WINDOW_HEIGHT;
import static FxControllers.RootSceneController.WINDOW_WIDTH;

public class App2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScoreRepository repository = new ScoreRepositoryImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/main_menu.fxml"));
        Parent root = loader.load();
        RootSceneController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setRoot(root);
        controller.setScores(new ArrayList<>());
        checkRepo(repository, controller);
        controller.setRepository(repository);
        primaryStage.setTitle("Memory");
        Scene menu = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        controller.setMainMenu(menu);
        primaryStage.setScene(menu);
        primaryStage.show();


    }

    private void checkRepo(ScoreRepository repository, RootSceneController controller)  {
        try {
            controller.setScores(repository.loadAll());
        } catch (IOException e) {
            System.out.println("no file found");
        }
    }
}
