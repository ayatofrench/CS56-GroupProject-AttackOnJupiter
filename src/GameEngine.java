import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameEngine
{

    private GamePane gamePane;
    private StartMenuPane startPane;
    private Scene scene;
    private Scene gameScene;
    private AnimationTimer gameLoop;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private final double GROUNDLEVEL = primaryScreenBounds.getHeight()*(.84);
    private Rectangle ground = new Rectangle();
    private ArrayList<String> input;
    private Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private Rectangle backgroundCredits = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private Rectangle backgroundHTP = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private RangedCharacter lisa;
    private NPC enemy;
    private boolean paused = false;
    private double gravity = 0;
    private double prevPos = 0;

     public GameEngine()
     {
        startPane = new StartMenuPane();
        gameLoop = gameLoop();

        input = new ArrayList<>();

        scene = new Scene(startPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        btnListener();
     }

     public void initGameTwo()
     {
    	 gamePane = new GamePane();
    	 gamePane.getChildren().clear();

    	 //Initializing fighters
    	 lisa = new RangedCharacter();
    	 enemy = new NPC();
    	 enemy.setBossLevel(2);

    	 //Setting enemies
    	 lisa.setEnemy(enemy);
    	 enemy.setEnemy(lisa);
    	 //enemy.setFill(gsP);

         //Add character and ground to game
         gamePane.getChildren().addAll(background, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize setting
         createSetting();

         //Initialize ground
         createGround();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreenExitHint("NetBeans is trash");
         Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
         Main.getStage().setFullScreen(true);
     }


     public void initGame()
     {
    	 gamePane = new GamePane();
    	 gamePane.getChildren().clear();

    	 //Initializing fighters
    	 lisa = new RangedCharacter();
    	 enemy = new NPC();
    	 enemy.setBossLevel(1);

    	 //Setting enemies
    	 lisa.setEnemy(enemy);
    	 enemy.setEnemy(lisa);

         //Add character and ground to game
         gamePane.getChildren().addAll(background, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize setting
         createSetting();

         //Initialize ground
         createGround();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreenExitHint("NetBeans is trash");
         Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
         Main.getStage().setFullScreen(true);
     }

     public AnimationTimer gameLoop()
    {
        return gameLoop = new AnimationTimer()
        {
            @Override
            public void handle(long now) {

            	if (lisa.getSleepTime() != 0)
            		lisa.setSleepTime(lisa.getSleepTime() - 1);
            	if (enemy.getSleepTime() != 0)
            		enemy.setSleepTime(enemy.getSleepTime() - 1);
                lisa.handleMovement(input);
                enemy.handleMovement(input);
            }
        };
    }

     private void keyListener()
     {
    	gameScene.setOnKeyTyped((event) ->
    	{
    		String code = event.getCode().toString();

    		if (lisa.isShooting() && input.contains("RIGHT"))
    		{

    			input.remove(code);
    		}

        	if (lisa.isShooting() && input.contains("LEFT"))
            	input.remove(code);

        	if(input.contains("ESCAPE"))
        		Main.getStage().setFullScreen(false);
    	});

        gameScene.setOnKeyPressed((event) ->
        {
        	String code = event.getCode().toString();

        	if(!input.contains(code))
                	input.add(code);

        	if(input.contains("SPACE")) {
                lisa.jump();
        	}

        	if (input.contains("F12"))
        		Main.getStage().setFullScreen(true);

        	if(input.contains("P")) {
        		pauseGame();
        }

        lisa.setPrevXPos(lisa.getX());
    });

        gameScene.setOnKeyReleased((event) -> {
            String code = event.getCode().toString();

            input.remove(code);
        });
    }

     private void btnListener() {
         startPane.getPlayButton().setOnMouseClicked((event) -> {
            initGame();
         });

         startPane.getHTPButton().setOnMouseClicked(((event) ->
         {
        	 Stage htpWindow = new Stage();
             //BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/HowToPlay.png"),
        	//BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        	     //BackgroundSize.DEFAULT);
                           backgroundHTP.setWidth(1100);
                  backgroundHTP.setHeight(582);
                  Image setting = new Image("file:Pictures/menu/HowToPlay.png");
                  ImagePattern settingP = new ImagePattern(setting);
                  backgroundHTP.setFill(settingP);

             Pane htpPane=new Pane();
                      htpPane.getChildren().add(backgroundHTP);
             Scene htpScene=new Scene(htpPane,1100,582);
             htpWindow.setScene(htpScene);
             Button btnBack = new Button("Go Back to Main Menu");

                      btnBack.setLayoutX(1100*.39);
             btnBack.setStyle("-fx-border-color: black; -fx-background-color: transparent;");
             btnBack.setTextFill(Color.BLACK);

             Font Htp = new Font("Comic Sans MS", 20);
             btnBack.setFont(Htp);
             htpPane.getChildren().add(btnBack);
                      btnBack.setAlignment(Pos.CENTER);

             // htpPane.setBackground(new Background(imageBG));
             htpWindow.show();

             //button to close how to play

        	 //button to close how to play
        	 btnBack.setOnAction(new EventHandler<ActionEvent>()
        	 {
               @Override
               public void handle(ActionEvent event)
               {
                   htpWindow.close();
               }

        	 });
         }));

         startPane.getCreditsButton().setOnMouseClicked((event) ->
         {
        	 backgroundCredits.setWidth(primaryScreenBounds.getWidth());
             backgroundCredits.setHeight(primaryScreenBounds.getHeight());
             Image setting = new Image("file:Pictures/menu/AoJLoadingScreen.gif");
             ImagePattern settingP = new ImagePattern(setting);
             backgroundCredits.setFill(settingP);


             Pane creditPane = new Pane();

	       	Scene credits = new Scene(creditPane, scene.getWidth(), primaryScreenBounds.getHeight());
	       	//creditPane.setAlignment(Pos.BOTTOM_CENTER);
	       	Font CreditFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .03472);
	       	creditPane.getChildren().add(backgroundCredits);

	       	//made 2 labels for names due to formatting issues
	       	Label names1 = new Label("Shant"+"\n"+"Zak"+"\n"+"Ryoto"+"\n");
	       	names1.setOpacity(.5);
	       	names1.setTextFill(Color.CYAN);
	       	names1.setFont(CreditFont);
	       	names1.setLayoutX(primaryScreenBounds.getWidth() / 2);
	       	names1.setLayoutY(primaryScreenBounds.getHeight() * 0.52);
	       	creditPane.getChildren().add(names1);
	       	Label names = new Label("Alex"+"\n"+"Ayato"+"\n"+"Apurav"+"\n"+"Donovan");
	       	names.setOpacity(.5);
	       	names.setTextFill(Color.MEDIUMSLATEBLUE);
	       	names.setFont(CreditFont);
	       	names.setLayoutX(primaryScreenBounds.getWidth() / 2);
	       	names.setLayoutY(primaryScreenBounds.getHeight() * 0.67);
	       	creditPane.getChildren().add(names);

	       	//primaryStage.setTitle("Credits");
	       	Main.getStage().setScene(credits);
	       	Main.getStage().setMaximized(true);

	       	//button to for going back to main menu
	       	Button btnBack = new Button("Go Back to Main Menu");
	       	btnBack.setAlignment(Pos.BOTTOM_CENTER);
	       	btnBack.setOpacity(.5);
	       	btnBack.setStyle("-fx-border-color: black; -fx-background-color: darkslateblue;");
	       	btnBack.setTextFill(Color.MEDIUMSLATEBLUE);
	       	btnBack.setFont(CreditFont);
	       	btnBack.setLayoutX(primaryScreenBounds.getWidth() * .43);
	       	btnBack.setLayoutY(primaryScreenBounds.getHeight() * .87);
	       	creditPane.getChildren().add(btnBack);
	      	btnBack.setAlignment(Pos.BOTTOM_RIGHT);

	      		//button action
	      		btnBack.setOnAction(new EventHandler<ActionEvent>()
	      		{
	      			@Override
	      			public void handle(ActionEvent event)
	      			{
	      				Main.getStage().setScene(scene);
	      				//Main.getStage().setFullScreen(true);
	      			}
	      		});
	      });


         startPane.getQuitButton().setOnMouseClicked((event ->
         {
        	 Stage quitWindow = new Stage();
             Font font = new Font("Times New Roman", 30);
             BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/menu/AoJTerror.gif"),
                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                     BackgroundSize.DEFAULT);

             FlowPane fPane = new FlowPane();
             fPane.setBackground(new Background(imageBG));
             fPane.setHgap(5);
             fPane.setAlignment(Pos.BOTTOM_CENTER);

             //no button for confirming quit
             Button btnNo = new Button("No");
             btnNo.setStyle("-fx-border-color: darkorange; -fx-background-color: coral");
             btnNo.setFont(font);
             btnNo.setOpacity(.5);
             btnNo.setTextFill(Color.BLACK);

             //yes button for confirming quit
             Button btnYes = new Button("Yes");
             btnYes.setStyle("-fx-border-color: darkorange; -fx-background-color: coral");
             btnYes.setFont(font);
             btnYes.setOpacity(0.5);
             btnYes.setTextFill(Color.BLACK);

            fPane.getChildren().addAll(btnYes, btnNo);
     		Scene quitScene = new Scene(fPane, 498, 317);
     		quitWindow.setScene(quitScene);
     		quitWindow.setTitle("Quit Window");
     		quitWindow.show();

             btnNo.setOnMouseClicked((e) -> {
            	 quitWindow.close();
                 Main.getStage().setScene(scene);
             });
             btnYes.setOnMouseClicked((e) -> {
                 System.exit(0);
             });
         }));
    }

     public void pauseGame() {
        if(!paused) {
            gameLoop.stop();
            paused = true;
        }
        else {
            gameLoop.start();
            paused = false;
        }
    }

     private void createSetting()
    {
    	Image settingRain = new Image("file:Pictures/scene/rain_back.gif");
        ImagePattern settingRainP = new ImagePattern(settingRain);


        Image settingNight = new Image("file:Pictures/scene/dark_ground_back.gif");
        ImagePattern settingNightP = new ImagePattern(settingNight);

        if (enemy.getBossLevel() == 1)
        	background.setFill(settingRainP);

        if (enemy.getBossLevel() == 2)
        	background.setFill(settingNightP);
    }

     private void createGround()
    {
        ground.setFill(Color.BLACK);
        ground.setStroke(Color.ORANGE);
        ground.setOpacity(0);
        ground.setWidth(scene.getWidth());
        ground.setX(0);
        ground.setHeight(1);
        ground.setY(GROUNDLEVEL);

    }

     public Scene getScene()
    {
        return scene;
    }

     public GamePane getGamePane()
     {
    	 return this.gamePane;
     }

     public Rectangle getGround()
    {
         return ground;
    }

     public Rectangle getSetting()
    {
    	return this.background;
    }

     private void displayDeath()
     {
    	 Font deathFont = new Font("Comic Sans MS", 25);
    	 Label lbl = new Label("There was a death!");
    	 lbl.setFont(deathFont);

     }
}
