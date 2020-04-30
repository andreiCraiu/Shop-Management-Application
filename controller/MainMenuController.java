package controller;

import domain.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.ProductService;
import service.TransactionService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainMenuController {
    public Label time;
    public Label date;
    public TableView tableView;
    public TableColumn update;
    public ImageView addingIcon;
    public TextField txtSearchBar;

    ProductService productService;
    TransactionService transactionService;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
    private ProductValidator productValidator = new ProductValidator();
    private ProductNotificationFactory productNotificationFactory = new ProductNotificationFactory();

    @FXML
    private void initialize() {
        setDataTime();
        addAllProducts();
        txtSearchBar.setOnKeyPressed(event -> {
            List<Product> productList;
            if (txtSearchBar.getText().equals(""))
                productList = productService.getAll();
            else
                productList = productService.getAllContainsString(txtSearchBar.getText());
            products.clear();
            products.addAll(productList);
        });
    }

    public void setServices(ProductService productService, TransactionService transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
    }

    public void addAllProducts() {
        Platform.runLater(() -> {
            products.addAll(productService.getAll());
            tableView.setItems(products);
        });
    }

    public void setDataTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            time.setText(LocalDateTime.now().format(formatter));
            date.setText(LocalDateTime.now().format(formatterDate));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void addNewProductButton() throws IOException {
        System.out.println("Hello to everyone!");
        sceneManagementFactory.setScene(Scenes.AddProductMenu);
    }

    public void goTotTransactionMenu() throws IOException {
        sceneManagementFactory.setScene(Scenes.AddTransactionMenu);
    }

    public void logOut() throws IOException {
        sceneManagementFactory.setScene(Scenes.Login);
    }

    public void deleteProduct() {
        try {
            productValidator.validateSelectProduct(tableView, products);
            int index = tableView.getSelectionModel().getSelectedIndex();
            Product product = products.get(index);
            productValidator.validateDeleteProduct(productService.delete(product.getId()));
            productNotificationFactory.getSuccessfulDeleteNotification();
            products.clear();
            products.addAll(productService.getAll());
        } catch (ProductException pex) {
            switch (pex.getProductError()) {
                case NoAddedError:
                    productNotificationFactory.getNoDeletedNotification();
                    break;
                case NoSelectedError:
                    productNotificationFactory.getNoSelectedItemNotification();
                    break;
            }
        }
    }

    public void editProduct() throws IOException {
        try {
            productValidator.validateSelectProduct(tableView, products);
            sceneManagementFactory.setSelectedProduct(getSelectedProduct());
            sceneManagementFactory.setScene(Scenes.UpdateMenu);
        } catch (ProductException pex) {
            switch (pex.getProductError()) {
                case NoSelectedError:
                    productNotificationFactory.getNoSelectedItemNotification();
                    break;
            }
        }
    }


    public Product getSelectedProduct() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Product product = products.get(index);
        return product;
    }

    public void percentOperationBtn() throws IOException {
        sceneManagementFactory.setScene(Scenes.PercentOperationMenu);
    }

    public void showTransactionBtn() throws IOException {
        sceneManagementFactory.setScene(Scenes.ShowTransactionMenu);
    }
}
