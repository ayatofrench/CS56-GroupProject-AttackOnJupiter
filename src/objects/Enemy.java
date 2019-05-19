package objects;

public class Enemy extends Character{

    private double time = 0;

    public Enemy() {
        super();
    }

    @Override
    public void handleMovement() {
        time = (int)((9.4)/MOVEMENTSPEED);

//        if (enemy.getDirection().equalsIgnoreCase("right"))
//            enemy.setX(enemy.getMovementSpeed()*time + enemy.getX());
//        if (enemy.getDirection().equalsIgnoreCase("left"))
//            enemy.setX(enemy.getX() - enemy.getMovementSpeed() * time);
//
//        System.out.println("Enemy's x-position is: " + enemy.getX());
//
//        if (xFinal == enemy.getX())
//        {
//            if (enemy.getDirection().equalsIgnoreCase("right"))
//            {
//                enemy.setDirection("left");
//            }
//            if (enemy.getDirection().equalsIgnoreCase("left"))
//                enemy.setDirection("right");
//            //System.out.println("xFinal (" + xFinal + ") < " + "enemy.getx == (" +
//            //enemy.getX() + ") is " + (xFinal == enemy.getX()));
//            xFinal = (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//            moving = false;
//        }
    }
}
