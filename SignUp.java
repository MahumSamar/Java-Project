package flexTransport;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUp implements Initializable
{
    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Static Variable for DriverID
    public static String driverID;

    @FXML
    private TabPane tabPaneSignUp;
    @FXML
    private Tab studentTab;
    @FXML
    private Tab facultyTab;
    @FXML
    private Tab driverTab;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblStudent;
    @FXML
    private Label lblFaculty;
    @FXML
    private Label lblDriver;
    @FXML
    private Label labelStatus;

    //FXML ID's for entering the data by the user
    @FXML
    private TextField txtStudentFirstName;

    @FXML
    private PasswordField txtStudentPassword;

    @FXML
    private TextField txtStudentLastName;

    @FXML
    private TextField txtStudentID;

    @FXML
    private TextField txtStudentEmailAddress;

    @FXML
    private TextField txtStudentSemester;

    @FXML
    private TextField txtStudentUsername;

    @FXML
    private TextField txtStudentPhoneNumber;

    @FXML
    private ComboBox<String> cbStudentDepartment;

    @FXML
    private TextField txtStudentAddress;

    @FXML
    private ComboBox<String> cbStudentArea;

    @FXML
    private RadioButton rbStudentMale;

    @FXML
    private Label lblStudentFee;

    @FXML
    private Label lblFacultyFee;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbStudentFemale;

    @FXML
    private TextField txtFacultyFirstName;

    @FXML
    private TextField txtFacultyLastName;

    @FXML
    private TextField txtFacultyID;

    @FXML
    private TextField txtFacultyEmailAddress;

    @FXML
    private TextField txtFacultyUsername;

    @FXML
    private TextField txtFacultyPhoneNumber;

    @FXML
    private ComboBox<String> cbFacultyDepartment;

    @FXML
    private TextField txtFacultyAddress;

    @FXML
    private ComboBox<String> cbFacultyArea;

    @FXML
    private PasswordField txtFacultyPassword;

    @FXML
    private RadioButton rbFacultyMale;

    @FXML
    private ToggleGroup gender1;

    @FXML
    private RadioButton rbFacultyFemale;

    @FXML
    private ComboBox<String> cbFacultyDesignation;
    @FXML
    private TextField txtDriverFirstName;

    @FXML
    private TextField txtDriverLastName;

    @FXML
    private TextField txtDriverID;

    @FXML
    private TextField txtDriverEmailAddress;

    @FXML
    private TextField txtDriverAddress;

    @FXML
    private TextField txtDriverPhoneNumber;

    @FXML
    private TextField txtDriverExperience;

    @FXML
    private PasswordField txtDriverPassword;

    @FXML
    private RadioButton rbDriverMale;

    @FXML
    private ToggleGroup gender2;

    @FXML
    private RadioButton rbDriverFemale;

    @FXML
    private TextField txtDriverUsername;

    @FXML
    private ComboBox<String> cbDriverArea;

    @FXML
    private Button btStudentSignUp;

    @FXML
    private Button btFacultySignUp;

    @FXML
    private Button btDriverSignUp;

    @FXML
    private Label lblFillAllFields;

    Administrator administrator = new Administrator();

    // Observable List for Entering options Into ComboBox
    ObservableList<String> obListArea = FXCollections.observableArrayList(administrator.location());

    ObservableList<String> obListDepartment = FXCollections.observableArrayList("SEECS", "S3H", "SADA", "NBS", "SMME", "SCME", "IESE", "ASAB");

    ObservableList<String> obListDesignation = FXCollections.observableArrayList("Professor","Assistant Professor", "HOD");



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Initializes the ComboBoxes
        cbStudentArea.setItems(obListArea);
        cbFacultyArea.setItems(obListArea);
        cbDriverArea.setItems(obListArea);
        cbStudentDepartment.setItems(obListDepartment);
        cbFacultyDepartment.setItems(obListDepartment);
        cbFacultyDesignation.setItems(obListDesignation);

    }

    // Method For Opening StudentTab
    @FXML
    public void openStudentTab(MouseEvent event)
    {
        //open the admin tab when the administrator label is clicked
        TranslateTransition leftAnimation = new TranslateTransition(new Duration(500), labelStatus);
        leftAnimation.setToX(slidingPane.getTranslateX());
        leftAnimation.play();
        leftAnimation.setOnFinished((ActionEvent event2) -> labelStatus.setText("STUDENT"));
        tabPaneSignUp.getSelectionModel().select(studentTab);

    }

    // Method For Opening DriverTAb
    @FXML
    public void openDriverTab(MouseEvent event)
    {
        //opens the user tab when the user label is clicked
        //translation animation for sliding the label left and right
        TranslateTransition rightAnimation = new TranslateTransition(new Duration(500), labelStatus);
        //moves the status label to the appropriate location
        rightAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - labelStatus.getPrefWidth() ));
        rightAnimation.play();		//plays the animation
        rightAnimation.setOnFinished((ActionEvent event1) -> labelStatus.setText("DRIVER"));
        tabPaneSignUp.getSelectionModel().select(driverTab);

    }

    // Method For Opening FacultyTab
    @FXML
    public void openFacultyTab(MouseEvent event)
    {
        //opens the user tab when the user label is clicked
        //translation animation for sliding the label left and right
        TranslateTransition midAnimation = new TranslateTransition(new Duration(500), labelStatus);
        //moves the status label to the appropriate location
        midAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - 2*(labelStatus.getPrefWidth()) ));
        midAnimation.play();		//plays the animation
        midAnimation.setOnFinished((ActionEvent event1) -> labelStatus.setText("FACULTY"));
        tabPaneSignUp.getSelectionModel().select(facultyTab);

    }

    // Method For Opening SignInForm ( Loads SignUp.fxml )
    @FXML
    public void openSignInForm(MouseEvent event) throws Exception, IOException {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();

    }

    // Method For Student SignUp
    @FXML
    public void studentSignUp(MouseEvent event) throws Exception  {

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String gender = "";
        boolean area = (cbStudentArea.getValue() == null);
        String studentArea = cbStudentArea.getValue();
        Double fare = fareCalculate(studentArea);
        String firstName = txtStudentFirstName.getText();
        String lastName = txtStudentLastName.getText();
        String studentID = txtStudentID.getText();
        String phoneNumber = txtStudentPhoneNumber.getText();
        String emailAddress = txtStudentEmailAddress.getText();
        boolean department = (cbStudentDepartment.getValue() == null);
        String studentDepartment = cbStudentDepartment.getValue();
        if(rbStudentMale.isSelected())
        {
            gender = "Male";
        }
        else
        {
            gender = "Female";
        }
        int semester = 0;
        try
        {
            if(txtStudentSemester.getText() != null)
                semester = Integer.parseInt(txtStudentSemester.getText());
        }
        catch (NumberFormatException e)
        {
            semester = 0;
        }
        String address = txtStudentAddress.getText();
        String username = txtStudentUsername.getText();
        String password = txtStudentPassword.getText();
        if ((firstName.equals("")) || (lastName.equals("")) || (studentID.equals("")) || (phoneNumber.equals("")) ||
                (emailAddress.equals("")) || (department) || (semester == 0) || (gender.equals("")) ||
                (address.equals("")) || (username.equals("")) || (password.equals("")) || (area))
        {
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(firstName)))
        {
            lblFillAllFields.setText("First Name should only be alphabets.");
        }
        else if(!(isLetter(lastName)))
        {
            lblFillAllFields.setText("Last Name should only be alphabets.");
        }
        else if (!(semester <= 8 && semester >= 1) )
        {
            lblFillAllFields.setText("Semester Range is from 1 to 8.");
        }
        else if(!(isDigit(phoneNumber)))
        {
            lblFillAllFields.setText("Phone Number should be Digits.");
        }
        else if(!(phoneNumber.length() == 11))
        {
            lblFillAllFields.setText("phone Number length should be 11.");
        }
        else if(!(isOnlyAlphabetsAndDigit(studentID)))
        {
            lblFillAllFields.setText("StudentId includes Alphabet/Digit and '-' and '_' ");
        }
        else if(alreadyExistsUserID("student", studentID))
        {
            lblFillAllFields.setText("StudentID already exists. ");
        }
        else if(!(isOnlyAlphabetsAndDigit(username)))
        {
            lblFillAllFields.setText("Username includes Alphabet/Digit and '-' and '_'");
        }
        else if(!(isOnlyAlphabetsAndDigit(password)))
        {
            lblFillAllFields.setText("Password includes Alphabet/Digit and '-' and '_'");
        }
        else if(alreadyExistsUsername("student", username))
        {
            lblFillAllFields.setText("Username already exists.");
        }
        else if(!(username.length() >=8 && username.length() <= 15))
        {
            lblFillAllFields.setText("Username length should be 8 - 15.");
        }
        else if(!(password.length() >=8 && password.length() <= 15))
        {
            lblFillAllFields.setText("Password length should be 8 - 15.");
        }
        else
        {
            String sql = "Insert into student ( StudentID, userName, password, firstName," +
                    "lastName, gender, phoneNumber, emailAddress, department, studentSemester, " +
                    "address, area, fare ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, studentID);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, firstName);
                preparedStatement.setString(5, lastName);
                preparedStatement.setString(6, gender);
                preparedStatement.setString(7, phoneNumber);
                preparedStatement.setString(8, emailAddress);
                preparedStatement.setString(9, studentDepartment);
                preparedStatement.setInt(10, semester);
                preparedStatement.setString(11, address);
                preparedStatement.setString(12, studentArea);
                preparedStatement.setDouble(13, fare);

                preparedStatement.executeUpdate();

            openSignInForm(event);


        }

    }

    // Method For Faculty SignUp
    @FXML
    public void facultySignUp(MouseEvent event) throws Exception {

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String gender = "";
        boolean area = (cbFacultyArea.getValue() == null);
        String facultyArea = cbFacultyArea.getValue();
        Double fare = fareCalculate(facultyArea);
        String firstName = txtFacultyFirstName.getText();
        String lastName = txtFacultyLastName.getText();
        String facultyID = txtFacultyID.getText();
        String phoneNumber = txtFacultyPhoneNumber.getText();
        String emailAddress = txtFacultyEmailAddress.getText();
        boolean department = (cbFacultyDepartment.getValue() == null);
        String facultyDepartment = cbFacultyDepartment.getValue();
        if(rbFacultyMale.isSelected())
        {
            gender = "Male";
        }
        else
        {
            gender = "Female";
        }
        boolean designation = (cbFacultyDesignation.getValue() == null);
        String facultyDesignation = cbFacultyDesignation.getValue();
        String address = txtFacultyAddress.getText();
        String username = txtFacultyUsername.getText();
        String password = txtFacultyPassword.getText();
        if ((firstName.equals("")) || (lastName.equals("")) || (facultyID.equals("")) || (phoneNumber.equals("")) ||
                (emailAddress.equals("")) || (department) || (designation) || (gender.equals("")) ||
                (address.equals("")) || (username.equals("")) || (password.equals("")) || (area))
        {
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(firstName)))
        {
            lblFillAllFields.setText("First Name should only be alphabets.");
        }
        else if(!(isLetter(lastName)))
        {
            lblFillAllFields.setText("Last Name should only be alphabets.");
        }
        else if(!(isDigit(phoneNumber)))
        {
            lblFillAllFields.setText("Phone Number should be Digits.");
        }
        else if(!(phoneNumber.length() == 11))
        {
            lblFillAllFields.setText("Phone Number length should be 11.");
        }
        else if(!(isOnlyAlphabetsAndDigit(facultyID)))
        {
            lblFillAllFields.setText("FacultyID includes Alphabet/Digit and '-' and '_' ");
        }
        else if(alreadyExistsUserID("facultyMember", facultyID))
        {
            lblFillAllFields.setText("FacultyID already exists. ");
        }
        else if(!(isOnlyAlphabetsAndDigit(username)))
        {
            lblFillAllFields.setText("Username includes Alphabet/Digit and '-' and '_'");
        }
        else if(alreadyExistsUsername("facultyMember", username))
        {
            lblFillAllFields.setText("Username already exists.");
        }
        else if(!(isOnlyAlphabetsAndDigit(password)))
        {
            lblFillAllFields.setText("Password includes Alphabet/Digit and '-' and '_'");
        }
        else if(!(username.length() >=8 && username.length() <= 15))
        {
            lblFillAllFields.setText("Username length should be 8 - 15.");
        }
        else if(!(password.length() >=8 && password.length() <= 15))
        {
            lblFillAllFields.setText("Password length should be 8 - 15.");
        }
        else
        {
            String sql = "Insert into facultyMember ( FacultyID, userName, password, firstName," +
                    "lastName, gender, phoneNumber, emailAddress, designation, department, " +
                    "address, area, fare ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, facultyID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, emailAddress);
            preparedStatement.setString(9, facultyDesignation);
            preparedStatement.setString(10, facultyDepartment);
            preparedStatement.setString(11, address);
            preparedStatement.setString(12, facultyArea);
            preparedStatement.setDouble(13, fare);

            preparedStatement.executeUpdate();

            openSignInForm(event);


        }

    }

    // Method For Driver SignUp
    @FXML
    public void driverSignUp(MouseEvent event) throws Exception {

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        String gender = "";
        boolean area = (cbDriverArea.getValue() == null);
        String driverArea = cbDriverArea.getValue();
        String firstName = txtDriverFirstName.getText();
        String lastName = txtDriverLastName.getText();
        driverID = txtDriverID.getText();
        String phoneNumber = txtDriverPhoneNumber.getText();
        String emailAddress = txtDriverEmailAddress.getText();
        int experience = 0;
        try{
            
            experience = Integer.parseInt(txtDriverExperience.getText());
        }catch(Exception e)
        {
            lblFillAllFields.setText("Experience should be digit.");
        }

        if(rbDriverMale.isSelected())
        {
            gender = "Male";
        }
        else
        {
            gender = "Female";
        }

        String address = txtDriverAddress.getText();
        String username = txtDriverUsername.getText();
        String password = txtDriverPassword.getText();
        if ((firstName.equals("")) || (lastName.equals("")) || (driverID.equals("")) || (phoneNumber.equals("")) ||
                (emailAddress.equals("")) ||  (gender.equals("")) ||
                (address.equals("")) || (username.equals("")) || (password.equals("")) || (area) || (experience==0))
        {
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(firstName)))
        {
            lblFillAllFields.setText("First Name should only be alphabets.");
        }
        else if(!(isLetter(lastName)))
        {
            lblFillAllFields.setText("Last Name should only be alphabets.");
        }
        else if(!(isDigit(phoneNumber)))
        {
            lblFillAllFields.setText("Phone Number should be Digits.");
        }
        else if(!(phoneNumber.length() == 11))
        {
            lblFillAllFields.setText("Phone Number length should be 11.");
        }
        else if(!(isOnlyAlphabetsAndDigit(driverID)))
        {
            lblFillAllFields.setText("DriverID includes Alphabet/Digit and '-' and '_' ");
        }
        else if(alreadyExistsUserID("driver", driverID))
        {
            lblFillAllFields.setText("DriverID already exists. ");
        }
        else if(!(isOnlyAlphabetsAndDigit(username)))
        {
            lblFillAllFields.setText("Username includes Alphabet/Digit and '-' and '_'");
        }
        else if(!(isOnlyAlphabetsAndDigit(password)))
        {
            lblFillAllFields.setText("Password includes Alphabet/Digit and '-' and '_'");
        }
        else if(alreadyExistsUsername("driver", username))
        {
            lblFillAllFields.setText("Username already exists.");
        }
        else if(!(username.length() >=8 && username.length() <= 15))
        {
            lblFillAllFields.setText("Username length should be 8 - 15.");
        }
        else if(!(password.length() >=8 && password.length() <= 15))
        {
            lblFillAllFields.setText("Password length should be 8 - 15.");
        }
        else
        {
            String sql = "Insert into driver ( DriverID, userName, password, firstName," +
                    "lastName, gender, phoneNumber, emailAddress, experience, " +
                    "address, area ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, emailAddress);
            preparedStatement.setInt(9, experience);
            preparedStatement.setString(10, address);
            preparedStatement.setString(11, driverArea);

            preparedStatement.executeUpdate();

            openVehiclesForm(event);


        }

    }

    // Method For Checking String Contains Letters
    public boolean isLetter(String s)
    {

        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for(int i=0 ; i < charArray.length ; i++)
        {
            char ch = charArray[i];
            if(!(((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'))))
            {
                return false;
            }
        }
        return true;
    }

    // Method For Checking String Contains Digits
    public boolean isDigit(String s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for(int i=0 ; i < charArray.length ; i++)
        {
            char ch = charArray[i];
            if(!((ch >= '0') && (ch <= '9')))
            {
                return false;
            }
        }
        return true;
    }

    // Method For Checking String Contains Letters and Digits
    public boolean isOnlyAlphabetsAndDigit(String  s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for(int i=0 ; i < charArray.length ; i++)
        {
            char ch = charArray[i];
            if(!(((ch >= '0') && (ch <= '9')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) || (ch == '_') || (ch == '-')))
            {
                return false;
            }
        }
        return true;
    }

    // Method For CalculatingFare
    public Double fareCalculate(String area)
    {
        Double fare = 0.0;
        try{
            MysqlConnection mysqlConnection = new MysqlConnection();
            String sql = "select fare from fareCalculation where area = ?";
            connection = mysqlConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, area);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            fare = resultSet.getDouble("fare");

        }catch(Exception e)
        {

        }

        return fare;
    }

    // Method For Setting Fee Label
    public void setFeeLabel(ActionEvent event)
    {
        boolean area = (cbStudentArea.getValue() == null);
        String studentArea = cbStudentArea.getValue();
        Double fare = fareCalculate(studentArea);
        lblStudentFee.setText("FEE : " + fare);

    }

    // Method For Checking UserName Already Exists
    public boolean alreadyExistsUsername(String title, String username)
    {
        String sql = "";
        String DbUsername = "";
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;



        try
        {
            if(title.equals("student"))
            {
                sql = "SELECT * FROM student where userName = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, username);

                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    DbUsername = resultSet.getString("userName");
                }



                if(!(DbUsername.equals(username)))
                {
                    result = false;
                }
                else
                {
                    result =  true;
                }

            }
            else if(title.equals("facultyMember"))
            {
                sql = "SELECT * FROM facultyMember where userName = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, username);

                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    DbUsername = resultSet.getString("userName");
                }



                if(!(DbUsername.equals(username)))
                {
                    result = false;
                }
                else
                {
                    result =  true;
                }

            }
            else if(title.equals("driver"))
            {
                sql = "SELECT * FROM driver where userName = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, username);

                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    DbUsername = resultSet.getString("userName");
                }



                if(!(DbUsername.equals(username)))
                {
                    result = false;
                }
                else
                {
                    result =  true;
                }

            }

        }catch(Exception e)
        {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE,null,e);
        }
        return result;
    }

    // Method For Checking User ID Already Exist
    public boolean alreadyExistsUserID(String title, String userID)
    {
        String sql = "";
        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        boolean result = false;

        try {
            if(title.equals("student"))
            {
                sql = "SELECT * FROM student where StudentID = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, userID);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    String DbUserID = resultSet.getString("StudentID");


                    if (!(DbUserID.equals(userID))) {
                        result = false;
                    } else {
                        result = true;
                    }
                }
            }
            else if(title.equals("facultyMember"))
            {
                sql = "SELECT * FROM facultyMember where FacultyID = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, userID);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    String DbUserID = resultSet.getString("FacultyID");


                    if (!(DbUserID.equals(userID))) {
                        result = false;
                    } else {
                        result = true;
                    }
                }
            }
            else if(title.equals("driver"))
            {
                sql = "SELECT * FROM driver where DriverID = ? ";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, userID);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    String DbUserID = resultSet.getString("DriverID");


                    if (!(DbUserID.equals(userID))) {
                        result = false;
                    } else {
                        result = true;
                    }
                }
            }

            else {
                return false;
            }

            }catch(Exception e)
            {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);
            }

        return result;
    }

    // Method For Opening VehicleForm ( Loads VehiclesInfo.fxml )
    public void openVehiclesForm(MouseEvent event) throws IOException {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("VehiclesInfo.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();
    }


}

