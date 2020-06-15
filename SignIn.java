package flexTransport;

import javafx.animation.TranslateTransition;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class SignIn implements Initializable
{
    // Static Variables For UserName and Password
    public static String username;
    public static String password;

    //tab pane for student, faculty and driver
    @FXML
    private TabPane tabPaneLogin;
    @FXML
    //admin tab
    private Tab adminTab;
    //user tab
    @FXML
    private Tab userTab;
    //label for notify the sign up  labels to the user
    @FXML
    private Label lbCreateAccount;
    //sliding pane where the animation is applied
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblUser;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelAdminError; //for notifying wrong credentials
    @FXML
    private Label labelUserError;   //for notifying wrong credentials For user
    @FXML
    private Button btAdminSignIn;
    @FXML
    private TextField txtAdminUsername; //for AdminUserName
    @FXML
    private TextField txtAdminPassword; //for AdminPassWord
    @FXML
    private TextField txtUserUsername;  //for UserName
    @FXML
    private TextField txtUserPassword; // for UserPassWord
    @FXML
    private Button btUserSignIn;
    @FXML
    private RadioButton rbStudent;
    @FXML
    private RadioButton rbFaculty;
    @FXML
    private RadioButton rbDriver;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    // Method For Opening AdminTab
    @FXML
    public void openAdminTab(MouseEvent event)
    {
        //open the admin tab when the administrator label is clicked
        TranslateTransition leftAnimation = new TranslateTransition(new Duration(500), labelStatus);
        leftAnimation.setToX(slidingPane.getTranslateX());
        leftAnimation.play();
        leftAnimation.setOnFinished((ActionEvent event2) -> labelStatus.setText("ADMINISTRATOR"));
        tabPaneLogin.getSelectionModel().select(adminTab);
    }

    // Method For Opening UserTab
    @FXML
    public void openUserTab(MouseEvent event)

    {
        //opens the user tab when the user label is clicked
        //translation animation for sliding the label left and right
        TranslateTransition rightAnimation = new TranslateTransition(new Duration(500), labelStatus);
        //moves the status label to the appropriate location
        rightAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - labelStatus.getPrefWidth() ));
        rightAnimation.play();		//plays the animation
        rightAnimation.setOnFinished((ActionEvent event1) -> labelStatus.setText("USER"));
        //when moves to the user label the text is changed to USER
        tabPaneLogin.getSelectionModel().select(userTab);

    }

    // Method For Opening SignUpForm
    public void openSignUpForm(MouseEvent event) throws Exception
    {
        Parent SignUpForm = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene SignUpScene = new Scene(SignUpForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignUpScene);
        window.show();

    }

    // Method For Opening AdminAccount
    public void openAdminAccount(ActionEvent event) throws IOException
    {
        username = txtAdminUsername.getText();
        password = txtAdminPassword.getText();
        Administrator administrator = new Administrator();
        boolean result = administrator.login(username,password);
        if(result)
        {
            Parent adminSignIn = FXMLLoader.load(getClass().getResource("AdminSignIn.fxml"));
            Scene adminAccount = new Scene(adminSignIn);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(adminAccount);
            window.show();
        }
        else
        {
            labelAdminError.setText("Username or Password is Incorrect");
        }

    }

    // Method For Opening UserAccount
    public void openUserAccount(ActionEvent event) throws IOException, SQLException {
        username = txtUserUsername.getText();
        password = txtUserPassword.getText();
        if(rbStudent.isSelected())
        {
            openStudentAccount(event,username,password);
        }
        else if(rbFaculty.isSelected())
        {
            openFacultyAccount(event,username,password);
        }
        else if(rbDriver.isSelected())
        {
            openDriverAccount(event,username,password);
        }

    }

    // Method For Opening StudentAccount
    public void openStudentAccount(ActionEvent event,String username,String password) throws IOException, SQLException {

        Student student = new Student();
        boolean result = student.login(username,password);
        if(result)
        {
            Parent studentSignIn = FXMLLoader.load(getClass().getResource("StudentSignIn.fxml"));
            Scene studentAccount = new Scene(studentSignIn);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(studentAccount);
            window.show();
        }
        else
        {
            labelUserError.setText("Username or Password is Incorrect");
        }

    }

    // Method For Opening FacultyAccount
    public void openFacultyAccount(ActionEvent event,String username,String password) throws IOException
    {
        FacultyMember facultyMember = new FacultyMember();
        boolean result = facultyMember.login(username,password);
        if(result)
        {
            Parent studentSignIn = FXMLLoader.load(getClass().getResource("FacultySignIn.fxml"));
            Scene studentAccount = new Scene(studentSignIn);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(studentAccount);
            window.show();
        }
        else
        {
            labelUserError.setText("Username or Password is Incorrect");
        }

    }

    // Method For Opening DriverAccount
    public void openDriverAccount(ActionEvent event,String username,String password) throws IOException
    {
        Driver driver = new Driver();
        boolean result = driver.login(username,password);
        if(result)
        {
            Parent driverSignIn = FXMLLoader.load(getClass().getResource("DriverSignIn.fxml"));
            Scene driverAccount = new Scene(driverSignIn);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(driverAccount);
            window.show();
        }
        else
        {
            labelUserError.setText("Username or Password is Incorrect");
        }

    }


}

