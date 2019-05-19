//package application;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.scene.Group;
//import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Screen;
//
//public class RunIt extends Application implements Runnable
//{
//	private boolean running = false;
//	private Thread thread;
//	private objects.Character lisa;
//    private boolean jumping = false;
//    private double gravity = 0;
//    private double prevPos = 0;
//    private double prevXPos = 0;
//    private double time = 0;
//    private boolean moving;
//	private double xFinal;
//
//	public static void main(String [] args)
//	{
//		Application.launch(args);
//		System.exit(0);
//	}
//
//	public void start(Stage primaryStage) throws FileNotFoundException
//	{
//		//Getting native screen dimensions and setting that equal to the screen height and width
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
//        primaryStage.setFullScreen(true);
//        primaryStage.setResizable(false);
//        double groundLevel = primaryScreenBounds.getHeight()*(.84);
//		Scene scene = new Scene(new javafx.scene.Group(), primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
//
//		MeleeCharacter lisa = new MeleeCharacter();
//		lisa.setFill(Color.YELLOW);
//		objects.Character enemy = new objects.Character();
//
//		//Initializing background image settings
//		Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
//		background.setX(0);
//		background.setY(0);
//
//		//Initializing ground settings
//		Rectangle ground = new Rectangle();
//		ground.setFill(Color.BLACK);
//		ground.setStroke(Color.ORANGE);
//        ground.setOpacity(0);
//		ground.setWidth(scene.getWidth());
//		ground.setX(0);
//        ground.setHeight(1);
//		ground.setY(groundLevel);
//
//		enemy.setHealth(120);
//		enemy.setWidth(primaryScreenBounds.getWidth() * .12);
//		enemy.setHeight(primaryScreenBounds.getHeight()*(.25));
//		enemy.setX(0);
//		enemy.setY(primaryScreenBounds.getHeight()*(.84) - enemy.getHeight());
//		enemy.setMovementSpeed(5);
//		//enemy.setStroke(Color.RED);
//
//		//Initializing character settings
//		lisa.setHealth(120);
//		lisa.setWidth(primaryScreenBounds.getWidth() * .12);
//		lisa.setHeight(primaryScreenBounds.getHeight()*(.25));
//        //lisa.setStroke(Color.GREEN);
//		lisa.setX(primaryScreenBounds.getWidth() - enemy.getWidth());
//		lisa.setY(primaryScreenBounds.getHeight()*(.84));
//		lisa.setMovementSpeed(5);
//
//		((Group)scene.getRoot()).getChildren().addAll(background, lisa, enemy, ground);
//
//		//Initializing all character frames
//		Image setting = new Image("file:Pictures/back.gif");
//        ImagePattern settingP = new ImagePattern(setting);
//        background.setFill(settingP);
//
//        //Initializing images for Lisa
//        Image lisa_right = new Image("file:Pictures/lisa_right.gif");
//        ImagePattern lisa_rightP = new ImagePattern(lisa_right);
//
//        Image lisa_left = new Image("file:Pictures/lisa_left.gif");
//        ImagePattern lisa_leftP = new ImagePattern(lisa_left);
//        lisa.setFill(lisa_leftP);
//
//        Image attackLeft = new Image("file:Pictures/attack_left.gif");
//        ImagePattern attack_leftP = new ImagePattern(attackLeft);
//
//        Image attackRight = new Image("file:Pictures/attack_right.gif");
//        ImagePattern attack_rightP = new ImagePattern(attackRight);
//
//        Image lisaStillRight = new Image("file:Pictures/lisaStillRight.png");
//        ImagePattern lisaStillRightP = new ImagePattern(lisaStillRight);
//        Image lisaStillLeft = new Image("file:Pictures/lisaStillLeft.png");
//        ImagePattern lisaStillLeftP = new ImagePattern(lisaStillLeft);
//
//        //Initializing images for Enemy
//        Image enemyRight = new Image("file:Pictures/archerRight.gif");
//        ImagePattern enemyRightP = new ImagePattern(enemyRight);
//        enemy.setFill(enemyRightP);
//
//
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Game test");
//
//		ArrayList<String> input = new ArrayList<String>();
//
//		//Input handling
//		scene.setOnKeyPressed((event) -> {
//            String code = event.getCode().toString();
//
//            if(!input.contains(code))
//                input.add(code);
//
//            if(input.contains("SPACE"))
//            {
//                jumping = true;
//                prevPos = lisa.getY();
//            }
//
//            if (input.contains("E") || input.contains("P"))
//            {
//            	if (input.contains("E"))
//            		enemy.setDirection("right");
//            	if (input.contains("P"))
//            		enemy.setDirection("left");
//
//            	xFinal = (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//
//            	while((xFinal % 5) != 0)
//            	{
//            		xFinal--;
//            	}
//
//            	moving = true;
//
//            	if (xFinal >= primaryScreenBounds.getWidth())
//            	{
//        			xFinal = primaryScreenBounds.getWidth() - enemy.getWidth();
//        			System.out.println("Here: " + xFinal);
//            	}
//
//            	System.out.println("xFinal is: " + xFinal);
//            }
//
//            prevXPos = lisa.getX();
//        });
//
//        scene.setOnKeyReleased((event) -> {
//            String code = event.getCode().toString();
//
//
//                input.remove(code);
//        });
//
//		new AnimationTimer()
//		{
//            public void handle(long currentNanoTime)
//            {
//            	//Identifying which way the character is facing
//            	if (lisa.getX() > prevXPos)
//            		lisa.setDirection("right");
//
//            	if (lisa.getX() < prevXPos)
//            		lisa.setDirection("left");
//
//            	//objects.Character Ability 1
//            	if(input.contains("Q"))
//            		lisa.bulwark();
//
//            	//Attack Directions
//            	if(input.contains("RIGHT"))
//            	{
//            		lisa.setFill(attack_rightP);
//            	}
//
//            	if(input.contains("LEFT"))
//            	{
//            		lisa.setFill(attack_leftP);
//            	}
//
//            	//Movement keys
//            	if (moving)
//            	{
//            		System.out.println("Final positon: " + xFinal);
//            		time = (int)((9.4)/enemy.getMovementSpeed());
//
//            		if (enemy.getDirection().equalsIgnoreCase("right"))
//            			enemy.setX(enemy.getMovementSpeed()*time + enemy.getX());
//            		if (enemy.getDirection().equalsIgnoreCase("left"))
//            			enemy.setX(enemy.getX() - enemy.getMovementSpeed() * time);
//
//            		System.out.println("Enemy's x-position is: " + enemy.getX());
//
//            		if (xFinal == enemy.getX())
//            		{
//            			if (enemy.getDirection().equalsIgnoreCase("right"))
//            			{
//            				enemy.setDirection("left");
//            			}
//            			if (enemy.getDirection().equalsIgnoreCase("left"))
//            				enemy.setDirection("right");
//            			//System.out.println("xFinal (" + xFinal + ") < " + "enemy.getx == (" +
//            			//enemy.getX() + ") is " + (xFinal == enemy.getX()));
//            			xFinal = (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//            			moving = false;
//            		}
//            	}
//
//            	if(input.contains("A"))
//            	{
//                    lisa.setX(lisa.getX() - lisa.getMovementSpeed());
//                    lisa.setFill(lisa_leftP);
//                }
//
//            	if(input.contains(("D")))
//            	{
//                    lisa.setX(lisa.getX() + lisa.getMovementSpeed());
//                    lisa.setFill(lisa_rightP);
//                }
//
//            	//Handling actions conducted while moving
//                if(jumping)
//                {
//                	//Determining direction while jumping
//                	if (lisa.getDirection().equalsIgnoreCase("right"))
//                		lisa.setFill(lisaStillRightP);
//
//                	if (lisa.getDirection().equalsIgnoreCase("left"))
//                		lisa.setFill(lisaStillLeftP);
//
//                	//Simulating gravity so the character falls down after jumping
//                    lisa.setY(lisa.getY() - 10 + gravity);
//                    gravity += 0.5;
//
//                    //Print statements which will be removed later on
//                    System.out.println(lisa.getY());
//                    System.out.println(jumping);
//                    System.out.println("Gravity: " + gravity);
//
//                    //If the character's y is equal to the ground's y
//                   if(lisa.getY() + lisa.getHeight() > ground.getY())
//                   {
//                       gravity = 0;
//                       jumping = false;
//
//                       //
//                       if (lisa.getDirection().equalsIgnoreCase("left"))
//                    	   lisa.setFill(lisa_leftP);
//
//                       if (lisa.getDirection().equalsIgnoreCase("right"))
//                    	   lisa.setFill(lisa_rightP);
//                   }
//                }
//
//                //If Lisa collides with enemy
//                /*if(getBoundary(lisa.getX(), lisa.getY(), lisa.getWidth(),
//                		lisa.getHeight()).intersects(getBoundary(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())))
//                {
//                	if (lisa.getX() > enemy.getX())
//                    	lisa.setX(enemy.getX() + enemy.getWidth());
//                    if (lisa.getX() < enemy.getX())
//                    	lisa.setX(enemy.getX() - enemy.getWidth());
//                }*/
//
//                //Ensuring Lisa doesn't fall through the earth
//            	if (((lisa.getY()) + (lisa.getHeight())) > (ground.getY() - ground.getHeight()))
//                	lisa.setY(ground.getY() - (lisa.getHeight()));
//            	if (((enemy.getY()) + (enemy.getHeight())) > (ground.getY() - ground.getHeight()))
//                	enemy.setY(ground.getY() - (enemy.getHeight()));
//
//            	//If Lisa goes above the screen
//            	if (lisa.getY() < 0)
//            		lisa.setY(0);
//            	if (enemy.getY() < 0)
//            		enemy.setY(0);
//
//            	//If lisa goes to the left border of the screen
//            	if ((lisa.getX() < 0))
//            	{
//            		lisa.setX(0);
//                }
//            	if ((enemy.getX() < 0))
//            	{
//            		enemy.setX(0);
//            		xFinal = xFinal + ((int)(Math.random()*((primaryScreenBounds.getWidth()  + 1))));
//
//            		while(xFinal < 0 || xFinal > primaryScreenBounds.getWidth())
//            			xFinal = xFinal - (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//                }
//
//            	//If lisa goes to the right of the screen
//            	if ((lisa.getX() > (scene.getWidth() - lisa.getWidth())))
//            		lisa.setX(scene.getWidth() - lisa.getWidth());
//            	if ((enemy.getX() > (scene.getWidth() - enemy.getWidth())))
//            	{
//            		enemy.setX(scene.getWidth() - enemy.getWidth());
//            		xFinal = (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//
//            		while(xFinal < 0 || xFinal > primaryScreenBounds.getWidth())
//            			xFinal = xFinal - (int)(Math.random()*((primaryScreenBounds.getWidth()  + 1)));
//            	}
//
//            }
//
//		}.start();
//
//		primaryStage.show();
//	}
//
//	/*private synchronized void start()
//	{
//		if (running)
//			start();
//
//		running = true;
//		thread = new Thread(this);
//		thread.start();
//	}*/
//
//	/*private synchronized void stop()
//	{
//		if (!running)
//			return;
//
//		running = false;
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.exit(0);
//	}*/
//
//	public void run()
//	{
//		//init
//		long lastTime = System.nanoTime();
//		final double amountOfTicks = 60.0;
//		double ns = 1000000000 / amountOfTicks;
//		double delta = 0;
//		int updates = 0;
//		int frames = 0;
//		long timer = System.currentTimeMillis();
//
//		while (running)
//		{
//			long now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//
//			if (delta >= 1)
//			{
//				tick();
//				updates++;
//				delta--;
//			}
//
//			//render();
//			frames++;
//
//			if (System.currentTimeMillis() - timer > 1000)
//			{
//				timer += 1000;
//				//System.out.println("Ticks: " + updates + ", Frames: " + frames);
//				updates = 0;
//				frames = 0;
//			}
//		}
//		render();
//
//		//stop();
//	}
//
//	private void tick()
//	{
//		lisa.tick();
//	}
//
//	private void render()
//	{
//		//BufferStrategy bs = this.get;
//	}
//
//	private javafx.geometry.Rectangle2D getBoundary(double posX, double posY, double width, double height) {
//        return new javafx.geometry.Rectangle2D(posX, posY, width, height);
//    }
//}
