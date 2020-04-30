package domain;

import javafx.scene.control.TableView;

import java.util.List;

public class ProductValidator {
    public void validateAllEmptyTextBox(String textQuantity, String txtProductName, String txtPrice, String txtManufacturer, String txtId) {
        if (textQuantity.equals("") && txtProductName.equals("") && txtPrice.equals("") && txtManufacturer.equals("") && txtId.equals("")) {
            throw new ProductException(ProductError.AllEmptyError);
        }
    }

    public void validateAddingProduct(Boolean isAdded) {
        if (!isAdded)
            throw new ProductException(ProductError.NoAddedError);
    }

    public void validateTextBox(String textQuantity, String txtProductName, String txtPrice, String txtManufacturer, String txtId) {
        if (textQuantity.equals("") || txtProductName.equals("") || txtPrice.equals("") || txtManufacturer.equals("") || txtId.equals("")) {
            throw new ProductException(ProductError.EmptyStringError);
        }
    }

    public void validateParseTextBox(String textQuantity, String txtPrice) {
        try {
            Double.parseDouble(txtPrice);
            Integer.parseInt(textQuantity);
        } catch (RuntimeException rex) {
            throw new ProductException(ProductError.ParseError);
        }
    }

    public void validateDeleteProduct(Boolean isDeleted) {
        if (!isDeleted)
            throw new ProductException(ProductError.DeleteError);
    }

    public void validateSelectProduct(TableView tableView, List<Product> products) {
        try {
            int index = tableView.getSelectionModel().getSelectedIndex();
            Product product = products.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new ProductException(ProductError.NoSelectedError);
        }
    }

    public void validateSelectProperties(Object typeOfOperation, Double operationPercentValue, Double operationValue) {
        if (typeOfOperation == null || operationPercentValue == null || operationPercentValue == null)
            throw new ProductException(ProductError.NoSelectedValuesError);
    }

    public void validateNegativeValues(String price, String quantity) {
        if (Double.parseDouble(price) < 0 || Integer.parseInt(quantity) < 0)
            throw new ProductException(ProductError.NegativeValuesError);
    }
}
