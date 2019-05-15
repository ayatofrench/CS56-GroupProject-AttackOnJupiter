package AoJGame;

import java.util.ArrayList;

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
    private Image bulletRight = new Image("file:Pictures/arrow_right.png");
    private ImagePattern bulletRightP = new ImagePattern(bulletRight);
    private Image bulletLeft = new Image("file:Pictures/arrow_left.png");
    private ImagePattern bulletLeftP = new ImagePattern(bulletLeft);
    private Projectile proj;
	 
	public RangedCharacter()
	{
        this.setFill(archerRightP);
		this.setDirection("right");
		this.setHealth(120);
		this.setWidth(primaryScreenBounds.getWidth() * .15);
		this.setHeight(primaryScreenBounds.getHeight()*(.25));
        //this.setStroke(Color.GREEN);
		this.setX(0);
		this.setY(primaryScreenBounds.getHeight()*(.84));
		maxPosition = (int)(primaryScreenBounds.getWidth() - this.getWidth());
		while(maxPosition % 5 != 0)
			maxPosition--;
	}
	
	public void shoot()
	{
		
		proj = new Projectile(this.getX() + this.getWidth(), this.getY() + (this.getHeight() / 2), this.direction);
		game.getGamePane().getChildren().add(proj);
		shooting = true;
	}
	
	public boolean isShooting()
	{
		if (shooting == false)
			return false;
		
		return true;
	}
	
	@Override
	public void handleMovement(ArrayList input) 
	{	
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
        
        if (input.contains("RIGHT"))
        {
        	this.setDirection("Right");
        	this.setFill(archerAttackRightP);
        	this.shoot();
        }
        
        if (input.contains("LEFT"))
        {
        	this.setDirection("left");
        	this.setFill(archerLeftP);
        	this.shoot();
        }
        
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
        }

        if(jumping) 
        {
            this.setY(this.getY() - 10 + gravity);
            gravity += GRAVITAIONALFORCE;

            if(this.getY() + this.getHeight() > game.getGround().getY()) 
            {
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

        if (this.getX() > maxPosition)
            this.setX(maxPosition);
	}
}
