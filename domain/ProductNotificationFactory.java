package domain;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class ProductNotificationFactory {

    public void getConfirmNotification() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Your product has been added!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();

    }

    public void getErrorAddedNotification(){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Filed adding product! ID already exists")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getEmptyStringNotification() {
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Empty text box!")
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.CENTER);
            notifications.showError();
    }

    public void getParseErrorNotification(){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Parse Error!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getAllEmptyStringNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("All boxes are empty!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getNoDeletedNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("No Deleted Product!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getNoSelectedItemNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("No selected Product!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getSuccessfulDeleteNotification() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Product successful delete!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

    public void getSuccessfulUpdateProduct() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Product successful edit!")
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }
    public void getNoSelectedValuesErrorNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("No selected values!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }

    public void getSuccessfulOperationApplied() {
        Notifications notifications = Notifications.create()
                .title("Confirmation")
                .text("Operation successful applied!" )
                .graphic(new ImageView(new Image("resurces/icons/icons8-checkmark-48.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

    public void getNegativeValuesNotification() {
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Negative values detected!")
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.showError();
    }
}
