package com.example.ticktacktoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class HelloController {
    @FXML
    public GridPane gridBoard;
    @FXML
    private String symbol = "X";
    private boolean isEnd = false;
    @FXML
    protected void onButtonClick(ActionEvent event){
        if(event.getSource() instanceof Button){
            Button button = (Button)event.getSource();
            if (!"".equals(button.getText()) || isEnd) return;
            button.setText(symbol);
            counter++;
            checkWinner();
            symbol = "X".equals(symbol) ? "O" : "X";
        }
    }
    @FXML
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private String[] board = new String[9];
    @FXML
    int counter = 0;
    @FXML
    protected void checkWinner(){
        int i=0;
        for(Node node: gridBoard.getChildren()) {
            if(node instanceof Button) {
                Button checkButton = (Button)node;
                board[i] = checkButton.getText();
            }
            i++;
        }

        for (int j = 0; j <= 8; j++) {
            String wins = switch (j) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };

            if ("XXX".equals(wins) || "OOO".equals(wins)) {
                alert.setTitle("Koniec gry");
                alert.setHeaderText("Wygrał: " + symbol);
                alert.showAndWait();
                isEnd = true;
            }

        }
        if (counter == 9){
            if(!isEnd) {
                alert.setTitle("Koniec gry");
                alert.setHeaderText("Remis");
                alert.showAndWait();
                isEnd = true;
            }
        }

    }

}