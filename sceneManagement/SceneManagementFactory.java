package sceneManagement;

import controller.*;
import domain.Product;
import domain.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.DatabaseConnection;
import service.LoginService;
import service.ProductService;
import service.TransactionService;

import java.io.IOException;

public class SceneManagementFactory {    // singleton class
    private TransactionService transactionService;
    private ProductService productService;
    private DatabaseConnection databaseConnection;
    private LoginService loginService;
    private static SceneManagementFactory instance = null;
    private Stage stage;
    private Product selectedProduct;
    private Transaction selectedTransaction;

    public static SceneManagementFactory getInstance() {
        if (instance == null)
            instance = new SceneManagementFactory();
        return instance;
    }

    public void setLoginService(DatabaseConnection databaseConnection, LoginService loginService) {
        this.databaseConnection = databaseConnection;
        this.loginService = loginService;
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    public void setMenuServices(TransactionService transactionService, ProductService productService) {
        this.transactionService = transactionService;
        this.productService = productService;
    }

    public void setScene(Scenes scene) throws IOException {
        switch (scene) {
            case Login:
                setLoginScene();
                break;
            case MainMenu:
                setMenuScene();
                break;
            case AddProductMenu:
                setAddProductScene();
                break;
            case AddTransactionMenu:
                setAddTransactionMenu();
                break;
            case UpdateMenu:
                setUpdateScene();
                break;
            case PercentOperationMenu:
                setPercentOperationScene();
                break;
            case ShowTransactionMenu:
                setShowTransactionScene();
                break;
            case EditTransactionMenu:
                setEditTransactionScene();
                break;
        }
    }

    private void setEditTransactionScene() throws IOException {
        stage.setTitle("Update Transaction");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/editTransaction.fxml"));

        AnchorPane pane = fxmlLoader.load();
        EditTransactionController editTransactionController = fxmlLoader.getController();

        editTransactionController.setServices(transactionService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setShowTransactionScene() throws IOException {
        stage.setTitle("My Transactions");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/transactionMenu.fxml"));

        AnchorPane pane = fxmlLoader.load();
        TransactionMenuController transactionMenuController = fxmlLoader.getController();

        transactionMenuController.setServices(transactionService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setPercentOperationScene() throws IOException {
        stage.setTitle("Percent Operation");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/percentOperation.fxml"));

        AnchorPane pane = fxmlLoader.load();
        PercentOperationController percentOperationController = fxmlLoader.getController();

        percentOperationController.setServices(productService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setUpdateScene() throws IOException {
        stage.setTitle("Update Menu");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/editProduct.fxml"));

        AnchorPane pane = fxmlLoader.load();
        EditProductController editProductController = fxmlLoader.getController();

        editProductController.setServices(productService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setAddTransactionMenu() throws IOException {
        stage.setTitle("Add Transaction");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addTransaction.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AddTransactionController addTransactionController = fxmlLoader.getController();
        addTransactionController.setServices(productService, transactionService);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setAddProductScene() throws IOException {
        stage.setTitle("Add Product");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addProduct.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AddProductController controller = fxmlLoader.getController();
        controller.setServices(productService);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    private void setMenuScene() throws IOException {
        stage.setTitle("Menu");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/mainMenu.fxml"));
        AnchorPane pane = fxmlLoader.load();

        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.setServices(productService, transactionService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    public void setLoginScene() throws IOException {
        stage.setTitle("Login");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        AnchorPane pane = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setServices(databaseConnection, loginService);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    public Product getSelectedProduct(){
        return this.selectedProduct;
    }


    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }
}



