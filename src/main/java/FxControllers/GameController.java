package FxControllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import FxComponents.Card;
import javafx.stage.Stage;
import utils.CardsAfterLife;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GameController extends Pane {
    private List<Card> cards;

    private volatile Card lastDraggedCard;
    private volatile Card draggedCard;

    private volatile RootSceneController rootSceneController;


    private final EventHandler<MouseEvent> onMousePressedHandler =  e -> {
        draggedCard = (Card) e.getSource();
    };


    private final EventHandler<MouseEvent> onMouseReleasedHandler = e -> {
        if (draggedCard == null) {
            System.out.println("dragged card = null");
            return;
        }else
        if (draggedCard.isDone()){
            System.out.println("the card is done");
            return;
        }else
        if (draggedCard.equals(lastDraggedCard)){
            System.out.println("its the same card");
            return;
        }

        if (lastDraggedCard == null) {
            draggedCard.flip();
            System.out.println(draggedCard.hashCode());
            System.out.println("set card faceDown");
            lastDraggedCard = draggedCard;
            draggedCard = null;
        }
        else if (lastDraggedCard.getCarTypeId() == draggedCard.getCarTypeId()){
            draggedCard.flip();
            System.out.println("ITS THE PAIR");
            draggedCard.setDone(true);
            lastDraggedCard.setDone(true);
            lastDraggedCard = null;
            draggedCard =null;
            if (isGameWon()){
                System.out.println("game is won");
                askForName();
//                rootSceneController.setMainMenuScene();
            }
        }else{
            System.out.println("its not the same.. kurwa student debil");
            draggedCard.flip();
            CardsAfterLife cal = new CardsAfterLife(lastDraggedCard, draggedCard);
            cal.run();
            lastDraggedCard.setNotRevealed(false);
            draggedCard.setNotRevealed(false);
            lastDraggedCard=null;
            draggedCard=null;
        }
    };

    private void askForName()  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/enter_name.fxml"));
        Parent rootName = null;
        try {
            rootName = (Parent) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NameController controller = loader.getController();
        controller.setLoader(loader);
        controller.setRootController(rootSceneController);
        Stage nameStage = new Stage();
        controller.setThisStage(nameStage);
        nameStage.setTitle("set name");
        nameStage.setScene(new Scene(rootName, 600, 400));
        nameStage.show();
    }

    public boolean isGameWon(){
        List<Card> howMany = cards.stream().filter(Card::isDone).collect(Collectors.toList());
        System.out.println("cards done: how many");
        return howMany.size() == cards.size();
    }

    public void addMouseEventHandlers(Card card) {
        card.setOnMousePressed(onMousePressedHandler);
        card.setOnMouseReleased(onMouseReleasedHandler);
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void dealCards() {
        int rowSize = (int) Math.sqrt(cards.size());
        int tempSize = rowSize;
        double startX = 85;
        double startY = 20;
        int xIterator = 0;
        for (Card card : cards) {
            if (tempSize == 0) {
                xIterator = 0;
                startY = startY + 250;
                tempSize = rowSize;
            }
            card.setLayoutX(startX + xIterator * 250);
            card.setLayoutY(startY);
            addMouseEventHandlers(card);
            getChildren().add(card);
            tempSize--;
            xIterator++;
        }
    }




    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setRootSceneController(RootSceneController rootSceneController) {
        this.rootSceneController = rootSceneController;
    }
}