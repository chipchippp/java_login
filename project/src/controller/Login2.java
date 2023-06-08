package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import database.Connector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import utils.ConnectionUtil;


public class Login2 implements Initializable {

    public TextField txtUser;
    public PasswordField txtPass;

    public Label lblError;
    public Button btnSignin;

    /// --
    PreparedStatement pst = null;
    ResultSet rs = null;


    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/home.fxml"));
                    Parent root = fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("POS");
                    stage.setScene(new Scene(root, 1315, 810));
                    Home home = fxmlLoader.getController();

                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    private String logIn() {
        String status = "Success";
        String email = txtUser.getText();
        String password = txtPass.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM users Where username=? and password=?";
            try {
                Connection conn = new Connector().getConn();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Logged In Successfully...");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = "Exception";
            }
        }

        return status;
    }
    private void setLblError(Color color, String text) {
        lblError.setTextFill(color);
        lblError.setText(text);
        System.out.println(text);
    }
}