package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jxl.write.WriteException;
import model.Student;
import model.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SampleControl {

    public static String id;

    public TextField nameField;
    public TextField studentidField;
    public TextField sexField;
    public TextField gradeField;
    public TextField collegeField;
    public TextField birthdayField;
    public TextField nativeField;
    public TextField query_id;
    public Label idlabel;
    public Label namelabel;
    public Label sexlabel;
    public Label gradelabel;
    public Label collegelabel;
    public Label birthdaylabel;
    public Label nativelabel;
    public Label awardlabel;
    public Label punishlabel;
    public ChoiceBox update_choice;
    public TextField updateField;
    public final Map<String, String> map = new HashMap<>();
    public Label changestatus;
    public Label changetime;
    public TextField deleteid;
    public TextField mima;
    public TextField zhanghao;
    public AnchorPane abcd;
    public TextField yuan;
    public TextField xin;

    @FXML
    void submit() {
        String id = studentidField.getText();
        String name = nameField.getText();
        String sex = sexField.getText();
        String grade = gradeField.getText();
        String college = collegeField.getText();
        String birthday = birthdayField.getText();
        String native_place = nativeField.getText();
        Student.add(id,name,sex,grade,college,birthday,native_place);
    }

    @FXML
    void query(){
        id = query_id.getText();
        Student get_query = Student.query(id);
        assert get_query != null;
        idlabel.setText(get_query.id);
        namelabel.setText(get_query.name);
        sexlabel.setText(get_query.sex);
        gradelabel.setText(get_query.grade);
        collegelabel.setText(get_query.college);
        birthdaylabel.setText(get_query.birthday);
        nativelabel.setText(get_query.native_place);
        awardlabel.setText(get_query.awardlabel);
        punishlabel.setText(get_query.punishlabel);
        changestatus.setText(get_query.changestatus);
        changetime.setText(get_query.changetime);
    }

    public void update() {
        map.put("姓名","name");
        map.put("性别","sex");
        map.put("班级","class");
        map.put("院系","department");
        map.put("生日","birthday");
        map.put("籍贯","native_place");
        String item = map.get(update_choice.getValue().toString());
        String content = updateField.getText();
        Student.update(item,content,id);
    }

    public void delete() {
        String delete = deleteid.getText();
        Student.delete(delete);
    }

    public void newid() {
        String password = mima.getText();
        String hao = zhanghao.getText();
        Student.register(password,hao);
    }

    public void newpassword() throws IOException {
        Stage stage = (Stage) abcd.getScene().getWindow();
        stage.hide();

        Stage primaryStage=new Stage();
//        System.out.print("点击");
        Parent root = FXMLLoader.load(getClass().getResource("../view/new_password.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 关闭监听
        primaryStage.setOnCloseRequest(event -> stage.show());
    }

    public void updatepassword() {
        Student.updatehao(Logincontroller.hao,yuan.getText(),xin.getText());
    }

    public void daochu() throws IOException, WriteException {
        dao.dao();
    }
}
