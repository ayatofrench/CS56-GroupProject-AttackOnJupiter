import Model.AttackOnJupiterButton;
import Model.AttackOnJupiterSubScene;
import Model.InfoLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class StartMenuPane extends Group {

    private GameEngine game;
    private Button btnPlay;
    private Button btnQuit;
    private Button btnCredits;
    private Button btnHTP;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private AnchorPane mainPane;//buttons
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    //private AttackOnJupiterSubScene playSubScene;
    private AttackOnJupiterSubScene howToPlaySubScene;
    private AttackOnJupiterSubScene creditsSubScene;
    private AttackOnJupiterSubScene quitSubScene;

    private AttackOnJupiterSubScene sceneToHide;


    List<AttackOnJupiterButton> menuButtons;

    public StartMenuPane()
    {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        createButtons();
        createBackground();
        createLogo();

        createSubScenes();
    }

    public Scene getMainScene() {
        return mainScene;
    }

    private void createBackground() {
        Image backgroundImage = new Image("file:src/resources/AttackonJavaJupiterMenu.jpg", 1919, 805, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void addMenuButton(AttackOnJupiterButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);

    }

    private void createButtons() {
        createPlayButton();
        createHowToPlayButton();
        createCreditsButton();
        createQuitButton();

        createLogo();
    }

    private void createPlayButton() {
        AttackOnJupiterButton playButton = new AttackOnJupiterButton("Play");
        addMenuButton(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                game.initGame();
            }
        });
    }

    private void createHowToPlayButton() {
        AttackOnJupiterButton howToPlayButton = new AttackOnJupiterButton("How To Play");
        addMenuButton(howToPlayButton);

        howToPlayButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                showSubScene(howToPlaySubScene);
            }

        });
    }

    private void createCreditsButton() {
        AttackOnJupiterButton creditsButton = new AttackOnJupiterButton("Credits");
        addMenuButton(creditsButton);

        InfoLabel credits = new InfoLabel("Hello");
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                showSubScene(creditsSubScene);
            }

        });
    }

    private void createQuitButton() {
        AttackOnJupiterButton quitButton = new AttackOnJupiterButton("Quit");
        addMenuButton(quitButton);

        quitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                Main.getStage().close();
            }

        });
    }

    private void createLogo() {
        //Insert Better Logo here
        ImageView logo = new ImageView("file:Pictures/lisaStillLeft.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }

    private void createSubScenes() {
        //playSubScene = new AttackOnJupiterSubScene("file:src/resources/Button.png");
        //mainPane.getChildren().add(playSubScene);

        howToPlaySubScene = new AttackOnJupiterSubScene("file:Pictures/AoJTerror.gif");
        mainPane.getChildren().add(howToPlaySubScene);

        creditsSubScene = new AttackOnJupiterSubScene("file:src/resources/Button.png");
        mainPane.getChildren().add(creditsSubScene);

        quitSubScene = new AttackOnJupiterSubScene("file:src/resources/Button.png");
        mainPane.getChildren().add(quitSubScene);
    }

    private void showSubScene(AttackOnJupiterSubScene subScene) {
        if(sceneToHide!= null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }


    public void setGameEngine(GameEngine game)
    {
        this.game = game;
    }

}
