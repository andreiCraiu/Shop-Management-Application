package domain;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class TransactionNotificationFactory {
    public void getNoAddedTransactionNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Filed adding transaction! ID already exists! ")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getAllEmptyFieldsNotification(){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("All boxes are empty!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getEmptyFieldsNotification(){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("There are empty boxes!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getSuccessfulAddNotification() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Your transaction has been added!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

    public void getParseErrorNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Invalid input!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }


    public void getNoSelectedTransactionNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("No Selected Transaction!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getSuccessfulDeletedTransactionNotification() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Transaction successful delete!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

    public void getSuccessfulEditedTransaction() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Transaction successful edit!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

    public void getNegativeValuesNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Negative values detected! ")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getNoFoundIdNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Product ID doesn't exist! ")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }
}
