package objects;

import engine.GamePane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class Player extends Character {

    private ArrayList<String> input;

    private boolean hurtable = true;

    //Initializing images for Lisa
    private static Image lisa_right = new Image("file:Pictures/lisa_right.gif");
    private static Image lisa_left = new Image("file:Pictures/lisa_left.gif");
    private static Image attackLeft = new Image("file:Pictures/attack_left.gif");
    private static Image attackRight = new Image("file:Pictures/attack_right.gif");
    private static Image lisaStillRight = new Image("file:Pictures/lisaStillRight.png");
    private static Image lisaStillLeft = new Image("file:Pictures/lisaStillLeft.png");

    private ImagePattern lisa_rightP;
    private ImagePattern lisa_leftP;
    private ImagePattern attack_leftP;
    private ImagePattern attack_rightP;
    private ImagePattern lisaStillRightP;
    private ImagePattern lisaStillLeftP;

    public Player() {
        lisa_rightP = new ImagePattern(lisa_right);
        lisa_leftP = new ImagePattern(lisa_left);
        attack_rightP = new ImagePattern(attackRight);
        attack_leftP = new ImagePattern(attackLeft);
        lisaStillRightP = new ImagePattern(lisaStillRight);
        lisaStillLeftP = new ImagePattern(lisaStillLeft);
        this.setFill(lisa_leftP);
    }

	public void bulwark()
	{
		hurtable = false;

	}

	@Override
    public void handleMovement() {
        game = ((GamePane)getParent()).getEngine();
        input = game.getInput();

        if(input.contains("LEFT")) {
            this.setX(this.getX() - MOVEMENTSPEED);
            this.setFill(lisa_leftP);
        }

        if(input.contains(("RIGHT"))) {
            this.setX(this.getX() + MOVEMENTSPEED);
            this.setFill(lisa_rightP);
        }

        if(jumping) {
            this.setY(this.getY() - 10 + gravity);
            gravity += GRAVITAIONALFORCE;

            if(this.getY() + this.getHeight() > game.getGround().getY()) {
                gravity = 0;
                jumping = false;
            }
        }

        if (((this.getY()) + (this.getHeight())) > (game.getGround().getY() - game.getGround().getHeight()))
            this.setY(game.getGround().getY() - (this.getHeight()));

        if (this.getY() < 0){
            this.setY(0);
            gravity = 0;
            jumping = false;
        }

        if ((this.getX() < 0)){
            this.setX(0);

        }

        if ((this.getX() > (game.getScene().getWidth() - this.getWidth())))
            this.setX(game.getScene().getWidth() - this.getWidth());
    }
}
