package q1;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.ColumnConstraints;

public class MyProjectController{	
	@FXML
	private GridPane sudokuGrid;
	@FXML
	private Button set;
	private Sudoku sudoku;

	@FXML
    public void initialize(){
        sudoku = new Sudoku();
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                TextField textField = new TextField();
                textField.setPrefWidth(50);
                textField.setPrefHeight(50);
                textField.setStyle(colorForSquare(i, j));
                GridPane.setRowIndex(textField, i);
                GridPane.setColumnIndex(textField, j);
                sudokuGrid.getChildren().add(textField);
                final int row = i;
                final int col = j;
                textField.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        TextField textField = (TextField) event.getSource();
                        String input = textField.getText(); 
                        if (input.length() == 1 && Character.isDigit(input.charAt(0))){
                        	int digit = input.charAt(0) - '0';
                        	boolean match = sudoku.tryAddingNumber(row, col, digit);
                        	if (!match){
                        		JOptionPane.showMessageDialog(null, "Invalid input");
                                textField.setText("");
                        	} else{
                        		victory();  // checks if the table is full
                        	}
                        }
                        else{ 
                       		JOptionPane.showMessageDialog(null, "Invalid input");
                            textField.setText("");
                        }
                    }
                });
            }
        }        
        // create column constraints and set them for the grid
        ColumnConstraints[] columnConstraints = new ColumnConstraints[9];
        for (int i = 0; i < 9; i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 9); // set the width to be 1/9 of the total width
            columnConstraints[i] = colConst;
        }
        sudokuGrid.getColumnConstraints().setAll(columnConstraints);
        clear();
	}

	private String colorForSquare(int i, int j){
		int row = i / 3;
		int col = j / 3;

		return "-fx-border-color: black ; -fx-border-width: 1px ;" + (((row + col) % 2 == 0) ? "-fx-background-color: gray" : "-fx-background-color: white");
	}

	@FXML
	private void set(){
		for (Node node : sudokuGrid.getChildren()){
			TextField textField = (TextField) node;
			if (textField.getText().length() == 1){
				textField.setDisable(true);
				textField.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15)); 
			}
		}

		set.setDisable(true);
	}

	@FXML
	private void clear(){
		for (Node node : sudokuGrid.getChildren()){
			TextField textField = (TextField) node;
			textField.setText("");
			textField.setFont(Font.font("Arial", FontWeight.MEDIUM, 15));
			node.setDisable(false);
		}

		set.setDisable(false);
		sudoku.init();
	}
	
	private void victory(){
        boolean allFieldsComplete = true;
        for (Node node : sudokuGrid.getChildren()){
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                if (textField.getText().isEmpty()){
                    allFieldsComplete = false;
                    break;
                }
            }
        }
        if (allFieldsComplete){
        	JOptionPane.showMessageDialog(null, "Victory !!");
        }
	}
}
