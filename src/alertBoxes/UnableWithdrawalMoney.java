package alertBoxes;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import withdrawalMenu.WithdrawalMenuController;

import java.io.IOException;

public class UnableWithdrawalMoney {

    ATM atm = new ATM();

    public void withdrawalMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../withdrawalMenu/WithdrawalMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Withdrawal Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            WithdrawalMenuController withdrawalMenuController = fxmlLoader.getController();
            withdrawalMenuController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAtm(ATM atm){this.atm = atm;}

}
