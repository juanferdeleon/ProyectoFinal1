package alertBoxes;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.ATM;
import wireTransfer.WireTransferController;

import java.io.IOException;

public class AcctNoDosntMatchController {

    ATM atm = new ATM();

    public void wireTransferMenuWindow(ActionEvent event){
        Parent root1;

        try {
            //Cierra la actual ventana
            ((Node)event.getSource()).getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../wireTransfer/transfersMenu.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Wire Transfer Menu");
            stage.setScene(new Scene(root1));

            //Envia la informacion del usuario ingresado a la nueva ventana
            WireTransferController wireTransferController = fxmlLoader.getController();
            wireTransferController.setAtm(atm);

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void setAtm(ATM atm){
        this.atm = atm;
    }


}
