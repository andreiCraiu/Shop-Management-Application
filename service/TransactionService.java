package service;

import domain.Product;
import domain.Transaction;
import repository.IRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TransactionService {
    IRepository<String ,Product> productRepository;
    IRepository<String,Transaction> transactionRepository;

    public TransactionService(IRepository<String, Product> productRepository, IRepository<String, Transaction> transactionRepository) {
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public Boolean create(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public void delete(String transaction_id){
        transactionRepository.delete(transaction_id);
    }

    public List<Transaction> getAll(){
       return transactionRepository.getAll();
    }

    public void update(Transaction transaction) {
        transactionRepository.update(transaction);
    }

    public List<Transaction> getAllContainsString(String inputString) {
        return StreamSupport.stream(transactionRepository.getAll().spliterator(), false)
                .filter(transaction -> transaction.getId().contains(inputString) ||
                        transaction.getProductId().contains(inputString) ||
                        String.valueOf(transaction.getQuantity()).contains(inputString) ||
                        String.valueOf(transaction.getCardNumber()).contains(inputString) ||
                        String.valueOf(transaction.getDate()).contains(inputString) ||
                        String.valueOf(transaction.getTime()).contains(inputString))
                .collect(Collectors.toList());
    }

}
