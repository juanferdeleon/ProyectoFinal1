package printReceipt;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import userMenu.UserMenuController;

import java.io.IOException;

public class PrintReceiptController {

    ATM atm = new ATM();

    public void doNotPrintReceipt(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../userMenu/UserMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            UserMenuController userMenuController = fxmlLoader.getController();
            userMenuController.setAtm(atm);
            userMenuController.setWelcomeId("Bienvenido/a " + atm.getUser().getPersonFirstName());

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAtm(ATM atm){this.atm = atm;}

}
