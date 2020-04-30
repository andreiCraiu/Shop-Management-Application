package domain;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TransactionValidator {
    public void validateAddingTransaction(Boolean isAdded) {
        if(!isAdded)
            throw new TransactionException(TransactionError.NoAddedTransactionError);
    }

    public void validateAllEmptyBoxes(String txtTransactionID, String txtQuantity, String txtProductID, String txtClientCard) {
        if(txtClientCard.equals("") && txtProductID.equals("") && txtQuantity.equals("") && txtTransactionID.equals(""))
            throw new TransactionException(TransactionError.AllEmptyBoxesError);
    }

    public void validateEmptyBoxes(String txtTransactionID, String txtQuantity, String txtProductID, String txtClientCard) {
        if(txtClientCard.equals("") || txtProductID.equals("") || txtQuantity.equals("") || txtTransactionID.equals(""))
            throw new TransactionException(TransactionError.EmptyBoxesError);
    }

    public void validateParseInput( String txtQuantity, String txtClientCard) {
     try{
         Integer.parseInt(txtQuantity);
         Integer.parseInt(txtClientCard);
     }catch (RuntimeException rex){
         throw new TransactionException(TransactionError.ParseError);
     }
    }

    public void validateSelectedTransaction(TableView transactionsTableView, ObservableList<Transaction> transactions) {
        try {
            int index = transactionsTableView.getSelectionModel().getSelectedIndex();
            Transaction transaction = transactions.get(index);
        }catch (IndexOutOfBoundsException ex){
            throw new TransactionException(TransactionError.NoSelectedError);
        }
    }

    public void validateNegativeValues(String clientCard, String quantity) {
        if(Integer.parseInt(clientCard) < 0 || Integer.parseInt(quantity) < 0)
            throw  new TransactionException(TransactionError.NegativeValuesError);
    }

    public void validateProductID(ObservableList<Product> productTableView, String productId) {
        boolean contains = false;
        for (Product product : productTableView) {
            if(product.getId().equals(productId)){
                contains = true;
            }
        }
        if(contains == false ){
            throw new TransactionException(TransactionError.NoIdFoundError);
        }
    }
}
