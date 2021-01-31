package controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Student;

public class StatusController {
    public TextField statusField;
    public ChoiceBox typechoice;
    public TextArea descritionArea;

    public void status() {
        String id = statusField.getText();
        String choice = typechoice.getValue().toString();
        String description = descritionArea.getText();
        Student.status(id,choice,description);
    }
}
