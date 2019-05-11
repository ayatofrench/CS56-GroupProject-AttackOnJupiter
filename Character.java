package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

public class Character extends Rectangle
{
    protected GameEngine game;
    private int healthPoints;
    protected Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    protected final float GRAVITAIONALFORCE = 0.5f;
    protected float gravity;
    protected boolean jumping = false;
    private final int MOVEMENTSPEED = 5;
    private String direction;
    private Image archerLeft = new Image("file:Pictures/lisa_range_left.gif");
    private ImagePattern archerLeftP = new ImagePattern(archerLeft);
    private Image archerRight = new Image("file:Pictures/lisa_range_right.gif");
    private ImagePattern archerRightP = new ImagePattern(archerRight);
    private double prevXPos;
    
    public Character() 
    {
        super.setY(primaryScreenBounds.getHeight()*(.84));
        gravity = 0;

    }

    public void reduceHealth(int dmg)
    {
        this.healthPoints -= dmg;
    }
    
    public void setPrevXPos(double prevXPos)
    {
    	this.prevXPos = prevXPos;
    }

    public void setHealth(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }
    
    public void setDirection(String direction)
	{
		this.direction = direction;
	}
    
    public double getPrevXPos()
    {
    	return this.prevXPos;
    }

    public int getHealth()
    {
        return this.healthPoints;
    }
    
    public String getDirection()
	{
		return this.direction;
	}

    public int getMovementSpeed()
    {
    	return this.MOVEMENTSPEED;
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

    public void handleMovement(ArrayList input) 
    {
    	//Identifying which way the character is facing
    	if (this.getX() > this.getPrevXPos())
    	{
    		this.setDirection("right");
    		this.setFill(archerRightP);
    	}
    	
    	if (this.getX() < this.getPrevXPos())
    	{
    		this.setDirection("left");
    		this.setFill(archerLeftP);
    	}
    	
        if(input.contains("A"))
        {
        	this.setX(this.getX() - MOVEMENTSPEED);
        }

        if(input.contains(("D")))
        {
        	this.setX(this.getX() + MOVEMENTSPEED);
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

    public void setGame(GameEngine game) {
        this.game = game;
    }

    public void jump() 
    {
        this.jumping = true;
    }

}

