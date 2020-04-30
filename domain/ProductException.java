package domain;

public class ProductException extends RuntimeException{
    private ProductError productError;

    public ProductException(ProductError productError) {
        this.productError = productError;
    }

    public ProductError getProductError() {
        return productError;
    }

}
