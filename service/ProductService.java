package service;

import domain.Product;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProductService {
    private IRepository<String, Product> productIRepository;

    public ProductService(IRepository<String, Product> productRepository) {
        this.productIRepository = productRepository;
    }

    public List<Product> getAll() {
        return productIRepository.getAll();
    }

    public Boolean create(Product product) {
        return productIRepository.save(product);
    }

    public Boolean delete(String transactionId) {
        return productIRepository.delete(transactionId);
    }

    public Boolean update(Product product) {
        return productIRepository.update(product);
    }

    public List<Product> getAllContainsString(String inputString) { // stream
        return StreamSupport.stream(getAll().spliterator(), false)
                .filter(product -> product.getName().contains(inputString) ||
                        product.getManufacturer().contains(inputString) ||
                        String.valueOf(product.getNumberOfItems()).contains(inputString) ||
                        String.valueOf(product.getPrice()).contains(inputString))
                .collect(Collectors.toList());
    }



    public void applyPercentOperation(Object typeOfOperation, Double operationPercentValue, Double operationValue) {
        if (typeOfOperation.equals("Increase")) {
            for (Product product : getAll()) {
                if (product.getPrice() < operationValue) {
                    product.setPrice(product.getPrice() + (operationPercentValue / 100) * product.getPrice());
                    update(product);
                }
            }
        } else {
            for (Product product : getAll()) {
                if (product.getPrice() < operationValue) {
                    product.setPrice(product.getPrice() - (operationPercentValue / 100) * product.getPrice());
                    update(product);
                }
            }
        }
    }
}
