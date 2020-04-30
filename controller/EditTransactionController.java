package controller;

import domain.Transaction;
import domain.TransactionException;
import domain.TransactionNotificationFactory;
import domain.TransactionValidator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.TransactionService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class EditTransactionController {
    public TextField txtTransactionId;
    public TextField txtProductID;
    public TextField txtTransactionQuantity;
    public TextField txtTransactionCardNumber;
    private TransactionService transactionService;
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
    private TransactionNotificationFactory transactionNotificationFactory = new TransactionNotificationFactory();
    private TransactionValidator transactionValidator = new TransactionValidator();

    @FXML
    public void initialize() {
            txtProductID.setText(sceneManagementFactory.getSelectedTransaction().getProductId());
            txtTransactionCardNumber.setText(String.valueOf(sceneManagementFactory.getSelectedTransaction().getCardNumber()));
            txtTransactionId.setText(sceneManagementFactory.getSelectedTransaction().getId());
            txtTransactionQuantity.setText(String.valueOf(sceneManagementFactory.getSelectedTransaction().getQuantity()));
    }

    public void setServices(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public void backUpdateTransactionBtn() throws IOException {
        sceneManagementFactory.setScene(Scenes.ShowTransactionMenu);
    }

    public void editTransactionBtn() {
        try {

            transactionValidator.validateAllEmptyBoxes(txtTransactionId.getText(), txtTransactionQuantity.getText(), txtProductID.getText(), txtTransactionCardNumber.getText());
            transactionValidator.validateEmptyBoxes(txtTransactionId.getText(), txtTransactionQuantity.getText(), txtProductID.getText(), txtTransactionCardNumber.getText());
            transactionValidator.validateParseInput(txtTransactionQuantity.getText(), txtTransactionCardNumber.getText());
            String transactionID = txtTransactionId.getText();
            String productID = txtProductID.getText();
            int quantity = Integer.parseInt(txtTransactionQuantity.getText());
            int cardNumber = Integer.parseInt(txtTransactionCardNumber.getText());
            Date date = sceneManagementFactory.getSelectedTransaction().getDate();
            Time time = sceneManagementFactory.getSelectedTransaction().getTime();

            Transaction transaction = new Transaction(transactionID, productID, quantity, date, time, cardNumber);
            transactionService.update(transaction);
            transactionNotificationFactory.getSuccessfulEditedTransaction();
        }catch (TransactionException tex){
            switch (tex.getError()){
                case EmptyBoxesError:
                    transactionNotificationFactory.getEmptyFieldsNotification();
                    break;
                case ParseError:
                    transactionNotificationFactory.getParseErrorNotification();
                    break;
                case AllEmptyBoxesError:
                    transactionNotificationFactory.getAllEmptyFieldsNotification();
                    break;
            }
        }
    }
}
