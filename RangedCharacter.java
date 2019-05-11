package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;

public class RangedCharacter extends Character
{
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
	private Image archerLeft = new Image("file:Pictures/lisa_range_left.gif");
    private ImagePattern archerP = new ImagePattern(archerLeft);
    private Image archerRight = new Image("file:Pictures/lisa_range_right.gif");
    private ImagePattern archerRightP = new ImagePattern(archerRight);
	 
	public RangedCharacter()
	{
		this.setDirection("right");
		this.setHealth(120);
		this.setWidth(primaryScreenBounds.getWidth() * .15);
		this.setHeight(primaryScreenBounds.getHeight()*(.25));
        this.setStroke(Color.GREEN);
		this.setX(0);
		this.setY(primaryScreenBounds.getHeight()*(.84));
	}
}
