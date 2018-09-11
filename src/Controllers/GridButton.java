package Controllers;


import Scenes.Window;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GridButton extends Button {

    private final Notation notation;

    public GridButton(String text, int column, int row, Notation notation) {
        super(text);
        GridPane.setConstraints(this, column, row);
        GridPane.setHalignment(this, HPos.CENTER);
        this.notation = notation;
        addHandlers();
    }

    public Notation getNotation() {
        return notation;
    }

    private void addHandlers() {
        this.setOnAction(event -> {
            boolean isCorrect = Window.getCurrentNotation().checkNotation(this.notation);
            if(isCorrect) {
                this.setStyle("-fx-background-color : #00ff00");
                Window.addCorrect();
                Window.addPress();
            } else {
                this.setStyle("-fx-background-color : #ff0000");
                this.setDisabled(true);
                Window.addPress();
            }
        });
    }

    public void resetFill() {
        this.setStyle(null);
        this.setDisabled(false);
    }
}
