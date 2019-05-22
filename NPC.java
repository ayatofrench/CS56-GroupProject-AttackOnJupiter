package AoJGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class NPC extends Character
{
	private int actionNum;
	private Projectile proj;
	private int shootNum;
	private int time;
	private int xFinal;
	private boolean moving = false;
	private Image metaManLeft = new Image("file:Pictures/metaman/metaman_left.gif");
    private ImagePattern metaManLeftP = new ImagePattern(metaManLeft);
    private Image metaManRight = new Image("file:Pictures/metaman/metaman_left.gif");
    private ImagePattern metaManRightP = new ImagePattern(metaManRight);
    private Image gunslingerLeft = new Image("file:Pictures/lisa_range/lisa_range_left.gif");
    private ImagePattern gunslingerLeftP = new ImagePattern(gunslingerLeft);
    private Image gunslingerRight = new Image("file:Pictures/lisa_range/lisa_range_right.gif");
    private ImagePattern gunslingerRightP = new ImagePattern(gunslingerRight);
    private Image worm_right = new Image("file:Pictures/metaman/worm_right.gif");
    private ImagePattern wormRightP = new ImagePattern(worm_right);
    private Image worm_left = new Image("file:Pictures/metaman/worm_left.gif");
    private ImagePattern wormLeftP = new ImagePattern(worm_left);
    private int bossLevel = 0;
    
    public NPC()
    {
    	this.sleepTime = 0;
    	if (bossLevel == 1)
				this.setFill(gunslingerLeftP);
		if (bossLevel == 2)
				this.setFill(gunslingerLeftP);
    	this.setHealth(120);
		this.setWidth((int)(primaryScreenBounds.getWidth() * .2));
		maxPosition = (int)(primaryScreenBounds.getWidth() - this.getWidth());
		while(maxPosition % 5 != 0)
			maxPosition--;
		this.setHeight(primaryScreenBounds.getHeight()*(.3));
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
		if (!this.isAlive())
		{
			game.getGamePane().getChildren().remove(proj);
			game.initGameTwo();
		}
		
		if (this.isAlive())
		{
			shootNum = (int)(Math.random()*(150) + 1);
			if (shootNum == 5 && sleepTime == 0)
			{
				this.shoot();
			}
				
			if (shooting)
		    {
				if (this.getX() < this.getEnemy().getX())
				{
					proj.setFill(wormRightP);
		        	proj.launchRight();
		        	sleepTime = 110;
		        }
		        	
				if (this.getX() > this.getEnemy().getX())
		        {
		        	proj.setFill(wormLeftP);
		        	proj.launchLeft();
		        	sleepTime = 110;
		        }
		        	
					if(getBoundary(proj.getX(), proj.getY(), proj.getWidth(), 
	        			proj.getHeight()).intersects(getBoundary(this.getEnemy().getX(), this.getEnemy().getY(), 
	        					this.getEnemy().getWidth(), this.getEnemy().getHeight())))
					{
		        		this.getEnemy().reduceHealth(120);
		        		if (!this.getEnemy().isAlive())
		        		{
		        			game.getGamePane().getChildren().remove(this.getEnemy());
		        		}
		        		
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
					}
		        	
		        	if (proj.getX() > primaryScreenBounds.getWidth() - proj.getWidth())
		        	{
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
		        	}
		        	
		        	if (proj.getX() < 0)
		        	{
		        		game.getGamePane().getChildren().remove(proj);
		        		sleepTime = 0;
		        		shooting = false;
		        	}
		        	
		        }
			
			if (moving)
	     	{
				actionNum = (int)(Math.random()*(200) + 1);
		    	if (actionNum == 5)
		    		jumping = true;
		    	
	     		time = (int)((9.4)/this.getMovementSpeed());
	     		
	     		if (this.getDirection().equalsIgnoreCase("right"))
	     		{
	     			this.setX(this.getX() + this.getMovementSpeed() * time);
	     			if (bossLevel == 1)
	     				this.setFill(metaManRightP);
	     			if(bossLevel == 2)
	     				this.setFill(gunslingerRightP);
	     		}
	     		if (this.getDirection().equalsIgnoreCase("left"))
	     		{
	     			if (bossLevel == 1)
	     				this.setFill(metaManLeftP);
	     			if (bossLevel == 2)
	     				this.setFill(gunslingerLeftP);
	     				this.setX(this.getX() - this.getMovementSpeed() * time);
	     		}
	     		
	     		if (xFinal == this.getX())
	     		{	
	     			if (this.getDirection().equalsIgnoreCase("right"))
	         		{
	     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
	     				this.setDirection("left");
	     				checkXFinal(this);
	     				if (bossLevel == 1)
	     					this.setFill(metaManRightP);
	     				if (bossLevel == 2)
	     					this.setFill(gunslingerLeftP);
	         		}
	         		
	     			else if (this.getDirection().equalsIgnoreCase("left"))
	         		{
	     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
	         			this.setDirection("right");
	         			checkXFinal(this);
	         			if (bossLevel == 1)
	         				this.setFill(metaManRightP);
	         			if (bossLevel == 2)
	         				this.setFill(gunslingerRightP);
	         		}
	     		}
	     	}
			
			if (jumping) 
	        {
				
	        	//Simulating gravity so the character falls down after jumping
	            this.setY(this.getY() - 10 + gravity);
	            gravity += GRAVITAIONALFORCE;
	            
	            //If the character's y is equal to the ground's y
	            if( (this.getY() + this.getHeight() ) > game.getGround().getY()) 
	            {
	            	gravity = 0;
	            	jumping = false;
	               
	            	if (this.getDirection().equalsIgnoreCase("left"))
	            	{
	            		if (bossLevel == 1)
		     				this.setFill(metaManLeftP);
		     			if (bossLevel == 2)
		     				this.setFill(gunslingerLeftP);
	            	}
	               
	            	if (this.getDirection().equalsIgnoreCase("right"))
	            	{
	            		if (bossLevel == 1)
	         				this.setFill(metaManRightP);
	         			if (bossLevel == 2)
	         				this.setFill(gunslingerRightP);
	            	}
	            }
	        }
			
	    	if (this.getX() < 0)
	    	{
	    		this.setDirection("right");
	    		this.setX(0);    
	    		xFinal = (int)(Math.random()*( (maxPosition + 1) ) );
	    		checkXFinal(this);
	    		if (bossLevel == 1)
     				this.setFill(metaManRightP);
     			if (bossLevel == 2)
     				this.setFill(gunslingerRightP);
	        }
			
			if (this.getX() > maxPosition)
	    	{
	    		this.setDirection("left");
	    		this.setX(maxPosition);
	    		xFinal = (int)(Math.random()*((maxPosition + 1)));
	    		checkXFinal(this);
	    		if (bossLevel == 1)
     				this.setFill(metaManLeftP);
     			if (bossLevel == 2)
     				this.setFill(gunslingerLeftP);
	    	}
			
			if (this.getY() < 0)
	    		this.setY(0);
			
			if ( (this.getY() + this.getHeight() ) > (game.getGround().getY() - game.getGround().getHeight()))
	        	this.setY(game.getGround().getY() - (this.getHeight()));
		}
	}
	
	private void checkXFinal(Character enemy)
	{
		while ( (enemy.getDirection().equalsIgnoreCase("right") ) && (xFinal < enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while ( (enemy.getDirection().equalsIgnoreCase("left") ) && (xFinal > enemy.getX() ))
    		xFinal = (int)(Math.random()*((maxPosition  + 1)));
    	
    	while (xFinal > maxPosition)
    		xFinal = maxPosition;
    	
    	while( (xFinal % 5) != 0)
    		xFinal--;
    	
	}
	
	public void shoot()
	{
		proj = new Projectile(this.getX() + this.getWidth(), this.getY() + (this.getHeight() / 2), this.direction);
		game.getGamePane().getChildren().add(proj);
		shooting = true;
	}
	
	public void setBossLevel(int bossLevel)
	{
		this.bossLevel = bossLevel;
	}
	
	public int getBossLevel()
	{
		return this.bossLevel;
	}
}
