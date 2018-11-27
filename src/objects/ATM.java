package objects;

import people.User;

import java.sql.*;
import java.util.ArrayList;

public class ATM {

    private User user;

    public void withdrawMoney(Integer withdrawalAmmount){
        try{
            Double newAmmount = user.getAccountBalance() - withdrawalAmmount;
            Led led = new Led();
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "0k2hbtvy");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.users " +
                    "SET account_balance=" + newAmmount +
                    " WHERE id=" + user.getPersonId() + ";");
            preparedStatement.executeUpdate();
            user.setAccountBalance(newAmmount);
            led.connect("COM3");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void registerTransaccion(Integer withdrawalAmmount){
        try{
            Integer id = connectToDB() + 1;
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "0k2hbtvy");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.transactions( id, acctno, amountwithdrawn, dateandtime) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user.getAccountNo());
            preparedStatement.setInt(3, withdrawalAmmount);
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Integer connectToDB(){
        Integer ctr = 0;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATM", "postgres", "0k2hbtvy");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.transactions");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ctr++;
            }
            return ctr;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    
    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

}

