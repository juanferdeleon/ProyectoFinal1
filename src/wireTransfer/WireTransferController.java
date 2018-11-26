package wireTransfer;

import alertBoxes.AcctNoDosntMatchController;
import alertBoxes.UnableWithdrawManeyTransController;
import alertBoxes.UnableWithdrawalMoney;
import alertBoxes.UserNotFoundTransController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.ATM;
import people.User;
import printReceipt.PrintReceiptController;
import userMenu.UserMenuController;

import java.io.IOException;
import java.sql.*;

public class WireTransferController {

    ATM atm = new ATM();

    @FXML
    TextField acctNoId;

    @FXML
    TextField verAcctNoId;

    @FXML
    TextField quant;

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

    public void makeTransaction(ActionEvent event){
        if (verifyAccount(acctNoId.getText(), verAcctNoId.getText())) {
            User transUser = checkIfUser(connectToDB(), Integer.parseInt(acctNoId.getText()));
            if (transUser != null) {
                if (atm.getUser().getAccountBalance() - Double.parseDouble(quant.getText()) >= 0) {
                    withdrawMoney(Double.parseDouble(quant.getText()), atm.getUser());
                    depositMoney(Double.parseDouble(quant.getText()), transUser);
                    withdrawSuccessful(event);
                } else {
                    //No tiene suficiente para transferir
                    alertBox(event);
                }
            } else {
                //Usuario no existe
                alertBox3(event);
            }
        } else {
            //No coinciden los No. de cuenta
            alertBox2(event);
        }
    }

    public Boolean verifyAccount(String acct1, String acct2){
        if (acct1.equals(acct2)){
            return true;
        }
        return false;
    }

    public void withdrawMoney(Double withdrawalAmmount, User user){
        try{
            Double newAmmount = user.getAccountBalance() - withdrawalAmmount;
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET account_balance=" + newAmmount +
                    " WHERE id=" + user.getPersonId() + ";");
            preparedStatement.executeUpdate();
            user.setAccountBalance(newAmmount);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void depositMoney(Double depositAmount, User user){
        try{
            Double newAmmount = user.getAccountBalance() + depositAmount;
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET account_balance=" + newAmmount +
                    " WHERE id=" + user.getPersonId() + ";");
            preparedStatement.executeUpdate();
            user.setAccountBalance(newAmmount);
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
            printReceiptController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void alertBox(ActionEvent event) {
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node) event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/unableWithdrawalManeyTransController.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Unable Withdrawal Money");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            UnableWithdrawManeyTransController unableWithdrawManeyTransController = fxmlLoader.getController();
            unableWithdrawManeyTransController.setAtm(atm);


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alertBox2(ActionEvent event) {
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node) event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/acctNoDosntMatch.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Account No. doesn't match");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            AcctNoDosntMatchController acctNoDosntMatchController= fxmlLoader.getController();
            acctNoDosntMatchController.setAtm(atm);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alertBox3(ActionEvent event) {
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node) event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/UserNotFoundTrans.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User Not Found");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            UserNotFoundTransController userNotFoundTransController = fxmlLoader.getController();
            userNotFoundTransController.setAtm(atm);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResultSet connectToDB(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public User checkIfUser(ResultSet resultSet, Integer account_no){
        try{
            while (resultSet.next()){
                if (resultSet.getInt(5) == account_no){
                    User user = new User();
                    user.setPersonId(resultSet.getInt(1));
                    user.setAccountNo(resultSet.getInt(5));
                    user.setAccountPassword(resultSet.getInt(2));
                    user.setPersonFirstName(resultSet.getString(3));
                    user.setPersonLastName(resultSet.getString(4));
                    user.setAccountBalance(resultSet.getDouble(6));
                    return user;
                }
            }
        }catch (SQLException sqle){
            System.out.println(sqle);
        }
        return null;
    }

    public void setAtm(ATM atm){
        this.atm = atm;
    }
}
