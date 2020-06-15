package flexTransport;
//controller class for the AdminSignIn.fxml

// All the imports needed in this class
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
// class using multiple inheritance
public class Administrator extends Users implements FareCalculation, Initializable {
    // using encapsulation
    // Array list for location
    private ArrayList<String> location = new ArrayList<String>();

    // Variables for customers
    private int drivers;
    private int students;
    private int faculty;

    // variables for vehicles
    private int van;
    private int bus;
    private int shuttle;

    // static sql variable so that can be accessed by the other classes
    public static String sql;


    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // Method to get the total number of drivers
    public int getDrivers() throws Exception{
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from driver";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            drivers = 0;
            while (resultSet.next())
            {
                drivers = resultSet.getInt(1);
            }
        }
        catch(Exception e){}
        return drivers;
    }

    // Method to get the total number of students

    public int getStudents() {
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from student";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            students = 0;
            while (resultSet.next())
            {
                students = resultSet.getInt(1);
            }
        }
        catch(Exception e){}

        return students;
    }

    // Method to get the total number of faculty members
    public int getFaculty() {
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from facultyMember";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            faculty = 0;
            while (resultSet.next())
            {
                faculty = resultSet.getInt(1);
            }
        }
        catch(Exception e){}

        return faculty;
    }

    // Method to get the total number of customers
    public int getCustomers() throws Exception
    {
        Administrator administrator = new Administrator();
        int customers = administrator.getStudents() + administrator.getFaculty() + administrator.getDrivers();
        return customers;
    }

    // Method to get the total number of vans
    public int getVans() {
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from vehicles where vehicle = 'Van'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            van = 0;
            while (resultSet.next())
            {
                van = resultSet.getInt(1);
            }
        }
        catch(Exception e){}

        return van;
    }

    // Method to get the total number of buses
    public int getBuses() {
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from vehicles where vehicle = 'Bus'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            bus = 0;
            while (resultSet.next())
            {
                bus = resultSet.getInt(1);
            }
        }
        catch(Exception e){}

        return bus;
    }

    // Method to get the total number of shuttles
    public int getShuttles()
    {
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            String sql = "select count(*) from vehicles where vehicle = 'Shuttle'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            shuttle = 0;
            while (resultSet.next())
            {
                shuttle = resultSet.getInt(1);
            }
        }
        catch(Exception e){}

        return shuttle;
    }

    // Method to get the total number of vehicles
    public int getVehicles() {
        Administrator administrator = new Administrator();
        int vehicles = 0;
        vehicles = administrator.getVans() + administrator.getBuses() + administrator.getShuttles();
        return vehicles;
    }


    // Constructor
    public Administrator()
    {

    }

    // Constructor
    public Administrator(ArrayList<String> location) {
        this.location = location;

    }


    // Overridden method of fareCalculations
    @Override
    public Double payments(String s)
    {

        return 0.0;
    }

    // Overridden method of UserInterface
    @Override
    public void userTitle()
    {
        String title = this.getClass().getName();
        setTitle(title);
    }

    // Overridden method of fareCalculations
    @Override
    public ArrayList<String> location()
    {
        String customerLocation;
        String sql = "select area from fareCalculation";
        try{

            MysqlConnection mysqlConnection = new MysqlConnection();
            connection = mysqlConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                customerLocation = resultSet.getString("area");
                location.add(customerLocation);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return location;
    }


    // Overridden method of Users
    @Override
    boolean login(String username, String password)
    {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;
        String sql = "SELECT * FROM administrator where userName = ? and password = ?";

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


    // fxml id's used in fxml files
    @FXML
    private Button btDashboard; // For opening admin dashboard
    @FXML
    private Button btStudents;  // For opening students' info
    @FXML
    private Button btFaculty;   // For opening Faculty members' info
    @FXML
    private Button btDrivers;   // For opening Drivers' info
    @FXML
    private Button btVehicles;  // For opening vehicles' info
    @FXML
    private Button btAccount;   // For opening Accounts' info
    @FXML
    private Button btSignOut;   // For signing out
    @FXML
    private BorderPane mainPane;    // Border pane where other fxmls' will load


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }

    // Method to open Dashboard ( Loads AdminSignInDashBoard.fxml )
    public void openDashboard(ActionEvent event) throws IOException
    {
        Pane pane = FXMLLoader.load(getClass().getResource("AdminSignInDashBoard.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to open Students' Info ( Loads AdminSignInTables.fxml )
    public void openStudentInfo(ActionEvent event) throws IOException {
        sql = "select * from student";
        Pane pane = FXMLLoader.load(getClass().getResource("AdminSignInTables.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to open Facultys' Info ( Loads AdminSignInTables.fxml )
    public void openFacultyInfo(ActionEvent event) throws IOException {
        sql = "select * from facultyMember";
        Pane pane = FXMLLoader.load(getClass().getResource("AdminSignInTables.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to open Drivers' Info ( Loads AdminSignInTables.fxml )
    public void openDriverInfo(ActionEvent event) throws IOException {
        sql = "select * from driver";
        Pane pane = FXMLLoader.load(getClass().getResource("AdminSignInTables.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to open Vehicles' Info ( Loads AdminSignInTables.fxml )
    public void openVehicleInfo(ActionEvent event) throws IOException {
        sql = "select * from vehicles";
        Pane pane = FXMLLoader.load(getClass().getResource("AdminSignInTables.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to open Accounts' Info ( Loads Accounts.fxml )
    public void openAccountsInfo(ActionEvent event) throws IOException {

        sql ="select * from fareCalculation";
        Pane pane = FXMLLoader.load(getClass().getResource("Accounts.fxml"));
        mainPane.setCenter(pane);
    }

    // Method to Sign Out ( Loads SignIn.fxml )
    @Override
    void signOut(ActionEvent event) throws IOException
    {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();
    }




}
