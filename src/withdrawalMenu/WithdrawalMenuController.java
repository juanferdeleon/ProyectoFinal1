package withdrawalMenu;

import alertBoxes.UnableWithdrawalMoney;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import people.User;
import printReceipt.PrintReceiptController;
import userMenu.UserMenuController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class WithdrawalMenuController {

    User user = new User();

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
            variableAmountMenuController.setUser(user);

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
            userMenuController.setUser(user);
            userMenuController.setWelcomeId("Bienvenido/a " + user.getPersonFirstName());

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

    public void withdraw100(ActionEvent event){
        if (user.getAccountBalance() - 100 >= 0){
            withdrawMoney(100, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw200(ActionEvent event){
        if (user.getAccountBalance() - 200 >= 0){
            withdrawMoney(200, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw300(ActionEvent event){
        if (user.getAccountBalance() - 300 >= 0){
            withdrawMoney(300, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw400(ActionEvent event){
        if (user.getAccountBalance() - 400 >= 0){
            withdrawMoney(400, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw500(ActionEvent event){
        if (user.getAccountBalance() - 500 >= 0){
            withdrawMoney(500, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw600(ActionEvent event){
        if (user.getAccountBalance() - 600 >= 0){
            withdrawMoney(600, event);
        }else{
            alertBox(event);
        }
    }

    public void withdraw700(ActionEvent event){
        if (user.getAccountBalance() - 700 >= 0){
            withdrawMoney(700, event);
        }else{
            alertBox(event);
        }
    }

    public void withdrawMoney(Integer withdrawalAmmount, ActionEvent event){
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

}
