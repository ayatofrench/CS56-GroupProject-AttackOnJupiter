package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartMenuPane extends GridPane {

    private GameEngine game;

    private Button btnPlay;
    private Button btnQuit;
    private Button btnCredits;
    private Button btnHTP;

    public StartMenuPane() {
        Font font = new Font("Times New Roman", 30);
        this.setAlignment(Pos.CENTER);
        this.setVgap(15); //Sets vertical gap between nodes inside a pane
        BackgroundImage image = new BackgroundImage(new Image("file:Pictures/AttackOnJavaJupiterMenu.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT); //

        //Creating buttons
        //Play Button with its properties
        btnPlay = new Button("Play");
        btnPlay.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnPlay.setTextFill(Color.DEEPPINK);
        btnPlay.setFont(font);

        //How to Play button with its properties
        btnHTP = new Button("How to play");
        btnHTP.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnHTP.setTextFill(Color.DEEPPINK);
        btnHTP.setFont(font);

        //Credits Button with its properties
        btnCredits = new Button("Credits");
        btnCredits.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnCredits.setTextFill(Color.DEEPPINK);
        btnCredits.setFont(font);

        //Quit Button with its properties
        btnQuit = new Button("Quit");
        btnQuit.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnQuit.setTextFill(Color.DEEPPINK);
        btnQuit.setFont(font);

        //Adding nodes to the pane
        this.setBackground(new Background(image));
        this.add(btnPlay, 0, 0);
        this.add(btnHTP, 0, 1);
        this.add(btnCredits, 0, 2);
        this.add(btnQuit, 0, 3);


    }

    public Button getPlayButton() 
    {
        return btnPlay;
    }
    
    public Button getCreditsButton()
    {
    	return btnCredits;
    }
    
    public Button getHTPButton()
    {
    	return btnHTP;
    }

    public Button getQuitButton() 
    {
        return btnQuit;
    }

    public void setGameEngine(GameEngine game) {
        this.game = game;
    }

}