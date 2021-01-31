package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import java.io.IOException;


public class Logincontroller {

    @FXML
    private AnchorPane abc;

    public static String password;
    public static String hao;
    public TextField mima;
    public TextField zhanghao;

    public void login() throws IOException {
        password = mima.getText();
        hao = zhanghao.getText();
        Boolean b = Student.login(password,hao);
        if(b){
            Stage stage = (Stage) abc.getScene().getWindow();
            stage.hide();

            Stage primaryStage=new Stage();
//            System.out.print("点击");
            Parent root = FXMLLoader.load(getClass().getResource("../view/index.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("学生信息管理系统");
            primaryStage.setScene(scene);
            primaryStage.show();

            // 关闭监听
            primaryStage.setOnCloseRequest(event -> stage.show());
        }
    }

    public void studentlogin() throws IOException {
        password = mima.getText();
        hao = zhanghao.getText();
        Boolean b = Student.studentlogin(password,hao);
        if(b){
            Stage stage = (Stage) abc.getScene().getWindow();
            stage.hide();

            Stage primaryStage=new Stage();
//        System.out.print("点击");
            Parent root = FXMLLoader.load(getClass().getResource("../view/studentquery.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("学生信息管理系统");
            primaryStage.setScene(scene);
            primaryStage.show();

            // 关闭监听
            primaryStage.setOnCloseRequest(event -> stage.show());
        }
    }
}
