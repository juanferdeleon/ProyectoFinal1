package changePin;

import alertBoxes.UnableChangePinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import printReceipt.PrintReceiptController;
import userMenu.UserMenuController;
import withdrawalMenu.WithdrawalMenuController;

import javax.print.attribute.standard.PrinterInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ChangePinController {

    @FXML
    TextField currentPassword;

    @FXML
    PasswordField newPassword;

    @FXML
    PasswordField verNewPassword;

    ATM atm = new ATM();

    public void changePasswordButton(ActionEvent event){
        if (verifyInfo(Integer.parseInt(currentPassword.getText()), Integer.parseInt(newPassword.getText()), Integer.parseInt(verNewPassword.getText()))){
            changePassword(Integer.parseInt(newPassword.getText()));
            printReceipt(event);
        } else {
            alertBox(event);
        }
    }

    public void alertBox(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/unableChangePin.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Unable to Change Pin");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            UnableChangePinController unableChangePinController= fxmlLoader.getController();
            unableChangePinController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printReceipt(ActionEvent event){
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

    public void changePassword(Integer newPassword){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET pasword=" + newPassword +
                    " WHERE id=" + atm.getUser().getPersonId() + ";");
            preparedStatement.executeUpdate();
            atm.getUser().setAccountPassword(newPassword);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Boolean verifyInfo(Integer currentPassword, Integer newPassword, Integer verNewPassword){
        if (currentPassword.equals(atm.getUser().getAccountPassword()) && newPassword.equals(verNewPassword)){
            return true;
        }
        return false;
    }

    public void userMenuWindow(ActionEvent event){
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

    public void setAtm(ATM atm){
        this.atm = atm;
    }

}
