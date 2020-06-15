package flexTransport;
// Controller Class for AdminSignInDashboard.fxml

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSignInDashBoard implements Initializable
{
    // fxml ID's
    @FXML
    private Label lbNoOfCustomers;     // for number of customers
    @FXML
    private Label lbStudents;   // for number of students
    @FXML
    private Label lbFaculty;    // for number of Faculty
    @FXML
    private Label lbDrivers;    // for number of drivers
    @FXML
    private Label lbNoOfVehicles;   // for number of vehicles
    @FXML
    private Label lbBuses;  // for number of Buses
    @FXML
    private Label lbVans;  // for number of vans
    @FXML
    private Label lbShuttle;    // for number of shuttles

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            // Fill the Labels'
            dashBoard();
        } catch (Exception e) {
        }

    }

    public void dashBoard() throws Exception
    {

        // Creating Administrator Class Object
        Administrator administrator = new Administrator();
        // Loading information from MySql and is displaying in labels'
        lbNoOfCustomers.setText(Integer.toString(administrator.getCustomers()));
        lbStudents.setText(Integer.toString(administrator.getStudents()));
        lbFaculty.setText(Integer.toString(administrator.getFaculty()));
        lbDrivers.setText(Integer.toString(administrator.getDrivers()));

        lbNoOfVehicles.setText(Integer.toString(administrator.getVehicles()));
        lbBuses.setText(Integer.toString(administrator.getBuses()));
        lbVans.setText(Integer.toString(administrator.getVans()));
        lbShuttle.setText(Integer.toString(administrator.getShuttles()));

    }








}
