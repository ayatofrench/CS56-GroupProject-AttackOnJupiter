package objects;


import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

import engine.GameEngine;
import engine.GamePane;

public class Character extends Rectangle
{

    protected GameEngine game;
    protected int healthPoints;
    protected Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    protected final float GRAVITAIONALFORCE = 0.5f;
    protected float gravity;
    protected boolean jumping = false;
    protected final int MOVEMENTSPEED = 5;
    private String direction;

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

    public void handleMovement() {


    }

    public void jump() {
        this.jumping = true;
    }

    public void setDirection(String direction)
	{
		this.direction = direction;
	}

    public javafx.geometry.Rectangle2D getBoundary() {
        return new javafx.geometry.Rectangle2D(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

}

