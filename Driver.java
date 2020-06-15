package flexTransport;

// Constructor Class for DriverSignIn.fxml
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver extends Customers implements Initializable
{
    //private variables
    private String driverID;
    private int experience;
    private String driverType;
    // variables for username and password
    String username;
    String password;

    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // getters and setters
    public String getDriverID()
    {
        return driverID;
    }
    public void setDriverID(String driverID)
    {
        this.driverID = driverID;
    }

    public int getExperience() {
        return experience;
    }



    public void setExperience(int experience) {
        this.experience = experience;
    }

    //implemented methods of the abstract class and interface
    @Override
    void incrementCustomers()
    {
        int drivers = getDrivers();
        drivers++;
        setDrivers(drivers);
    }

    @Override
    void decrementCustomers()
    {
        int drivers = getDrivers();
        drivers++;
        setDrivers(drivers);
    }

    @Override
    public Double payments(String s)
    {
        switch (driverType) {
            case "Van" -> this.setSalary(10000);
            case "Bus" -> this.setSalary(15000);
            case "Shuttle" -> this.setSalary(8000);
        }
        return this.getSalary();

    }

    @Override
    public void userTitle()
    {
        String title = this.getClass().getName();
        setTitle(title);
    }


    // Overridden Method to LogIn
    @Override
    boolean login(String username, String password) {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;
        String sql = "SELECT * FROM driver where userName = ? and password = ?";

        try
        {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String DbUsername = resultSet.getString("username");
            String DbPassword = resultSet.getString("password");

            if(!((DbUsername.equals(username)) && (DbPassword.equals(password))))
            {
                result = false;
            }
            else
            {
                result =  true;
            }

        }catch(Exception e)
        {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE,null,e);
        }
        return result;
    }

    // Overridden Method to SignOut
    @Override
    void signOut(ActionEvent event) throws IOException
    {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();
    }


    //  fxml ID's
    @FXML
    private TextField txtDriverID;  // For Driver ID
    @FXML
    private TextField txtFirstName; // For First Name
    @FXML
    private TextField txtLastName;  // For LastName
    @FXML
    private TextField txtPhoneNumber;   // For Phone Number
    @FXML
    private TextField txtEmailAddress;  // For EmailAddress
    @FXML
    private TextField txtExperience;    // For Experience
    @FXML
    private TextField txtAddress;   // For Address
    @FXML
    private Label lblDriverSalary;  // For Salary
    @FXML
    private TextField txtGender;    // For Gender
    @FXML
    private TextField txtArea;  // For Area
    @FXML
    private TextField txtVehicle;   // For Vehicle Type


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        try {
            setUsernamePassword();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // Method to Search Driver Record in Database
    public ResultSet searchRecord() throws SQLException {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String sql = "SELECT * FROM driver where userName = ? and password = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {

        }

        System.out.println("connection made");
        return resultSet;
    }

    // Method to Set Values in the text field
    public void setValues() throws SQLException {
        try{
            resultSet = searchRecord();
            while(resultSet.next()) {

                driverID = resultSet.getString("DriverID");
                txtDriverID.setText(driverID);

                this.setFirstName(resultSet.getString("firstName"));
                txtFirstName.setText(this.getFirstName());

                this.setLastName(resultSet.getString("lastName"));
                txtLastName.setText(this.getLastName());

                this.setPhoneNumber(resultSet.getString("phoneNumber"));
                txtPhoneNumber.setText(this.getPhoneNumber());

                this.setEmailAddress(resultSet.getString("emailAddress"));
                txtEmailAddress.setText(this.getEmailAddress());

                experience = Integer.parseInt(resultSet.getString("experience"));
                txtExperience.setText(String.valueOf(experience));

                this.setAddress(resultSet.getString("address"));
                txtAddress.setText(this.getAddress());

                this.setArea(resultSet.getString("area"));
                txtArea.setText(this.getArea());

                this.setGender(resultSet.getString("gender"));
                txtGender.setText(this.getGender());

                driverType = searchDriverType(driverID);
                txtVehicle.setText(driverType);

                lblDriverSalary.setText(String.valueOf(payments(driverType)));

            }
        }catch(Exception e)
        {

        }




    }

    // Set Username and Password
    public void setUsernamePassword() throws SQLException {
        this.username = SignIn.username;
        this.password = SignIn.password;
        try {
            setValues();
        } catch(Exception e)
        {

        }

    }

    // Search the vehicle Type for Driver
    public String searchDriverType(String driverId)
    {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String sql = "SELECT vehicle FROM vehicles where DriverID = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                driverType = resultSet.getString("vehicle");
            }
        }catch(Exception e)
        {

        }

        return driverType;
    }

}
