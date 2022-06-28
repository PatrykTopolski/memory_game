package FxComponents;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Card  extends ImageView {

    private int carTypeId;
    public boolean faceDown;

    private boolean notRevealed;
    private  Image backFace;
    private  Image frontFace;
    private  DropShadow dropShadow;
    private boolean done;


    public void setUp(){
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        faceDown = true;
        done = false;
        setImage(backFace);
        setEffect(dropShadow);
    }

    public void flip() {
        faceDown = !faceDown;
        setImage(faceDown ? backFace : frontFace);
    }

}
