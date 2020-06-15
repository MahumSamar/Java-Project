package flexTransport;

// Controller Class for FacultySignIn.fxml

// All the Imports Needed For This Class
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

public class FacultyMember extends Customers implements Initializable
{
    // Private Variables
    private String facultyID;
    private String designation;
    // Variables for Username and Password
    String username;
    String password;

    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    // Getters and setters
    public String getFacultyID()
    {
        return facultyID;
    }
    public void setFacultyID(String facultyID)
    {
        this.facultyID = facultyID;
    }

    public String getDesignation()
    {
        return designation;
    }
    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    // Implementing the Methods extended
    @Override
    void incrementCustomers()
    {
        int faculty = getFaculty();
        faculty++;
        setDrivers(faculty);

    }

    @Override
    void decrementCustomers()
    {
        int faculty = getFaculty();
        faculty--;
        setDrivers(faculty);
    }

    // Method For Login
    @Override
    boolean login(String username, String password) {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;
        String sql = "SELECT * FROM facultyMember where userName = ? and password = ?";

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


    @Override
    public Double payments(String s)
    {

        return 0.0;
    }

    @Override
    public void userTitle()
    {
        String title = this.getClass().getName();
        setTitle(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        // Sets Username and Password and Loads Data
        try {
            setUsernamePassword();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // Method For Signing Out
    @Override
    void signOut(ActionEvent event) throws IOException
    {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();
    }


    // fxml ID's
    @FXML
    private TextField txtFacultyID; //  For Faculty ID
    @FXML
    private TextField txtFirstName; // For Firstname
    @FXML
    private TextField txtLastName;  // For LastName
    @FXML
    private TextField txtPhoneNumber;   // For PhoneNumber
    @FXML
    private TextField txtEmailAddress;  // For EmailAddress
    @FXML
    private TextField txtDepartment;    // For Department
    @FXML
    private TextField txtDesignation;   // For Designation
    @FXML
    private TextField txtAddress;   // For Address
    @FXML
    private TextField txtArea;   // For Area
    @FXML
    private TextField txtGender;   // For Gender
    @FXML
    private Label lblFacultyFee;   // For FacultyFee


    // Method For Searching Faculty Record
    public ResultSet searchRecord() throws SQLException {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String sql = "SELECT * FROM facultyMember where userName = ? and password = ?";
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

    // Method For Setting the Values in the TextField
    public void setValues() throws SQLException {
        try{
            resultSet = searchRecord();
            while(resultSet.next()) {

                facultyID = resultSet.getString("FacultyID");
                txtFacultyID.setText(facultyID);

                this.setFirstName(resultSet.getString("firstName"));
                txtFirstName.setText(this.getFirstName());

                this.setLastName(resultSet.getString("lastName"));
                txtLastName.setText(this.getLastName());

                this.setPhoneNumber(resultSet.getString("phoneNumber"));
                txtPhoneNumber.setText(this.getPhoneNumber());

                this.setEmailAddress(resultSet.getString("emailAddress"));
                txtEmailAddress.setText(this.getEmailAddress());

                this.setDepartment(resultSet.getString("department"));
                txtDepartment.setText(this.getDepartment());

                this.setDuesPerMonth(resultSet.getDouble("fare"));
                lblFacultyFee.setText("FEE : " + String.valueOf(this.getDuesPerMonth()));

                designation = resultSet.getString("designation");
                txtDesignation.setText(String.valueOf(designation));

                this.setAddress(resultSet.getString("address"));
                txtAddress.setText(this.getAddress());

                this.setArea(resultSet.getString("area"));
                txtArea.setText(this.getArea());

                this.setGender(resultSet.getString("gender"));
                txtGender.setText(this.getGender());


            }
        }catch(Exception e)
        {

        }




    }

    // Method For Setting UserName and PassWord
    public void setUsernamePassword() throws SQLException {
        this.username = SignIn.username;
        this.password = SignIn.password;
        try {
            setValues();
        } catch(Exception e)
        {

        }

    }


}

