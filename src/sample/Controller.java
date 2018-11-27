package sample;

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
import objects.Money;
import people.User;
import userMenu.UserMenuController;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class Controller {

    ATM atm = new ATM();

    @FXML
    TextField accountNo;

    @FXML
    PasswordField accountPassword;

    public void userMenuWindow(ActionEvent event){
        Parent root1;

        User user = checkIfUser(connectToDB(), Integer.parseInt(accountNo.getText()), Integer.parseInt(accountPassword.getText()));
        ArrayList<Money> amountOfMoney = new ArrayList<>();
        System.out.println(amountOfMoney.size());
        atm.setUser(user);

        if ( atm.getUser()!= null){
            try {
                //Cierra la actual ventana
                ((Node)event.getSource()).getScene().getWindow().hide();

                //Crea la nueva ventana
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
        }else{
            try {
                //Cierra la actual ventana
                ((Node)event.getSource()).getScene().getWindow().hide();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/UserNotFound.fxml"));
                root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("User Not Found");
                stage.setScene(new Scene(root1));

                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void adminLoginMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../adminLogin/AdminLogin.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Admin Login Menu");
            stage.setScene(new Scene(root1));

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ResultSet connectToDB(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "0k2hbtvy");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public User checkIfUser(ResultSet resultSet, Integer account_no, Integer password){
        try{
            while (resultSet.next()){
                if (resultSet.getInt(5) == account_no && resultSet.getInt(2) == password){
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
}
