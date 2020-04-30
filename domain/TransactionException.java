package domain;

public class TransactionException extends RuntimeException {
    private TransactionError error;

    public TransactionException(TransactionError error){
        this.error = error;
    }

    public TransactionError getError() {
        return error;
    }
}
