package application;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameEngine {

    private GamePane gamePane;
    private StartMenuPane startPane;
    private Scene scene;
    private Scene gameScene;

    private AnimationTimer gameLoop;

    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private final double GROUNDLEVEL = primaryScreenBounds.getHeight()*(.84);
    private Rectangle ground = new Rectangle();
    private ArrayList<String> input;
    Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private RangedCharacter lisa = new RangedCharacter();
    private NPC enemy = new NPC();
    private boolean paused = false;
    double gravity = 0;
    double prevPos = 0;

     public GameEngine() {

        gamePane = new GamePane();
        startPane = new StartMenuPane();
        gameLoop = gameLoop();

        input = new ArrayList<>();

        scene = new Scene(startPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        btnListener();

     }

     private void initGame() 
     {
         //Add character and ground to game
         gamePane.getChildren().addAll(background, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize setting
         createSetting();
         
         //Initialize ground
         createGround();
         
         //Initialize textures
         createTextures();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreen(true);
     }


    public AnimationTimer gameLoop() {
        return gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lisa.handleMovement(input);
                enemy.handleMovement(input);
            }
        };
    }

    private void keyListener() {

        gameScene.setOnKeyPressed((event) -> {
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
        	 BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/aojscreenplay.gif"), 
   				  BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
   		      BackgroundSize.DEFAULT);
        	 VBox htpPane=new VBox();
        	 Scene htpScene=new Scene(htpPane,1100,582);
        	 htpWindow.setScene(htpScene);
        	 Button btnBack = new Button("Go Back to Main Menu");
        	 btnBack.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        	 btnBack.setTextFill(Color.DEEPPINK);
        	 Font Htp = new Font("Comic Sans MS", 30);
        	 btnBack.setFont(Htp);
        	 htpPane.getChildren().add(btnBack);
        	 htpPane.setBackground(new Background(imageBG));
        	 htpWindow.show();

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
        	 BackgroundImage image = new BackgroundImage(new Image("file:Pictures/AoJLoadingScreen.gif"), 
					  BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			      BackgroundSize.DEFAULT);
       	
	       	VBox creditPane = new VBox();
	       	creditPane.setBackground(new Background(image));
	       	
	       	Scene credits = new Scene(creditPane, scene.getWidth(), 582);
	       	creditPane.setAlignment(Pos.BOTTOM_CENTER);
	       	Font CreditFont = new Font("Comic Sans MS", 25);
	       	
	       	//made 2 labels for names due to formatting issues
	       	Label names1 = new Label("Shant"+"\n"+"Zak"+"\n"+"Ryoto"+"\n");
	       	names1.setOpacity(.5);
	       	names1.setTextFill(Color.CYAN);
	       	names1.setFont(CreditFont);
	       	creditPane.getChildren().add(names1);
	       	Label names = new Label("   Alex"+"\n"+"   Ayoto"+"\n"+"   Apurav"+"\n"+"   Donovan");
	       	names.setOpacity(.5);
	       	names.setTextFill(Color.MEDIUMSLATEBLUE);
	       	names.setFont(CreditFont);
	       	creditPane.getChildren().add(names);
	       	
	       	//primaryStage.setTitle("Credits");
	       	Main.getStage().setScene(credits);
	       	
	       	//button to for going back to main menu
	       	Button btnBack = new Button("Go Back to Main Menu");
	       	btnBack.setAlignment(Pos.BOTTOM_CENTER);
	       	btnBack.setOpacity(.5);
	       	btnBack.setStyle("-fx-border-color: black; -fx-background-color: darkslateblue;");
	       	btnBack.setTextFill(Color.MEDIUMSLATEBLUE);
	       	btnBack.setFont(CreditFont);
	       	creditPane.getChildren().add(btnBack);
	      		btnBack.setAlignment(Pos.BOTTOM_RIGHT);
	          
	      		//button action
	      		btnBack.setOnAction(new EventHandler<ActionEvent>()
	      		{
	      			@Override
	      			public void handle(ActionEvent event) 
	      			{
	      				Main.getStage().setScene(scene);
	      				Main.getStage().setFullScreen(true);
	      			}
	      		});
	      });
         
         
         startPane.getQuitButton().setOnMouseClicked((event -> 
         {
             //quitWindow.initStyle(StageStyle.UNDECORATED);
        	 Stage quitWindow = new Stage();
             Font font = new Font("Times New Roman", 30);
             BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/AoJTerror.gif"),
                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                     BackgroundSize.DEFAULT);
             //
             FlowPane fPane = new FlowPane();
             fPane.setBackground(new Background(imageBG));
             fPane.setHgap(5);
             fPane.setAlignment(Pos.BOTTOM_CENTER);
             //
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
    	Image setting = new Image("file:Pictures/back.gif");
        ImagePattern settingP = new ImagePattern(setting);
        background.setFill(settingP);
    }
    
    private void createTextures()
    {
    	Image archerRight = new Image("file:Pictures/lisa_range_right.gif");
        ImagePattern archerRightP = new ImagePattern(archerRight);
        lisa.setFill(archerRightP);
    }
    
    private void createGround() {

        ground.setFill(Color.BLACK);
        ground.setStroke(Color.ORANGE);
        ground.setOpacity(0);
        ground.setWidth(scene.getWidth());
        ground.setX(0);
        ground.setHeight(1);
        ground.setY(GROUNDLEVEL);

    }

    public Scene getScene() {
        return scene;
    }

    public Rectangle getGround() {
         return ground;
    }
    
    public Rectangle getSetting()
    {
    	return this.background;
    }

}
