package withdrawalMenu;

import alertBoxes.UnableWithdrawalMoney;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import printReceipt.PrintReceiptController;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class VariableAmountMenuController {

    @FXML
    TextField quantId;

    ATM atm = new ATM();

    public void withdrawalMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WithdrawalMenu.fxml"));
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

    public void withdrawMoney(ActionEvent event){
        if (atm.getUser().getAccountBalance() - Double.parseDouble(quantId.getText()) >= 0){
            atm.withdrawMoney(Integer.parseInt(quantId.getText()));
            atm.registerTransaccion(Integer.parseInt(quantId.getText()));
            withdrawSuccessful(event);
        }else{
            alertBox(event);
        }
    }

    public void withdrawSuccessful(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../printReceipt/ReceiptsMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Withdrawal Money Succesful");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            PrintReceiptController printReceiptController = fxmlLoader.getController();
            printReceiptController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void alertBox(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/unableWithdrawalMoney.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Unable Withdrawal Money");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            UnableWithdrawalMoney unableWithdrawalMoney = fxmlLoader.getController();
            unableWithdrawalMoney.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAtm(ATM atm){this.atm = atm;}

}


