import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GamePane extends Pane {

    private GameEngine engine;

    public GamePane() {
        Image image = new Image("file:Pictures/back.gif");
        BackgroundImage backgroundImg = new BackgroundImage
                (image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImg));
    }

    public GameEngine getEngine() {
        return engine;
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }
}
