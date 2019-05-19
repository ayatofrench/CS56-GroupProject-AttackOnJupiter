//package application;
//
//import java.awt.Graphics;
//
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//public class Character2 extends Rectangle
//{
//	private int healthPoints;
//	protected Rectangle hitbox;
//	private double x;
//	private double y;
//	private int movementSpeed;
//	private int damageLost;
//	private String direction;
//
//	public objects.Character() {}
//
//
//	public objects.Character(double x, double y)
//	{
//		this.x = x;
//		this.y = y;
//	}
//
//	public void tick()
//	{
//
//	}
//
//	public void render(Graphics g)
//	{
//		//g.drawImage(this.x, this.y, null);
//	}
//
//	public void reduceHealth(int dmg)
//	{
//		damageLost = dmg;
//		this.healthPoints -= dmg;
//	}
//
//	public void setHealth(int healthPoints)
//	{
//		this.healthPoints = healthPoints;
//	}
//
//	public void setMovementSpeed(int movementSpeed)
//	{
//		this.movementSpeed = movementSpeed;
//	}
//
//	public void setDirection(String direction)
//	{
//		this.direction = direction;
//	}
//
//	public void setHitBox()
//	{
//		//this.hitbox.setX(this.x);
//		//this.hitbox.setY(this.y);
//	}
//
//	public int getHealth()
//	{
//		return this.healthPoints;
//	}
//
//	public int getMovementSpeed()
//	{
//		return this.movementSpeed;
//	}
//
//	public String getDirection()
//	{
//		return this.direction;
//	}
//
//	public void defenseAbility(){}
//
//	public boolean isAlive()
//	{
//		if (this.healthPoints > 0)
//			return true;
//
//		else if (this.healthPoints <= 0)
//			return false;
//
//		else
//			return true;
//	}
//}