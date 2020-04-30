package controller;

import domain.Product;
import domain.ProductException;
import domain.ProductNotificationFactory;
import domain.ProductValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.ProductService;

import java.io.IOException;

public class EditProductController {
    public TextField txtProductID;
    public TextField txtProductName;
    public TextField txtProductPrice;
    public TextField txtProductManufacturer;
    public TextField txtQuantity;

    private ProductService productService;
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
    private ProductNotificationFactory productNotificationFactory = new ProductNotificationFactory();
    private ProductValidator productValidator = new ProductValidator();

    public void setServices(ProductService productService) {
        this.productService = productService;
    }


    @FXML
    public void initialize(){
        txtProductID.setText(sceneManagementFactory.getSelectedProduct().getId());
        txtQuantity.setText(String.valueOf(sceneManagementFactory.getSelectedProduct().getNumberOfItems()));
        txtProductPrice.setText(String.valueOf(sceneManagementFactory.getSelectedProduct().getPrice()));
        txtProductManufacturer.setText(sceneManagementFactory.getSelectedProduct().getManufacturer());
        txtProductName.setText(sceneManagementFactory.getSelectedProduct().getName());
    }


    public void backEdit(ActionEvent actionEvent) throws IOException {
        sceneManagementFactory.setScene(Scenes.MainMenu);
    }


    public void editProduct() {
        try {
            productValidator.validateAllEmptyTextBox(txtQuantity.getText(), txtProductName.getText(), txtProductPrice.getText(),txtProductManufacturer.getText(), txtProductID.getText());
            productValidator.validateTextBox(txtQuantity.getText(), txtProductName.getText(), txtProductPrice.getText(),txtProductManufacturer.getText(), txtProductID.getText());
            productValidator.validateParseTextBox(txtQuantity.getText(), txtProductPrice.getText());

            String id = txtProductID.getText();
            String name = txtProductName.getText();
            String manufacturer = txtProductManufacturer.getText();
            Double price = Double.parseDouble(txtProductPrice.getText());
            int items = Integer.parseInt(txtQuantity.getText());

            Product product = new Product(id, name, price, items, manufacturer);
            productValidator.validateAddingProduct(productService.update(product));
            productNotificationFactory.getSuccessfulUpdateProduct();
        }catch (ProductException pex){
            switch (pex.getProductError()){
                case AllEmptyError:
                    productNotificationFactory.getAllEmptyStringNotification();
                    break;
                case EmptyStringError:
                    productNotificationFactory.getEmptyStringNotification();
                    break;
                case ParseError:
                    productNotificationFactory.getParseErrorNotification();
                    break;
                case NoAddedError:
                    productNotificationFactory.getErrorAddedNotification();
                    break;
            }
        }
    }
}
