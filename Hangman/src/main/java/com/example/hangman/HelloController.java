package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField Eingabe;
    @FXML
    private javafx.scene.text.Text Text;
    @FXML
    private Line bottomLine;
    @FXML
    private Line verticalLine;
    @FXML
    private Line topLine;
    @FXML
    private Circle head;
    @FXML
    private Line body;
    @FXML
    private Line leftArm;
    @FXML
    private Line rightArm;
    @FXML
    private Line leftfoot;
    @FXML
    private Line rightfoot;
    @FXML
    private TextField guessField;
    @FXML
    private TextField wortField;
    @FXML
    private Button bestätigunsButton;
    @FXML
    private Button InputButtonClick;
    @FXML
    private TextArea falscheBuchstaben;
    @FXML
    private Text benachrichtigung;
    private String wort;
    private int fehler = 0;

    private String solutionText;

    @FXML
    protected void hangmanUnvisible() {
        bottomLine.setVisible(false);
        verticalLine.setVisible(false);
        topLine.setVisible(false);
        head.setVisible(false);
        body.setVisible(false);
        leftArm.setVisible(false);
        rightArm.setVisible(false);
        leftfoot.setVisible(false);
        rightfoot.setVisible(false);
    }

    @FXML
    protected void hangmanVisible(int fehler) {
        switch (fehler) {
            case 1:
                bottomLine.setVisible(true);
                break;
            case 2:
                verticalLine.setVisible(true);
                break;
            case 3:
                topLine.setVisible(true);
                break;
            case 4:
                head.setVisible(true);
                break;
            case 5:
                body.setVisible(true);
                break;
            case 6:
                leftArm.setVisible(true);
                break;
            case 7:
                rightArm.setVisible(true);
                break;
            case 8:
                leftfoot.setVisible(true);
                break;

            case 9:
                rightfoot.setVisible(true);
                guessField.setText("Du hast Verloren");
                break;
            default:
                break;
        }

    }

    @FXML
    protected void onHelloButtonClick() {

        hangmanUnvisible();
        wort = Eingabe.getText().toLowerCase();

        if (wort.matches("[a-zäöüß]+")) {
        welcomeText.setText("Das Wort wurde gespeichert!");
        }

        else {
            welcomeText.setText("Das Wort wurde nicht gespeichert! Bitte gib ein Wort ein.");
            Eingabe.setText("");
        }

        for (int i = 0; i < Eingabe.getLength(); i++) {
            wortField.appendText("_");
        }
    }


    @FXML
    protected void InputButtonClick () {

        solutionText = wortField.getText(); // _____
        String guessedWord = guessField.getText().toLowerCase(); // Setzt das Wort in kleinbuchstaben

        if (guessedWord.equalsIgnoreCase(wort)) {
            benachrichtigung.setText("Richtig du hast das Wort: " + wort + " erraten");
            wortField.setText(Eingabe.getText());

        }

        if (guessField.getLength() == 1 && wort.contains(guessedWord)) {
            char guessedChar = guessedWord.charAt(0); // Nimmt den Buchstaben an der Stelle 0, im Feld: guessField und speichert ihn in guessedChar
            StringBuilder updatedSolution = new StringBuilder(solutionText); // übernimmt die unterstriche

            if (guessedWord.matches("[a-zäöüß]+")) {
                for (int i = 0; i < wort.length(); i++) {
                    if (wort.charAt(i) == guessedChar && wort.contains(guessedWord.toLowerCase())) { //Sucht durch das Wort mithilfe von i
                        updatedSolution.setCharAt(i, guessedChar); // Fügt den Buchstaben hinzu an der richtigen Stelle
                        benachrichtigung.setText("Richtig!");
                        guessField.setText("");
                    }
                }
            }

            wortField.setText(updatedSolution.toString()); // updatedSolution wird zu einem String umgewandelt, da setText einen erwartet

            if (!(wortField.getText().contains("_"))) {
                benachrichtigung.setText("Du hast das Wort erraten!");
                Text.setText("");
            }
        }

        if (guessedWord.matches("[*-,:. ; _ ~ -]+")) {
            benachrichtigung.setText("Lies die Aufgabe noch mal durch");
        }

        else if (guessField.getLength() == 1 && !(wort.contains(guessedWord.toLowerCase())) || guessField.getLength() > 1 && !(wort.contains(guessedWord.toLowerCase()))) {
        if (fehler <= 9) {
         benachrichtigung.setText("Falsch!");
         fehler++;
         guessField.setText("");
         hangmanVisible(fehler);
         String aktuellerText = falscheBuchstaben.getText();
         falscheBuchstaben.setText(aktuellerText + ", " + guessedWord);
        }


    }

  }

}





