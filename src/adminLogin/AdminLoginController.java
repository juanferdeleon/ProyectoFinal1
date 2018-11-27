package adminLogin;

import adminMenu.TransactionsOfTheDayController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import objects.Transactions;

import java.io.IOException;
import java.sql.*;

public class AdminLoginController {

    @FXML
    PasswordField adminPassword;

    public void loginMenu(ActionEvent event){
        Parent root1;
        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            //Crea la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../sample/sample.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login Menu");
            stage.setScene(new Scene(root1));

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void transactionsWindow(ActionEvent event){
        Parent root1;
        if (adminPassword.getText().equals("12345")){
            try {
                //Cierra la actual ventana
                ((Node)event.getSource()).getScene().getWindow().hide();

                //Crea la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../adminMenu/transactionsOfTheDay.fxml"));
                root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Login Menu");
                stage.setScene(new Scene(root1));


                ObservableList<Transactions> transactionsData = FXCollections.observableArrayList();
                ResultSet resultSet = connectToDB();


                try{

                    while (resultSet.next()){
                        Transactions transaction = new Transactions();
                        transaction.setAcctNo(resultSet.getInt(2));
                        transaction.setAmountWithdrawn(resultSet.getInt(3));
                        transaction.setDate(resultSet.getDate(4));
                        transactionsData.add(transaction);
                    }

                }catch (SQLException sqle){
                    System.out.println(sqle);
                }

                //Envia la informacion de las transacciones a la nueva ventana
                TransactionsOfTheDayController transactionsOfTheDayController = fxmlLoader.getController();
                transactionsOfTheDayController.setTransactionsData(transactionsData);
                transactionsOfTheDayController.initialize();


                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        } else{
            alertBox(event);
        }
    }

    public void alertBox(ActionEvent event){
        Parent root1;
        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            //Crea la nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../alertBoxes/atmInvalidPassword.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Invalid ATM Password");
            stage.setScene(new Scene(root1));

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ResultSet connectToDB(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.transactions");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
