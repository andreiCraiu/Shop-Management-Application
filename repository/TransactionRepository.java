package repository;
import domain.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository  implements IRepository<String, Transaction> {
    private DatabaseConnection databaseConnection;

    public TransactionRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Boolean save(Transaction transaction) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Transaction(transaction_id, transaction_quantity, transaction_date, transaction_time, product_id, transaction_card) values (?, ?, ?, ?, ?, ?)");

            statement.setString(1, transaction.getId());
            statement.setInt(2, transaction.getQuantity());
            statement.setDate(3, transaction.getDate());
            statement.setTime(4, transaction.getTime());
            statement.setString(5, transaction.getProductId());
            statement.setInt(6, transaction.getCardNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Transaction> getAll() {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        List<Transaction> transactions = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transaction");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String id = result.getString("transaction_id");
                int quantity = result.getInt("transaction_quantity");
                Date date = result.getDate("transaction_date");
                Time time = result.getTime("transaction_time");
                String productId = result.getString("product_id");
                Integer clientCard = result.getInt("transaction_card");
                Transaction transaction = new Transaction(id, productId, quantity, date, time, clientCard);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Boolean delete(String transactionId) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Transaction WHERE transaction_id = ?")) {
            statement.setString(1, transactionId);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(Transaction transaction) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Transaction SET transaction_id = ?, transaction_quantity = ?, transaction_date = ?, transaction_time = ?, product_id = ?, transaction_card = ? WHERE transaction_id =?");

            statement.setString(7, transaction.getId());

            statement.setString(1,transaction.getId());
            statement.setInt(2, transaction.getQuantity());
            statement.setDate(3, transaction.getDate());
            statement.setTime(4, transaction.getTime());
            statement.setString(5, transaction.getProductId());
            statement.setInt(6, transaction.getCardNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
              return false;
        }
         return true;
    }

}
