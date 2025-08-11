package tictacto;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label playerOne = new Label("Player 1");
        Label playerTwo = new Label("Player 2");

        TextField playerOneFieldText = new TextField();
        TextField playerTwoFieldText = new TextField();

        Label needLabelAnName = new Label("");

        Button letsPlayButton = new Button("GO!!");

        pane.add(playerOne, 0 , 0);
        pane.add(playerOneFieldText, 1, 0);
        pane.add(playerTwo, 0 , 1);
        pane.add(playerTwoFieldText, 1, 1);
        pane.add(needLabelAnName, 0, 2);
        pane.add(letsPlayButton,  0, 3);

        letsPlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (playerOneFieldText.getText().isEmpty() || playerTwoFieldText.getText().isEmpty()) {
                    needLabelAnName.setText("You need a name!!");
                    needLabelAnName.setTextFill(Color.RED);
                    return;
                }
                new StageGame(new Stage());
            }
        });

        EventHandler<KeyEvent> clearWarning = e -> needLabelAnName.setText("");
        playerOneFieldText.setOnKeyPressed(clearWarning);
        playerTwoFieldText.setOnKeyPressed(clearWarning);

        Scene scene = new Scene(pane, 320, 330);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
