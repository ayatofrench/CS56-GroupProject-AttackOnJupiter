package sample;

//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //StackPane test = new StackPane();

        Scene scene = new Scene(new javafx.scene.Group(), 512, 512);
        scene.getStylesheets().add("sample/test.css");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);

        Rectangle circ = new Rectangle(50, 50);
        circ.getStyleClass().add("my-circ");
        circ.setY(0.0);
        circ.setX(0.0);
        ((Group)scene.getRoot()).getChildren().add(circ);

        Rectangle rect = new Rectangle(50,50);
        rect.setX((scene.getWidth() - rect.getWidth())/2);
        rect.setY((scene.getHeight() - rect.getHeight())/2);
        rect.getStyleClass().add("my-rect");
        ((Group)scene.getRoot()).getChildren().add(rect);

        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed((event) -> {
            String code = event.getCode().toString();

            if(!input.contains(code))
                input.add(code);
        });

        scene.setOnKeyReleased((event) -> {
            String code = event.getCode().toString();
            input.remove(code);
        });

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                circ.setX(x);
                circ.setY(y);

                if(input.contains("LEFT")) {
                    rect.setX(rect.getX() - 1);
                }
                if(input.contains(("RIGHT"))) {
                    rect.setX(rect.getX() + 1);
                }
                if(input.contains(("UP"))) {
                    rect.setY(rect.getY() - 1);
                }
                if(input.contains(("DOWN"))) {
                    rect.setY(rect.getY() + 1);
                }

                if(getBoundary(circ.getX(), circ.getY(), 50, 50).intersects(getBoundary(rect.getX(), rect.getY(), 50, 50))){
                    System.out.println("You hit me!");
                }

            }
        }.start();


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private javafx.geometry.Rectangle2D getBoundary(double posX, double posY, double width, double height) {
        return new javafx.geometry.Rectangle2D(posX, posY, width, height);
    }


}
