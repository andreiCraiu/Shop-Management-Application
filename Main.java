import domain.Product;
import domain.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.*;
import sceneManagement.SceneManagementFactory;
import sceneManagement.Scenes;
import service.LoginService;
import service.ProductService;
import service.TransactionService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        DatabaseConnection databaseConnection = new DatabaseConnection();

        LoginRepository loginRepository = new LoginRepository(databaseConnection);
        LoginService loginService = new LoginService(loginRepository);

        IRepository<String, Product> productIRepository =  new ProductRepository(databaseConnection);
        IRepository<String, Transaction> transactionIRepository = new TransactionRepository(databaseConnection);
        ProductService productService = new ProductService(productIRepository);

        TransactionService transactionService = new TransactionService(productIRepository, transactionIRepository);

        SceneManagementFactory sceneManagementFactory = SceneManagementFactory.getInstance();
        sceneManagementFactory.setLoginService(databaseConnection, loginService);
        sceneManagementFactory.setMenuServices(transactionService, productService);
        sceneManagementFactory.setPrimaryStage(primaryStage);
        sceneManagementFactory.setScene(Scenes.Login);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
