package AoJGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application 
{
    private static Stage stage;
    
    public static void main(String [] args)
    {
    	Application.launch();
    	System.exit(0);
    }

    public void start(Stage primaryStage) 
    {
        stage = primaryStage;
        GameEngine engine = new GameEngine();
        stage.setTitle("Game Test");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(engine.getScene());
        stage.show();
    }

    public static Stage getStage() 
    {
        return stage;
    }
}
