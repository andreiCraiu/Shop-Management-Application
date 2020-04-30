package repository;

import domain.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    private DatabaseConnection databaseConnection;

    public LoginRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Boolean checkLogin(String user, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection(databaseConnection);
        String sql = "select * from employee";;

        List<Seller> sellers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

            while (result.next()){
                String userName = result.getString("user");
                String userPassword = result.getString("password");
                Seller seller = new Seller(userName, userPassword);
                sellers.add(seller);
            }
        for (Seller seller : sellers) {
            if(seller.getUserName().equals(user) && seller.getPassword().equals(password))
                return true;
        }
        return false;
   }
}
