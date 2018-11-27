package adminMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import objects.Transactions;

import java.io.IOException;
import java.sql.Date;

public class TransactionsOfTheDayController {

    @FXML
    TableView<Transactions> transactionsTable;

    @FXML
    TableColumn acctNo;

    @FXML
    TableColumn amountWithdrawn;

    @FXML
    TableColumn date;

    ObservableList<Transactions> transactionsData = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        acctNo.setCellValueFactory(new PropertyValueFactory<Transactions, Integer>("acctNo"));

        amountWithdrawn.setCellValueFactory(new PropertyValueFactory<Transactions, Integer>("amountWithdrawn"));

        date.setCellValueFactory(new PropertyValueFactory<Transactions, Date>("date"));

        transactionsTable.setItems(transactionsData);
    }

    public void setTransactionsData(ObservableList<Transactions> transactionsData) {
        this.transactionsData = transactionsData;
    }

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
