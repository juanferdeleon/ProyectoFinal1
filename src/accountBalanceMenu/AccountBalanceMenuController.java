package accountBalanceMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import people.User;
import userMenu.UserMenuController;

import java.io.IOException;

public class AccountBalanceMenuController {

    @FXML
    Label acctBalance;

    User user = new User();

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
            userMenuController.setUser(user);
            userMenuController.setWelcomeId("Bienvenido/a " + user.getPersonFirstName());

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAcctBalance(String acctBalance){
        this.acctBalance.setText(acctBalance);
    }

    public void setUser(User user){
        this.user = user;
    }
}
