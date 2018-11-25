package adminMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {

    public void exit(ActionEvent event){
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
}
