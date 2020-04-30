package controller;

import domain.Seller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import repository.DatabaseConnection;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.LoginService;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public TextField txtUser;
    public PasswordField txtPassword;
    public Label errorLabel;
    private LoginService loginService;
    private DatabaseConnection databaseConnection;

    public void setServices(DatabaseConnection databaseConnection, LoginService loginService) {
        this.databaseConnection = databaseConnection;
        this.loginService = loginService;
    }

    @FXML
    public void initialize() {
        errorLabel.setVisible(false);
        setServices(databaseConnection, loginService);
    }


    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        String userName = txtUser.getText();
        String password = txtPassword.getText();

        Seller seller = new Seller(userName, password);
        if (loginService.checkLogin(seller)) {
            SceneManagementFactory manager = SceneManagementFactory.getInstance();
            manager.setScene(Scenes.MainMenu);
        }

        errorLabel.setVisible(true);
    }

}
