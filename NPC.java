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
	private Image metaManLeft = new Image("file:Pictures/metaman_left.gif");
    private ImagePattern metaManLeftP = new ImagePattern(metaManLeft);
    private Image metaManRight = new Image("file:Pictures/metaman_left.gif");
    private ImagePattern metaManRightP = new ImagePattern(metaManRight);
    private Image worm_right = new Image("file:Pictures/worm_right.gif");
    private ImagePattern wormRightP = new ImagePattern(worm_right);
    private Image worm_left = new Image("file:Pictures/worm_left.gif");
    private ImagePattern wormLeftP = new ImagePattern(worm_left);
    
    public NPC()
    {
    	this.setFill(metaManLeftP);
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
				proj.setFill(wormRightP);
	        	proj.launchRight();
	        }
	        	
			if (proj.getDirection().equalsIgnoreCase("left"))
	        {
	        	proj.setFill(wormLeftP);
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
     			this.setFill(metaManRightP);
     		}
     		if (this.getDirection().equalsIgnoreCase("left"))
     		{
     			this.setFill(metaManLeftP);
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
         			this.setFill(metaManLeftP);
         		}
         		
     			else if (this.getDirection().equalsIgnoreCase("left"))
         		{
     				xFinal = (int)(Math.random()*((maxPosition  + 1)));
         			System.out.println("===============Here===================");
         			this.setDirection("right");
         			checkXFinal(this);
         			this.setFill(metaManRightP);
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
            		this.setFill(metaManLeftP);
               
            	if (this.getDirection().equalsIgnoreCase("right"))
            		this.setFill(metaManRightP);
            }
        }
		
    	if (this.getX() < 0)
    	{
    		this.setDirection("right");
    		this.setX(0);    
    		xFinal = (int)(Math.random()*( (maxPosition + 1) ) );
    		checkXFinal(this);
    		this.setFill(metaManRightP);
        }
		
		if (this.getX() > maxPosition)
    	{
    		this.setDirection("left");
    		this.setX(maxPosition);
    		xFinal = (int)(Math.random()*((maxPosition + 1)));
    		checkXFinal(this);
    		this.setFill(metaManLeftP);
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
