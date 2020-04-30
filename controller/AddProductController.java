package controller;

import domain.ProductNotificationFactory;
import domain.Product;
import domain.ProductException;
import domain.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.ProductService;

import java.io.IOException;

public class AddProductController {
    public TextField txtId;
    public TextField txtProductName;
    public TextField txtManufacturer;
    public TextField txtPrice;
    public TextField txtQuantity;
    public Button addProductBtn;

    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ProductService productService;
    private ProductValidator productValidator = new ProductValidator();
    private ProductNotificationFactory productNotificationFactory = new ProductNotificationFactory();

    public void backScene() throws IOException {
        SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
        sceneManagementFactory.setScene(Scenes.MainMenu);
    }

    public void setServices(ProductService productService) {
        this.productService = productService;
    }

    public void clearFields() {
        txtId.setText("");
        txtManufacturer.setText("");
        txtPrice.setText("");
        txtProductName.setText("");
        txtQuantity.setText("");

    }

    public void addProduct() {
        try {
            productValidator.validateAllEmptyTextBox(txtQuantity.getText(), txtProductName.getText(), txtPrice.getText(), txtManufacturer.getText(), txtId.getText());
            productValidator.validateTextBox(txtQuantity.getText(), txtProductName.getText(), txtPrice.getText(), txtManufacturer.getText(), txtId.getText());
            productValidator.validateParseTextBox(txtQuantity.getText(),txtPrice.getText());
            productValidator.validateNegativeValues(txtPrice.getText(), txtQuantity.getText());
            String id = txtId.getText();
            String name = txtProductName.getText();
            String manufacturer = txtManufacturer.getText();
            Double price = Double.parseDouble(txtPrice.getText());
            int items = Integer.parseInt(txtQuantity.getText());


            products.clear();
            Product product = new Product(id, name, price, items, manufacturer);
            productValidator.validateAddingProduct(productService.create(product));
            productNotificationFactory.getConfirmNotification();

            clearFields();
        } catch (ProductException pex) {
            switch (pex.getProductError()){
                case ParseError:
                    productNotificationFactory.getParseErrorNotification();
                    break;
                case NoAddedError:
                    productNotificationFactory.getErrorAddedNotification();
                    break;
                case EmptyStringError:
                    productNotificationFactory.getEmptyStringNotification();
                    break;
                case AllEmptyError:
                    productNotificationFactory.getAllEmptyStringNotification();
                    break;
                case NegativeValuesError:
                    productNotificationFactory.getNegativeValuesNotification();
            }

        }
    }
}
