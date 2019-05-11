package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class NPC extends Character
{
	private int actionNum;
	private int time;
	private int xFinal;
	private boolean moving = false;
	private Image archerLeft = new Image("file:Pictures/lisa_range_left.gif");
    private ImagePattern archerLeftP = new ImagePattern(archerLeft);
    private Image archerRight = new Image("file:Pictures/lisa_range_right.gif");
    private ImagePattern archerRightP = new ImagePattern(archerRight);
    private int maxPosition;
    
    public NPC()
    {
    	this.setFill(archerLeftP);
    	this.setHealth(120);
		this.setWidth((int)(primaryScreenBounds.getWidth() * .15));
		maxPosition = (int)(primaryScreenBounds.getWidth() - this.getWidth());
		while(maxPosition % 5 != 0)
			maxPosition--;
		this.setHeight(primaryScreenBounds.getHeight()*(.25));
		this.setX(maxPosition);
		this.setY(primaryScreenBounds.getHeight()*(.84) - this.getHeight());
		xFinal = (int)(Math.random()*((maxPosition  + 1)));
		this.setDirection("left");
    	checkXFinal(this);
    	this.setStroke(Color.RED);
    	moving = true;
    }
	@Override
	public void handleMovement(ArrayList input)
	{
        System.out.println("xFinal is: " + xFinal);
		 
		if (moving)
     	{
			actionNum = (int)(Math.random()*(200) + 1);
	    	//System.out.println(actionNum);
	    	if (actionNum == 5)
	    		jumping = true;
	    	
			//System.out.println("Final positon: " + xFinal);
     		time = (int)((9.4)/this.getMovementSpeed());
     		
     		if (this.getDirection().equalsIgnoreCase("right"))
     		{
     			this.setX(this.getX() + this.getMovementSpeed() * time);
     			this.setFill(archerRightP);
     		}
     		if (this.getDirection().equalsIgnoreCase("left"))
     		{
     			this.setFill(archerLeftP);
     			this.setX(this.getX() - this.getMovementSpeed() * time);
     		}
     		
     		System.out.println("Enemy's x-position is: " + this.getX() + "\nEnemy's direction is: " + this.getDirection());
     		
     		if (xFinal == this.getX())
     		{	
     			if (this.getDirection().equalsIgnoreCase("right"))
         		{
     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
     				System.out.println("===============Here===================");
     				this.setDirection("left");
     				checkXFinal(this);
         			this.setFill(archerLeftP);
         		}
         		
     			else if (this.getDirection().equalsIgnoreCase("left"))
         		{
     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
         			System.out.println("===============Here===================");
         			this.setDirection("right");
         			checkXFinal(this);
         			this.setFill(archerRightP);
         		}
     		}
     	}
		
		if (jumping) 
        {
        	//Simulating gravity so the character falls down after jumping
            this.setY(this.getY() - 10 + gravity);
            gravity += GRAVITAIONALFORCE;
            
            //If the character's y is equal to the ground's y
           if(this.getY() + this.getHeight() > game.getGround().getY()) 
           {
               gravity = 0;
               jumping = false;
               
               if (this.getDirection().equalsIgnoreCase("left"))
            	   this.setFill(archerLeftP);
               
               if (this.getDirection().equalsIgnoreCase("right"))
            	   this.setFill(archerRightP);
           }
        }
		
    	if ((this.getX() < 0))
    	{
    		this.setDirection("right");
    		this.setX(0);    
    		xFinal = (int)(Math.random()*((maxPosition + 1)));
    		checkXFinal(this);
    		this.setFill(archerRightP);
        }
		
		if (this.getX() > maxPosition)
    	{
    		this.setDirection("left");
    		this.setX(maxPosition);
    		xFinal = (int)(Math.random()*((maxPosition + 1)));
    		checkXFinal(this);
    		this.setFill(archerLeftP);
    	}
		
		if (this.getY() < 0)
    		this.setY(0);
		
		if (((this.getY()) + (this.getHeight())) > (game.getGround().getY() - game.getGround().getHeight()))
        	this.setY(game.getGround().getY() - (this.getHeight()));
	}
	
	private void checkXFinal(Character enemy)
	{
		while (( enemy.getDirection().equalsIgnoreCase("right") ) && ( xFinal < enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while (( enemy.getDirection().equalsIgnoreCase("left") ) && ( xFinal > enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while (xFinal > maxPosition)
    		xFinal = maxPosition;
    	
    	while((xFinal % 5) != 0)
    		xFinal--;
    	
	}
}
