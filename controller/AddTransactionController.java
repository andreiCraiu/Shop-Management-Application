package controller;

import domain.Transaction;
import domain.TransactionValidator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.ProductService;
import service.TransactionService;
import domain.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class AddTransactionController {
    public TextField txtTransactionID;
    public TableView productTableView;
    public TextField searchTransaction;
    public Button backProductBtn;

    public TextField txtClientCard;
    public TextField txtProductID;
    public TextField txtQuantity;
    private TransactionService transactionService;
    private ProductService productService;

    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private TransactionValidator transactionValidator = new TransactionValidator();
    private TransactionNotificationFactory transactionNotificationFactory = new TransactionNotificationFactory();
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            transactions.addAll(transactionService.getAll());
            productTableView.setItems(transactions);
        });
    }


    public void setServices(ProductService productService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.productService = productService;

    }

    public void backProductMenu() throws IOException {
        sceneManagementFactory.setScene(Scenes.MainMenu);
    }

    public void clearBoxes(){
        txtClientCard.setText("");
        txtProductID.setText("");
        txtQuantity.setText("");
        txtTransactionID.setText("");
    }
    public void addTransaction() {
        try {
            products.addAll(productService.getAll());

            transactionValidator.validateProductID(products, txtProductID.getText());
            transactionValidator.validateAllEmptyBoxes(txtTransactionID.getText(), txtQuantity.getText(), txtProductID.getText(), txtClientCard.getText());
            transactionValidator.validateEmptyBoxes(txtTransactionID.getText(), txtQuantity.getText(), txtProductID.getText(), txtClientCard.getText());
            transactionValidator.validateParseInput(txtQuantity.getText(), txtClientCard.getText());
            transactionValidator.validateNegativeValues(txtClientCard.getText(), txtQuantity.getText());

            String productId = txtProductID.getText();
            String transactionId = txtTransactionID.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            int clientCard = Integer.parseInt(txtClientCard.getText());
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            Time time = new Time(Calendar.getInstance().getTimeInMillis());

            transactions.clear();
            Transaction transaction = new Transaction(transactionId, productId, quantity, date, time, clientCard);
            transactionValidator.validateAddingTransaction(transactionService.create(transaction));
            transactionNotificationFactory.getSuccessfulAddNotification();
            clearBoxes();
            transactions.addAll(transactionService.getAll());
        }catch (TransactionException tex){
            switch (tex.getError()) {
                case NoAddedTransactionError:
                    transactionNotificationFactory.getNoAddedTransactionNotification();
                    break;
                case AllEmptyBoxesError:
                    transactionNotificationFactory.getAllEmptyFieldsNotification();
                    break;
                case EmptyBoxesError:
                    transactionNotificationFactory.getEmptyFieldsNotification();
                    break;
                case ParseError:
                    transactionNotificationFactory.getParseErrorNotification();
                    break;
                case NoSelectedError:
                    transactionNotificationFactory.getNegativeValuesNotification();
                    break;
                case NoIdFoundError:
                    transactionNotificationFactory.getNoFoundIdNotification();
            }
        }
    }
}
