//package AoJGame;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle
{
    private final int MOVEMENTSPEED = 10;
    private String direction;

    public Projectile(double x, double y, String direction)
    {
        this.direction = direction;
        this.setX(x);
        this.setY(y);
        this.setWidth(40);
        this.setHeight(40);
        //this.setFill(Color.RED);
        //this.setStrokeWidth(100);
    }

    public String getDirection()
    {
        return this.direction;
    }

    public void launchRight()
    {
        this.setX(this.getX() + MOVEMENTSPEED);
    }

    public void launchLeft()
    {
        this.setX(this.getX() - MOVEMENTSPEED);
    }
}