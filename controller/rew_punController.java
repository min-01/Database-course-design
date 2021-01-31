package controller;

import javafx.scene.control.*;
import model.Student;


public class rew_punController {
    public TextField reward_id;
    public Button confirm;
    public Label reward_name;
    public Label reward;
    public ChoiceBox reward_estate;
    public TextArea descriptionText;
    public TextField punish_id;
    public ChoiceBox punish_estate;
    public Label punish_name;
    public Label punish;
    public TextArea pundescriptionText;

    public void add_reward() {
        String id = reward_id.getText();
        String choice = reward_estate.getValue().toString();
        String description = descriptionText.getText();
        Student getreward = Student.reward(id,choice,description);
        assert getreward != null;
        reward_name.setText(getreward.name);
        reward.setText(getreward.reward_punish);
    }

    public void add_punish() {
        String id = punish_id.getText();
        String choice = punish_estate.getValue().toString();
        String description = pundescriptionText.getText();
        Student getpunish = Student.punish(id,choice,description);
        assert getpunish != null;
        punish_name.setText(getpunish.name);
        punish.setText(getpunish.reward_punish);
    }
}
