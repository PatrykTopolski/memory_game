package FxControllers;

import FxComponents.Card;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
public class NumberController {

    private static double WINDOW_WIDTH = 1400;
    private static double WINDOW_HEIGHT = 500;
    private Stage primaryStage;
    private Stage thisStage;
    private FXMLLoader loader;
    private RootSceneController rootController;
    public static int ROW_SIZE;
    public static int COLUMN_SIZE;
    private Scene mainScene;



    public void confirm() {
        mainScene = primaryStage.getScene();
        TextField textNumber = (TextField) loader.getNamespace().get("confirm");
        int cardNumber = Integer.parseInt(textNumber.getText());
        calcColumnAndRow(cardNumber);
        List<Card> cards = null;
        try {
            cards = generateCards(cardNumber);
        } catch (Exception e) {
            return;
        }
        GameController game = new GameController();
        game.setRootSceneController(rootController);
        game.setTableBackground(new Image("/table/green.png"));
        primaryStage.setTitle("Memory");
        Scene gameScene = new Scene(game, 80 + (ROW_SIZE * 250), 20 + (COLUMN_SIZE * 250));
        gameScene.setOnMouseReleased(m->{
            double screenWidth = Screen.getPrimary().getBounds().getWidth();
            double screenHigh = Screen.getPrimary().getBounds().getHeight();
        });
        primaryStage.setScene(gameScene);
        thisStage.close();
        primaryStage.show();
        primaryStage.getScene().getAccelerators().put(
                KeyCombination.keyCombination("CTRL+SHIFT+U"),
                () -> {
                    System.out.println("exit key combination detected");
                    primaryStage.setScene(mainScene);
                }
        );
        Collections.shuffle(cards);
        game.setCards(cards);
        game.dealCards();
        game.initTimer();
    }

    private void calcColumnAndRow(int cardNumber) {
        ROW_SIZE = (int) Math.ceil(Math.sqrt(cardNumber));
        if (((ROW_SIZE * ROW_SIZE) - ROW_SIZE) >= cardNumber) {
            COLUMN_SIZE = (int) Math.floor(Math.sqrt(cardNumber));
        } else {
            COLUMN_SIZE = (int) Math.ceil(Math.sqrt(cardNumber));
            System.out.println("row: " + ROW_SIZE + " column " + COLUMN_SIZE);
        }
    }

    private List<Card> generateCards(int cardsNumber) throws Exception {
        checkIfNumberIsCorrect(cardsNumber);

        cardsNumber = cardsNumber / 2;
        java.util.List<Card> cards = new ArrayList<>();
        loopCards(cardsNumber, cards);
        loopCards(cardsNumber, cards);
        return cards;
    }

    private void checkIfNumberIsCorrect(int cardsNumber) throws Exception {
        if (cardsNumber %2!=0 ){
            throwPopupException("number not even");
        } else if (cardsNumber== 2) {
            throwPopupException("not enough cards");
        }
    }

    private void throwPopupException(String messageName) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("error");
        alert.setHeaderText("invalid cards number");
        alert.setContentText(messageName);
        alert.showAndWait();
        throw new Exception();
    }

    private void loopCards(int cardsNumber, List<Card> cards) {
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
