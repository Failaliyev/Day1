package tictacto;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StageGame {

    private boolean round;
    private final Button[] buttons;
    private int counter;
    private final Stage stage;

    public StageGame(Stage stage) {
        this.stage = stage;
        this.counter = 0;
        this.round = false;
        this.buttons = new Button[9];

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setHgap(8);
        pane.setVgap(8);

        for (int i = 0; i < 9; i++) {
            Button b = new Button();
            b.setPrefWidth(90);
            b.setPrefHeight(90);
            final int idx = i;
            b.setOnAction(e -> handleMove(idx));
            buttons[i] = b;
        }

        int c = 0;
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                pane.add(buttons[c], spalte, zeile);
                c++;
            }
        }

        Scene scene = new Scene(pane, 300, 300);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(int i) {
        Button b = buttons[i];
        if (!b.getText().isEmpty()) return; // Feld bereits belegt

        String mark = round ? "O" : "X";
        b.setText(mark);
        counter++;

        if (checkWin(mark)) {
            showEnd(mark + " hat gewonnen!");
            disableBoard(true);
            return;
        }
        if (counter == 9) {
            showEnd("Unentschieden!");
            return;
        }
        round = !round;
    }

    private boolean checkWin(String m) {
        int[][] linien = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };
        for (int[] L : linien) {
            if (m.equals(buttons[L[0]].getText()) &&
                    m.equals(buttons[L[1]].getText()) &&
                    m.equals(buttons[L[2]].getText())) {
                return true;
            }
        }
        return false;
    }

    private void showEnd(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Spiel beendet");
        alert.setContentText(msg + "\nDrücken Sie OK, um eine neue Runde zu starten.");
        alert.showAndWait();
        resetBoard();
    }

    private void disableBoard(boolean disable) {
        for (Button b : buttons) b.setDisable(disable);
    }

    private void resetBoard() {
        for (Button b : buttons) {
            b.setText("");
            b.setDisable(false);
        }
        counter = 0;
        round = false;
    }
}
