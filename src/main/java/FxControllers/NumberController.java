package FxControllers;

import FxComponents.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Setter
public class NumberController {

    private  static double WINDOW_WIDTH = 1400;
    private  static double WINDOW_HEIGHT = 500;
    private Stage primaryStage;

    private Stage thisStage;
    private FXMLLoader loader;

    private RootSceneController rootController;



    public void confirm(){

        TextField textNumber = (TextField) loader.getNamespace().get("confirm");
        int cardNumber = Integer.parseInt(textNumber.getText());
        List<Card> cards = generateCards(cardNumber);
        GameController game = new GameController();
        game.setRootSceneController(rootController);
        game.setTableBackground(new Image("/table/green.png"));
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        thisStage.close();
        primaryStage.show();
        game.setCards(cards);
        game.dealCards();
        game.initTimer();
    }

    private java.util.List<Card> generateCards(int cardsNumber){
        cardsNumber = cardsNumber/2;
        java.util.List<Card> cards = new ArrayList<>();
        loopCards(cardsNumber, cards);
        loopCards(cardsNumber, cards);
        return cards;
    }

    private void loopCards(int cardsNumber, java.util.List<Card> cards) {
        for (int i = 1; i <= cardsNumber; i++) {
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
    }
}
