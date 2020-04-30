package repository;

import domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IRepository<String, Product> {
    private DatabaseConnection databaseConnection;

    public ProductRepository(DatabaseConnection dbConnect) {
        this.databaseConnection = dbConnect;

    }

    @Override
    public Boolean save(Product product) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into products(product_id, product_name, product_price, product_items, product_manufacturer) values (?, ?, ?, ?, ?)");

            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getNumberOfItems());
            statement.setString(5, product.getManufacturer());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getAll() {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String id = result.getString("product_id");
                String name = result.getString("product_name");
                Double price = result.getDouble("product_price");
                Integer items = result.getInt("product_items");
                String manufacturer = result.getString("product_manufacturer");

                Product product = new Product(id, name, price, items, manufacturer);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Boolean delete(String productId) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Products WHERE product_id = ?")) {
            statement.setString(1, productId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(Product product) {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE products SET product_id = ?, product_name = ?, product_price = ?, product_items = ?, product_manufacturer = ? WHERE product_id =?");

            statement.setString(6, product.getId());
            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getNumberOfItems());
            statement.setString(5, product.getManufacturer());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
