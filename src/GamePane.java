import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GamePane extends Pane {

    public GamePane() {
        Image image = new Image("https://i.pinimg.com/originals/f3/64/b7/f364b7d0a49566891789d291b8b2e53f.png");
        BackgroundImage backgroundImg = new BackgroundImage
                (image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImg));
    }
}
