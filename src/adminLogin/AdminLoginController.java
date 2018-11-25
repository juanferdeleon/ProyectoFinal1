package adminLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void adminMenu(ActionEvent event){
        Parent root1;
        if (adminPassword.getText().equals("12345")){
            try {
                //Cierra la actual ventana
                ((Node)event.getSource()).getScene().getWindow().hide();

                //Crea la nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../adminMenu/adminMenu.fxml"));
                root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Administrator Menu");
                stage.setScene(new Scene(root1));

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

}
