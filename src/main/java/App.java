import FxControllers.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import FxComponents.Card;

import java.util.ArrayList;
import java.util.List;

public class App  extends Application {

    private  static double WINDOW_WIDTH = 1400;
    private  static double WINDOW_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Image cardBackImage = new Image("card_images/card_back.png");

            String imageFileName = "card_images/clubs" + i + ".png";
            Image cardFrontImage = new Image(imageFileName);
            Card newCard = Card.builder()
                    .carTypeId(i)
                    .backFace(cardBackImage)
                    .frontFace(cardFrontImage)
                    .build();
            newCard.setUp();
            cards.add(newCard);
        }

        for (int i = 1; i < 3; i++) {
            Image cardBackImage = new Image("card_images/card_back.png");

            String imageFileName = "card_images/clubs" + i + ".png";
            Image cardFrontImage = new Image(imageFileName);
            Card newCard = Card.builder()
                    .carTypeId(i)
                    .backFace(cardBackImage)
                    .frontFace(cardFrontImage)
                    .build();
            newCard.setUp();
            cards.add(newCard);
        }

        int row = (int) Math.sqrt(cards.size());
        for (int i = 0; i <row ; i++) {
            WINDOW_HEIGHT += 250 +20;
        }


        GameController game = new GameController();
        game.setTableBackground(new Image("/table/green.png"));
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
        game.setCards(cards);
        game.dealCards();
    }
}
