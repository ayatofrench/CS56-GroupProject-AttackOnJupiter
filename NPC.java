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
	private Image archerLeft = new Image("file:Pictures/lisa_range_left.gif");
    private ImagePattern archerLeftP = new ImagePattern(archerLeft);
    private Image archerRight = new Image("file:Pictures/lisa_range_right.gif");
    private ImagePattern archerRightP = new ImagePattern(archerRight);
    private Image bulletRight = new Image("file:Pictures/arrow_right.png");
    private ImagePattern bulletRightP = new ImagePattern(bulletRight);
    private Image bulletLeft = new Image("file:Pictures/arrow_left.png");
    private ImagePattern bulletLeftP = new ImagePattern(bulletLeft);
    
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
    	//this.setStroke(Color.RED);
    	moving = true;
    }
	@Override
	public void handleMovement(ArrayList input)
	{
        //System.out.println("xFinal is: " + xFinal);
		shootNum = (int)(Math.random()*(200) + 1);
		if (shootNum == 5)
			this.shoot();
			
		if (shooting)
	    {
			if (proj.getDirection().equalsIgnoreCase("right"))
			{
				proj.setFill(bulletRightP);
	        	proj.launchRight();
	        }
	        	
			if (proj.getDirection().equalsIgnoreCase("left"))
	        {
	        	proj.setFill(bulletLeftP);
	        	proj.launchLeft();
	        }
	        	
	        	/*if(getBoundary(lisa.getX(), lisa.getY(), lisa.getWidth(), 
	        			lisa.getHeight()).intersects(getBoundary(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())))
	        	{
	        		if (lisa.getX() > enemy.getX())
	        			lisa.setX(enemy.getX() + enemy.getWidth());
	        		if (lisa.getX() < enemy.getX())
	        			lisa.setX(enemy.getX() - enemy.getWidth());
	        	}*/
	        	
	        	if (proj.getX() > primaryScreenBounds.getWidth() - proj.getWidth())
	        	{
	        		game.getGamePane().getChildren().remove(proj);
	        		shooting = false;
	        	}
	        	
	        	if (proj.getX() < 0)
	        	{
	        		game.getGamePane().getChildren().remove(proj);
	        		shooting = false;
	        	}
	        	
	        	//game.getGamePane().getChildren().remove(proj);
	        }
		
		if (moving)
     	{
			actionNum = (int)(Math.random()*(200) + 1);
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
     		
     		//System.out.println("Enemy's x-position is: " + this.getX() + "\nEnemy's direction is: " + this.getDirection());
     		
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
            if( (this.getY() + this.getHeight() ) > game.getGround().getY()) 
            {
            	gravity = 0;
            	jumping = false;
               
            	if (this.getDirection().equalsIgnoreCase("left"))
            		this.setFill(archerLeftP);
               
            	if (this.getDirection().equalsIgnoreCase("right"))
            		this.setFill(archerRightP);
            }
        }
		
    	if (this.getX() < 0)
    	{
    		this.setDirection("right");
    		this.setX(0);    
    		xFinal = (int)(Math.random()*( (maxPosition + 1) ) );
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
		
		if ( (this.getY() + this.getHeight() ) > (game.getGround().getY() - game.getGround().getHeight()))
        	this.setY(game.getGround().getY() - (this.getHeight()));
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
}
