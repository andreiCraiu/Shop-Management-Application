package controller;

import domain.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.TransactionService;

import java.io.IOException;
import java.util.List;

public class TransactionMenuController {

    public TableView transactionsTableView;
    public TextField txtSearchTransaction;
    private TransactionService transactionService;
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private TransactionValidator transactionValidator = new TransactionValidator();
    private TransactionNotificationFactory transactionNotificationFactory = new TransactionNotificationFactory();

    public void setServices(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @FXML
    public void initialize() {
        txtSearchTransaction.setOnKeyPressed(event -> {
            List<Transaction> transactionList;
            if (txtSearchTransaction.getText().equals(""))
                transactionList = transactionService.getAll();
            else
                transactionList = transactionService.getAllContainsString(txtSearchTransaction.getText());
            transactions.clear();
            transactions.addAll(transactionList);
        });
        loadTransactions();
    }

    public void loadTransactions() {
        Platform.runLater(() -> {
            transactions.addAll(transactionService.getAll());
            transactionsTableView.setItems(transactions);
        });
    }

    public void deleteTransactionBtn() {
        try {
            transactionValidator.validateSelectedTransaction(transactionsTableView, transactions);
            int index = transactionsTableView.getSelectionModel().getSelectedIndex();
            Transaction transaction = transactions.get(index);
            transactionService.delete(transaction.getId());
            transactions.clear();
            transactions.addAll(transactionService.getAll());
            transactionNotificationFactory.getSuccessfulDeletedTransactionNotification();
        } catch (TransactionException tex) {
            switch (tex.getError()) {
                case NoSelectedError:
                    transactionNotificationFactory.getNoSelectedTransactionNotification();
                    break;
            }
        }
    }

    public void editTransactionBtn(ActionEvent actionEvent) throws IOException {
        try {
            transactionValidator.validateSelectedTransaction(transactionsTableView, transactions);
            sceneManagementFactory.setSelectedTransaction(getSelectedTransaction());
            sceneManagementFactory.setScene(Scenes.EditTransactionMenu);
        } catch (TransactionException tex) {
            switch (tex.getError()) {
                case NoSelectedError:
                    transactionNotificationFactory.getNoSelectedTransactionNotification();
                    break;
            }
        }
    }

    public void backTransactionShowMenu(ActionEvent actionEvent) throws IOException {
        sceneManagementFactory.setScene(Scenes.MainMenu);
    }

    public Transaction getSelectedTransaction() {
        int index = transactionsTableView.getSelectionModel().getSelectedIndex();
        Transaction transaction = transactions.get(index);
        return transaction;
    }
}
