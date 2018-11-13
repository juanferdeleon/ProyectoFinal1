package withdrawalMenu;

import alertBoxes.UnableWithdrawalMoney;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    User user = new User();

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
            withdrawalMenuController.setUser(user);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void withdrawMoney(ActionEvent event){

        if (user.getAccountBalance() - Double.parseDouble(quantId.getText()) >= 0){
            withdrawMoney(Double.parseDouble(quantId.getText()), event);
        }else{
            alertBox(event);
        }

    }

    public void withdrawMoney(Double withdrawalAmmount, ActionEvent event){
        try{
            Double newAmmount = user.getAccountBalance() - withdrawalAmmount;
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET account_balance=" + newAmmount +
                    " WHERE id=" + user.getPersonId() + ";");
            preparedStatement.executeUpdate();
            user.setAccountBalance(newAmmount);
            withdrawSuccessful(event);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
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
            printReceiptController.setUser(user);

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
            unableWithdrawalMoney.setUser(user);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

}


