package userMenu;

import accountBalanceMenu.AccountBalanceMenuController;
import changePin.ChangePinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import javafx.scene.control.Label;
import wireTransfer.WireTransferController;
import withdrawalMenu.WithdrawalMenuController;

import java.io.IOException;

public class UserMenuController {

    @FXML
    Label welcomeId;

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

    public void accountBalanceMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../accountBalanceMenu/AccountBalanceMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Account Balance Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            AccountBalanceMenuController accountBalanceMenuController= fxmlLoader.getController();
            accountBalanceMenuController.setAcctBalance("Q. " + atm.getUser().getAccountBalance());
            accountBalanceMenuController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void wireTransferMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../wireTransfer/transfersMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Wire Transfer Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            WireTransferController wireTransferController = fxmlLoader.getController();
            wireTransferController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void changePinMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../changePin/changePin.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Change Pin Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            ChangePinController changePinController = fxmlLoader.getController();
            changePinController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void MainMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../sample/sample.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(root1));

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setWelcomeId(String welcomeId){
        this.welcomeId.setText(welcomeId);
    }

    public void setAtm(ATM atm){
        this.atm = atm;
    }

}
