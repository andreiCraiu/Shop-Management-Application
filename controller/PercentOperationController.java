package controller;

import domain.ProductException;
import domain.ProductNotificationFactory;
import domain.ProductValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.ProductService;

import java.io.IOException;

public class PercentOperationController {

    public ComboBox operationComboBox;
    public Spinner<Double> spinnerOperationValue;
    public Spinner<Double> spinnerOperationPercent;

    private ProductService productService;
    private SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
    private ProductNotificationFactory productNotificationFactory = new ProductNotificationFactory();
    private ProductValidator productValidator = new ProductValidator();

    public void setServices(ProductService productService) {
        this.productService = productService;
    }

    public void ApplyPercentOperationBtn() {
        try {
            productValidator.validateSelectProperties(operationComboBox.getValue(), spinnerOperationPercent.getValue(), spinnerOperationValue.getValue());
            productService.applyPercentOperation(operationComboBox.getValue(), spinnerOperationValue.getValue(), spinnerOperationPercent.getValue());
            productNotificationFactory.getSuccessfulOperationApplied();
        }catch (ProductException pex){
            switch (pex.getProductError()){
                case NoSelectedValuesError:
                    productNotificationFactory.getNoSelectedValuesErrorNotification();
                    break;
            }
        }
    }

        public void percentOperationBackBtn (ActionEvent actionEvent) throws IOException {
            sceneManagementFactory.setScene(Scenes.MainMenu);
        }
    }
