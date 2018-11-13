package changePin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import people.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ChangePinController {

    @FXML
    TextField currentPassword;

    @FXML
    TextField newPassword;

    @FXML
    TextField verNewPassword;

    User user = new User();

    public void changePasswordButton(ActionEvent event){
        if (verifyInfo(Integer.parseInt(currentPassword.getText()), Integer.parseInt(newPassword.getText()), Integer.parseInt(verNewPassword.getText()))){
            changePassword(Integer.parseInt(newPassword.getText()));
        }

    }

    public void changePassword(Integer newPassword){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET pasword=" + newPassword +
                    " WHERE id=" + user.getPersonId() + ";");
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Boolean verifyInfo(Integer currentPassword, Integer newPassword, Integer verNewPassword){
        if (currentPassword.equals(user.getAccountPassword()) && newPassword.equals(verNewPassword)){
            return true;
        }
        return false;
    }

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

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

}
