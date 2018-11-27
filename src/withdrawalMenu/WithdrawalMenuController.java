package withdrawalMenu;

import alertBoxes.UnableWithdrawalMoney;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import printReceipt.PrintReceiptController;
import userMenu.UserMenuController;

import java.io.IOException;

public class WithdrawalMenuController {

    ATM atm = new ATM();

    public void variableAmountMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VariableAmountMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Variable Amount Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            VariableAmountMenuController variableAmountMenuController = fxmlLoader.getController();
            variableAmountMenuController.setAtm(atm);


            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void returnWindow(ActionEvent event){
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

    public void withdraw100(ActionEvent event){
        withdrawMoney(100, event);
    }

    public void withdraw200(ActionEvent event){
        withdrawMoney(200, event);
    }

    public void withdraw300(ActionEvent event){
        withdrawMoney(300, event);
    }

    public void withdraw400(ActionEvent event){
        withdrawMoney(400, event);
    }

    public void withdraw500(ActionEvent event){
        withdrawMoney(500, event);
    }

    public void withdraw600(ActionEvent event){
        withdrawMoney(600, event);
    }

    public void withdraw700(ActionEvent event){
        withdrawMoney(700, event);
    }

    public void withdrawMoney(Integer amount, ActionEvent event){
        if (atm.getUser().getAccountBalance() - amount >= 0){
            atm.withdrawMoney(amount);
            atm.registerTransaccion(amount);
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
