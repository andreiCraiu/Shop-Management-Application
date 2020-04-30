package service;

import domain.Seller;
import repository.LoginRepository;

import java.sql.SQLException;

public class LoginService {
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Boolean checkLogin(Seller seller) throws SQLException {
       return loginRepository.checkLogin(seller.getUserName(), seller.getPassword());
   }
}
