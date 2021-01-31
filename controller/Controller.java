package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private VBox rootLayout;

    @FXML
    void addmsg() throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.hide();

        Stage primaryStage=new Stage();
//        System.out.print("点击");
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 关闭监听
        primaryStage.setOnCloseRequest(event -> stage.show());
    }

    @FXML
    void query() throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/query.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> stage.show());
    }
    @FXML
    void reward() throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/reward.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> stage.show());
    }
    @FXML
    void punish() throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/punish.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> stage.show());
    }
    @FXML
    public void status() throws IOException {
        Stage stage = (Stage) rootLayout.getScene().getWindow();
        stage.hide();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/status_changes.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("学生信息管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> stage.show());
    }
}
