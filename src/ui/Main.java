package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import application.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws GameState.IllegalMoveException {
        test();
        System.exit(0);
        //launch(args);
    }

    private static void test() throws GameState.IllegalMoveException {
        GameState game = new GameState();

        //Game before Turn
        System.out.println(game);

        // Test turn
        Turn t = new Turn();
        t.addMove(1,5);
        t.addMove(6,5);

        // execute Turn
        game.doTurn(t);

        //Game after Turn
        System.out.println(game);
    }
}
