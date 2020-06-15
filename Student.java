package flexTransport;

// Controller Class For StudentSignIn.fxml
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class Student extends Customers implements Initializable
{
    // Private Variables
    private String studentID;
    private int studentSemester;
    String username;
    String password;



    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Constructor
    public Student()
    {

    }

    public Student(String studentID, String firstName, String lastName, String phoneNumber, String emailAddress,
                   String department, String semester, String address, String area) {
    this.studentID = studentID;
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setPhoneNumber(phoneNumber);
    this.setEmailAddress(emailAddress);
    this.setDepartment(department);
    this.setStudentSemester(Integer.parseInt(semester));
    this.setAddress(address);
    this.setArea(area);

    }

    // Getters and Setters
    public String getStudentID()
    {
        return studentID;
    }
    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }

    public int getStudentSemester()
    {
        return studentSemester;
    }
    public void setStudentSemester(int studentSemester)
    {
        this.studentSemester = studentSemester;
    }

    //implementing extended metods

    @Override
    void incrementCustomers()
    {
        int students = getStudents();
        students++;
        setStudents(students);

    }

    @Override
    void decrementCustomers()
    {
        int students = getStudents();
        students--;
        setStudents(students);
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


    // method for signing in
    @Override
    boolean login(String username, String password) throws SQLException {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;
        String sql = "SELECT * FROM student where userName = ? and password = ?";

        try
        {
            String DbUsername = "";
            String DbPassword = "";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                DbUsername = resultSet.getString("userName");
                DbPassword = resultSet.getString("password");
            }


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
        resultSet.close();
        return result;
    }


    //method for signing out
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
    private TextField txtStudentID; // For StudentID
    @FXML
    private TextField txtFirstName; // For FirstName
    @FXML
    private TextField txtLastName;  // For LastName
    @FXML
    private TextField txtPhoneNumber;   // For PhoneNumber
    @FXML
    private TextField txtEmailAddress;  // For EmailAddress
    @FXML
    private TextField txtDepartment;    // For Department
    @FXML
    private TextField txtSemester;  // For Semester
    @FXML
    private TextField txtAddress;   // For Address
    @FXML
    private TextField txtArea;  // For Area
    @FXML
    private TextField txtGender;    // For Gender
    @FXML
    private Label lblStudentFee;    // For StudentFee

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        // For setting UserName and Password
        try {
            setUsernamePassword();

            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    // Method For Searching Record In Database
    public ResultSet searchRecord() throws SQLException {
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String sql = "SELECT * FROM student where userName = ? and password = ?";
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

    // Method For Setting Values in The TextFields
    public void setValues() throws SQLException {
       try{
            resultSet = searchRecord();
            while(resultSet.next()) {

                studentID = resultSet.getString("StudentID");
                txtStudentID.setText(studentID);

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
                lblStudentFee.setText("FEE : " + this.getDuesPerMonth());

                studentSemester = Integer.parseInt(resultSet.getString("studentSemester"));
                txtSemester.setText(String.valueOf(studentSemester));

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

    // Method For Setting UserName and Password
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
