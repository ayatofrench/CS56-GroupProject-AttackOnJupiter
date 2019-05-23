package Model;

import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Screen;
import javafx.util.Duration;

public class AttackOnJupiterSubScene extends SubScene {
	
	private final static String FONT_PATH = "file:src/resources/Raleway.ttf";
	private final static String BACKGROUND_IMAGE = "file:src/resources/Button.png";
	private boolean isHidden;
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
	
	
	public AttackOnJupiterSubScene(String backgroundImage) {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		
		BackgroundImage image = new BackgroundImage(new Image(backgroundImage, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(image));

		isHidden = true;
		
		//This Sub scene is in the picture if you zoom it out, but if you set it as far as the screens pixel then u can mask it out
		//Will Probably have to be changed later
		setLayoutX(primaryScreenBounds.getMaxX());
		setLayoutY(180);
	}
	
	//method for logic to move the sub scene
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		//defines what method should be moved
		transition.setNode(this);
		
		if(isHidden) {
		transition.setToX(-676);
		isHidden = false;
		} else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
	

	
}
