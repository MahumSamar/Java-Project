package flexTransport;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import flexTransport.MysqlConnection;
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

public class VehiclesController implements Initializable{

    //DataBase Connection
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String driverID = SignUp.driverID;
    String vehicle;

//all basic fxml IDs

    @FXML
    private Pane slidingPane;

    @FXML
    private TabPane tabPaneSignUp;

    @FXML
    private Label lblVans;

    @FXML
    private Label lblShuttles;

    @FXML
    private Label lblBuses;

    @FXML
    private Tab vansTab;

    @FXML
    private Tab shuttleTab;


    @FXML
    private Tab busesTab;


    @FXML
    private Label lblStatus;

    private RadioButton rbYes;

    @FXML
    private RadioButton rbNo;



    //FXML ID's for entering the data by the user
    //for vans:
    @FXML
    private TextField txtVanModel;

    @FXML
    private TextField txtVanColor;

    @FXML
    private TextField txtVanNo;

    @FXML
    private TextField txtVanSittingCapacity;

    @FXML
    private TextField txtVanManufacturer;

    @FXML
    private RadioButton rbvaninsuredYes;

    @FXML
    private RadioButton rbvaninsuredNo;

    @FXML
    private ComboBox<String> cbtime;


    @FXML
    private TextField txtShuttleModel;

    @FXML
    private TextField txtShuttleColor;

    @FXML
    private TextField txtShuttleNo;

    @FXML
    private TextField txtShuttleSittingCapacity;

    @FXML
    private TextField txtShuttleManufacturer;

    @FXML
    private RadioButton rbShuttleinsuredYes;

    @FXML
    private RadioButton rbShuttleinsuredNo;

    @FXML
    private ComboBox<String> cbtimesh;


    //for buses
    @FXML
    private TextField txtBusModel;

    @FXML
    private TextField txtBusColor;

    @FXML
    private TextField txtBusNo;

    @FXML
    private TextField txtBusSittingCapacity;

    @FXML
    private TextField txtBusManufacturer;

    @FXML
    private RadioButton rbBusinsuredYes;

    @FXML
    private RadioButton rbBusinsuredNo;

    @FXML
    private ComboBox<String> cbtimebus;

    @FXML
    private Label timelabelbus;
    @FXML
    private ToggleGroup InsuranceStatus;
    @FXML
    private ToggleGroup InsuranceStatus1;
    @FXML
    private ToggleGroup InsuranceStatus11;

    //updating
    @FXML
    private Button btaddVan;

    @FXML
    private Button btaddShuttle;

    @FXML
    private Button btaddBus;
    @FXML
    private Label lblFillAllFields;



    //for creating array list for available timings of all three vehicles to be shown through combo boxes:
    ObservableList<String> timelist= FXCollections.observableArrayList("09:00 A.M","1:00 P.M","06:00 P.M");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setting of combo boxes for all times available
        cbtime.setItems(timelist);
        cbtimesh.setItems(timelist);
        cbtimebus.setItems(timelist);

    }

    @FXML
    void openbusesTab(MouseEvent event) {
        //opens the buses tab when the buses label is clicked
        //translation animation for sliding the label left and right
        TranslateTransition rightAnimation = new TranslateTransition(new Duration(500), lblStatus);
        //moves the status label to the appropriate location
        rightAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - lblStatus.getPrefWidth() ));
        rightAnimation.play();		//plays the animation
        rightAnimation.setOnFinished((ActionEvent event1) -> lblStatus.setText("BUSES"));
        tabPaneSignUp.getSelectionModel().select(busesTab);



    }

    @FXML
    void openshuttlesTab(MouseEvent event) {
        //opens the shuttles tab when the shuttle label is clicked
        //translation animation for sliding the label left and right
        TranslateTransition midAnimation = new TranslateTransition(new Duration(500), lblStatus);
        //moves the status label to the appropriate location
        midAnimation.setToX(slidingPane.getTranslateX() + (slidingPane.getPrefWidth() - 2*(lblStatus.getPrefWidth()) ));
        midAnimation.play();		//plays the animation
        midAnimation.setOnFinished((ActionEvent event1) -> lblStatus.setText("SHUTTLES"));
        tabPaneSignUp.getSelectionModel().select(shuttleTab);

    }

    @FXML
    void openvansTab(MouseEvent event) {
        //open the vans tab when the vans label is clicked
        TranslateTransition leftAnimation = new TranslateTransition(new Duration(500), lblStatus);
        //moves the status label to the appropriate location
        leftAnimation.setToX(slidingPane.getTranslateX());
        leftAnimation.play();
        leftAnimation.setOnFinished((ActionEvent event2) -> lblStatus.setText("VANS"));
        tabPaneSignUp.getSelectionModel().select(vansTab);

    }

    @FXML
    public void openSignInForm(MouseEvent event) throws Exception, IOException  //moves bck to sign in
    {
        Parent SignInForm = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene SignInScene = new Scene(SignInForm);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(SignInScene);
        window.show();

    }
    @FXML
    public void savingvansinfo(MouseEvent event) throws Exception  {  //all vans info is taken by connecting to database

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();   //connection is established
        //insurance status that is taken through radio buttons
        String InsuranceStatus = "";
        //setting all other information taken in text fields
        vehicle = "Van";
        String Model = txtVanModel.getText();
        String Color= txtVanColor.getText();
        String VehNo= txtVanNo.getText();
        String Manufacturer = txtVanManufacturer.getText();
        boolean timing = (cbtime.getValue()==null);
        String vanTiming=cbtime.getValue();
        if(rbvaninsuredYes.isSelected())  //to check if vehicle is insured
        {
            InsuranceStatus = "Insured";
        }
        else   //If vehicle is not insured
        {
            InsuranceStatus = "Not Insured";
        }
        int SittingCapacity = 0;
        try
        {    //if some sitting capacity is added
            if(txtVanSittingCapacity.getText() != null)
                SittingCapacity = Integer.parseInt(txtVanSittingCapacity.getText());
        }
        catch (NumberFormatException e)
        {
            SittingCapacity = 0;
        }

        //to check and verify with the conditions that none of the field is left empty
        if ((Model.equals("")) || (Color.equals("")) || (VehNo.equals("")) || (Manufacturer.equals("")) ||
                (timing) || (SittingCapacity == 0) || (InsuranceStatus.equals("")))
        {
            //Prompt message if any field is left empty
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(Color)))  //check to ensure that color containing all alphabets is added
        //isLetter() is a method defined later
        {
            lblFillAllFields.setText("Color should be valid.");
        }
        else if(!(isDigit(Model)))  //check to ensure that Model of a vehicle containing all digits is added
        // method "isDigit" is defined later
        {
            lblFillAllFields.setText("Model should only contain digits.");
        }
        else if (!(SittingCapacity <=30 && SittingCapacity >= 1) ) //For vans, sitting capacity of 30 passengers are allowed
        {
            lblFillAllFields.setText("Sitting Capacity is from 1 to 30.");
        }
        else if(!(isLetter(Manufacturer))) //check to ensure that manufacturer  containing all alphabets is added
        {
            lblFillAllFields.setText("Manufacturer name should only contain letters.");
        }
        else if(!(VehNo.length()>=4 && VehNo.length()<=7))
        {
            lblFillAllFields.setText("Vehicle No. should be valid");
        }

        else
        {
            //if everything is according to the requirement: Then add:
            String sql = "Insert into vehicles ( DriverID, Vehicle, Model, Color," +
                    "VehNo, Manufacturer, SittingCapacity, Timing, InsuranceStatus)" +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, vehicle);
            preparedStatement.setString(3, Model);
            preparedStatement.setString(4, Color);
            preparedStatement.setString(5, VehNo);
            preparedStatement.setString(6, Manufacturer);
            preparedStatement.setInt(7, SittingCapacity);
            preparedStatement.setString(8, vanTiming);
            preparedStatement.setString(9, InsuranceStatus);


            preparedStatement.executeUpdate();

            openSignInForm(event);


        }

    }

    @FXML
    public void savingshuttlesinfo(MouseEvent event) throws Exception  { //all shuttlles' info is taken by connecting to database

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();   //connection is established
        //insurance status that is taken through radio buttons
        String InsuranceStatus = "";
        vehicle = "Shuttle";
        //setting all other information taken in text fields
        String Model = txtShuttleModel.getText();
        String Color= txtShuttleColor.getText();
        String VehNo= txtShuttleNo.getText();
        String Manufacturer = txtShuttleManufacturer.getText();
        boolean timing = (cbtimesh.getValue()==null);
        String shuttleTiming=cbtimesh.getValue();
        if(rbShuttleinsuredYes.isSelected())  //to check if vehicle is insured
        {
            InsuranceStatus = "Insured";
        }
        else
        {    //If vehicle is not insured
            InsuranceStatus = "Not Insured";
        }
        int SittingCapacity = 0;
        try
        {  //if some sitting capacity is added
            if(txtShuttleSittingCapacity.getText() != null)
                SittingCapacity = Integer.parseInt(txtVanSittingCapacity.getText());
        }
        catch (NumberFormatException e)
        {
            SittingCapacity = 0;
        }

        //to check and verify with the conditions that none of the field is left empty
        if ((Model.equals("")) || (Color.equals("")) || (VehNo.equals("")) || (Manufacturer.equals("")) ||
                (timing) || (SittingCapacity == 0) || (InsuranceStatus.equals("")))
        {
            //Prompt message if any field is left empty
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(Color)))  //check to ensure that color containing all alphabets is added
        //isLetter() is a method defined later
        {
            lblFillAllFields.setText("Color should be valid.");
        }
        else if(!(isDigit(Model)))   //check to ensure that Model of a vehicle containing all digits is added
        // method "isDigit" is defined later
        {
            lblFillAllFields.setText("Model should only contain digits.");
        }
        else if (!(SittingCapacity <=12 && SittingCapacity >= 1) ) //For shuttles, sitting capacity of 12 passengers are allowed
        {
            lblFillAllFields.setText("Sitting Capacity is from 1 to 8.");
        }
        else if(!(isLetter(Manufacturer)))  //check to ensure that manufacturer  containing all alphabets is added
        {
            lblFillAllFields.setText("Manufacturer name should only contain letters.");
        }
        else if(!(VehNo.length()>=4 && VehNo.length()<=7))
        {
            lblFillAllFields.setText("Vehicle No. should be valid");
        }

        else
        {    //if everything is according to the requirement: Then add:
            String sql = "Insert into vehicles ( DriverID, Vehicle, Model, Color," +
                    "VehNo, Manufacturer, SittingCapacity, Timing, InsuranceStatus)" +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, vehicle);
            preparedStatement.setString(3, Model);
            preparedStatement.setString(4, Color);
            preparedStatement.setString(5, VehNo);
            preparedStatement.setString(6, Manufacturer);
            preparedStatement.setInt(7, SittingCapacity);
            preparedStatement.setString(8, shuttleTiming);
            preparedStatement.setString(9, InsuranceStatus);


            preparedStatement.executeUpdate();

            openSignInForm(event);


        }

    }

    @FXML
    public void savingbusesinfo(MouseEvent event) throws Exception  {

        MysqlConnection mysqlConnection = new MysqlConnection();
        connection = mysqlConnection.getConnection();
        //insurance status that is taken through radio buttons
        String InsuranceStatus = "";
        vehicle = "Bus";
        //setting all other information taken in text fields
        String Model = txtBusModel.getText();
        String Color= txtBusColor.getText();
        String VehNo= txtBusNo.getText();
        String Manufacturer = txtBusManufacturer.getText();
        boolean timing = (cbtimebus.getValue()==null);
        String busTiming=cbtimebus.getValue();
        if(rbBusinsuredYes.isSelected())   //to check if vehicle is insured
        {
            InsuranceStatus = "Insured";
        }
        else
        {  //If vehicle is not insured
            InsuranceStatus = "Not Insured";
        }
        int SittingCapacity = 0;
        try
        {   //if some sitting capacity is added
            if(txtBusSittingCapacity.getText() != null)
                SittingCapacity = Integer.parseInt(txtVanSittingCapacity.getText());
        }
        catch (NumberFormatException e)
        {
            SittingCapacity = 0;
        }

        //to check and verify with the conditions that none of the field is left empty
        if ((Model.equals("")) || (Color.equals("")) || (VehNo.equals("")) || (Manufacturer.equals("")) ||
                (timing) || (SittingCapacity == 0)  || (InsuranceStatus.equals("")))
        {   //Prompt message if any field is left empty
            lblFillAllFields.setText("Fill All the Fields.");

        }
        else if(!(isLetter(Color)))  //check to ensure that color containing all alphabets is added
        //isLetter() is a method defined later
        {
            lblFillAllFields.setText("Color should be valid.");
        }
        else if(!(isDigit(Model)))   //check to ensure that Model of a vehicle containing all digits is added
        // method "isDigit" is defined later
        {
            lblFillAllFields.setText("Model should only contain digits.");
        }
        else if (!(SittingCapacity <=50 && SittingCapacity >= 1) )  //For vans, sitting capacity of 50 passengers are allowed
        {
            lblFillAllFields.setText("Sitting Capacity is from 1 to 8.");
        }
        else if(!(isLetter(Manufacturer)))  //check to ensure that manufacturer  containing all alphabets is added
        {
            lblFillAllFields.setText("Manufacturer name should only contain letters.");
        }
        else if(!(VehNo.length()>=4 && VehNo.length()<=7))
        {
            lblFillAllFields.setText("Vehicle No. should be valid");
        }

        else
        {    //if everything is according to the requirement: Then add:
            String sql = "Insert into vehicles ( DriverID, Vehicle, Model, Color," +
                    "VehNo, Manufacturer, SittingCapacity, Timing, InsuranceStatus)" +
                    " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            preparedStatement.setString(2, vehicle);
            preparedStatement.setString(3, Model);
            preparedStatement.setString(4, Color);
            preparedStatement.setString(5, VehNo);
            preparedStatement.setString(6, Manufacturer);
            preparedStatement.setInt(7, SittingCapacity);
            preparedStatement.setString(8, busTiming);
            preparedStatement.setString(9, InsuranceStatus);


            preparedStatement.executeUpdate();

            openSignInForm(event);


        }

    }

    //A method for making sure that valid string containing all letters is added
    public boolean isLetter(String s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();  //making an array of all characters
        for(int i=0 ; i < charArray.length ; i++)  //iterating through an array
        {
            char ch = charArray[i];
            //condition to check if character at the desired position is not a letter
            if(!(((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'))))
            {
                return false;
            }
        }
        return true;
    }

    //Another method for making sure that valid string containing all digits is added
    public boolean isDigit(String s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray(); //making an array of all characters
        for(int i=0 ; i < charArray.length ; i++)  //iterating through an array
        {
            char ch = charArray[i];
            //condition to check if character at the desired position is not a digit
            if(!(ch >= '0') && (ch <= '9'))
            {
                return false;
            }
        }
        return true;
    }




}





