package alertBoxes;

import changePin.ChangePinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import people.User;

import java.io.IOException;

public class UnableChangePinController {

    User user = new User();

    public void changePinMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../changePin/changePin.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Change Pin Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            ChangePinController changePinController = fxmlLoader.getController();
            changePinController.setUser(user);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

}
