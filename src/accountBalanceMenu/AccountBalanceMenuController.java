package accountBalanceMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import objects.ATM;
import objects.Led;
import people.User;
import userMenu.UserMenuController;

import java.io.IOException;

public class AccountBalanceMenuController {

    @FXML
    Label acctBalance;

    ATM atm = new ATM();

    public void userMenuWindow(ActionEvent event) {
        changeWindow(event);
    }

    public void setAcctBalance(String acctBalance){
        this.acctBalance.setText(acctBalance);
    }

    public void printReceipt(ActionEvent event) {
        try {
            Led led = new Led();
            led.connect("COM3");
            changeWindow(event);
        } catch (Exception e) {
        }
    }


    public void setAtm(ATM atm){
        this.atm = atm;
    }

    public void changeWindow(ActionEvent event){
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
}
