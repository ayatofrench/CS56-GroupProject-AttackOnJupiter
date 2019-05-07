import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

public class Character extends Rectangle
{

    private GameEngine game;
    private int healthPoints;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private final float GRAVITAIONALFORCE = 0.5f;
    private float gravity;
    private boolean jumping = false;
    private final int MOVEMENTSPEED = 5;

    public Character() {

        super.setFill(Color.YELLOW);
        super.setWidth(100);
        super.setHeight(100);
        super.setX(primaryScreenBounds.getWidth());
        super.setY(primaryScreenBounds.getHeight()*(.84)-1);

        gravity = 0;

    }

    public void reduceHealth(int dmg)
    {
        this.healthPoints -= dmg;
    }

    public void setHealth(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public int getHealth()
    {
        return this.healthPoints;
    }

    public void defenseAbility(){}

    public boolean isAlive()
    {
        if (this.healthPoints > 0)
            return true;

        else if (this.healthPoints <= 0)
            return false;

        else
            return true;
    }

    public void handleMovement(ArrayList input) {
        if(input.contains("LEFT"))
            this.setX(this.getX() - MOVEMENTSPEED);

        if(input.contains(("RIGHT")))
            this.setX(this.getX() + MOVEMENTSPEED);

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

    public void setGame(GameEngine game) {
        this.game = game;
    }

    public void jump() {
        this.jumping = true;
    }

}

