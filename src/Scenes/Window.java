package Scenes;

import Controllers.GridButton;
import Controllers.Notation;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Window extends Application {

    public static int width = 620;
    public static int height = 620;

    private static final GridButton[] triadButtons = new GridButton[]{
            new GridButton("Root Triad", 0, 0, Notation.OTF),
            new GridButton("First Inversion Triad", 1, 0, Notation.TFO),
            new GridButton("Second Inversion Triad", 2, 0, Notation.FOT)
    };

    private static final GridButton[] seventhButtons = new GridButton[] {
            new GridButton("Root Seventh", 0, 1, Notation.OTFS),
            new GridButton("First Inversion Seventh", 1, 1, Notation.TFSO),
            new GridButton("Second Inversion Seventh", 2, 1, Notation.FSOT),
            new GridButton("Third Inversion Seventh", 3, 1, Notation.SOTF),
    };

    private static final String[] chordLetters = new String[] {
            "A", "B", "C", "D", "E", "F", "G"
    };
    private static final Text chordLetter = new Text();

    private static Text notationText = new Text();
    private static Notation currentNotation;

    private final Group root = new Group();

    private final GridPane triads = new GridPane();
    private final GridPane sevenths = new GridPane();
    private final GridPane chords = new GridPane();

    private static int numberCorrect = 0;
    private static int numberPresses = -1;
    private static Text numberText = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        chordLetter.setFont(new Font("Consolas", 105));
        chordLetter.setLayoutX(width / 2 - 55);
        chordLetter.setLayoutY(300);
        setChord();
        root.getChildren().add(chordLetter);

        notationText.setFont(new Font("Consolas", 20));
        notationText.setLayoutX(width / 2 + 10);
        notationText.setLayoutY(250);
        root.getChildren().add(notationText);

        numberText.setFont(new Font("Consolas", 15));
        numberText.setLayoutX(520);
        numberText.setLayoutY(300);
        root.getChildren().add(numberText);
        setText();

        addPress();

        Label instructions = new Label("Select the type of chord that matches the notation.");
        instructions.setFont(new Font("Consolas", 20));
//        for(String font : Font.getFamilies()) {
//            System.out.println(font);
//        }
        instructions.setLayoutX(32);
        instructions.setLayoutY(100);
        root.getChildren().add(instructions);


        triads.getChildren().addAll(triadButtons);
        sevenths.getChildren().addAll(seventhButtons);
        triads.setPadding(new Insets(0, 0, 0,88));
        chords.add(triads, 0, 0);
        chords.add(sevenths, 0, 1);
        chords.setGridLinesVisible(true);
        chords.setPadding(new Insets(0,50, 0, 50));
        chords.setLayoutY(500);

        root.getChildren().add(chords);
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Notation getRandomFBNotation(Notation[] options) {
        int amount = options.length;
        int choice = (int) (Math.random() * amount);
        return options[choice];
    }

    public static void setText() {
        notationText.setFill(Color.rgb(0, 0, 0));
        currentNotation = getRandomFBNotation(Notation.values());
        double switchyBoy = Math.random();
        if(switchyBoy > 0.5) {
            notationText.setText(currentNotation.getNotation());
        } else {
            notationText.setText(currentNotation.getShorthand());
        }
    }

    public static Notation getCurrentNotation() {
        return currentNotation;
    }

    public static void addCorrect() {
        notationText.setFill(Color.rgb(0, 255, 0));
        numberCorrect++;
        reset();
    }

    private static void reset() {
        setText();
        resetButtons();
        setChord();
    }

    private static void resetButtons() {
        for (GridButton button : triadButtons) {
            button.resetFill();
        }
        for (GridButton button : seventhButtons) {
            button.resetFill();
        }
    }

    public static void addPress() {
        numberPresses++;
        numberText.setText(numberCorrect + " / " + numberPresses);
    }

    private static void setChord() {
        int index = (int) (Math.random() * chordLetters.length);
        chordLetter.setText(chordLetters[index]);
    }
}