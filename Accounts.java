package flexTransport;

//This Class Is Used to control accounts information for this app,
//this is controller class for Accounts.fxml

//these are the imports used for this class
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Accounts implements Initializable
{
    // Establishing the connection to database
    MysqlConnection connection = new MysqlConnection();
    String sql = Administrator.sql;
    ResultSet resultSet = connection.getConnection().createStatement().executeQuery(sql);

    //All the fxml ID's used in the fxml
    @FXML
    private TableView<ObservableList> AccountsTable;    // for table
    @FXML
    private TextField txtNewArea;   // for entering new area
    @FXML
    private TextField txtNewFare;   // for entering new fare
    @FXML
    private Label lblError;     //Printing Error
    @FXML
    private Button btAdd;   // Adding new area to database
    @FXML
    private TextField txtTotalIncome;   // Print Total income

    @FXML
    private TextField txtSalariesPaid;  //Print Total Salaries paid to driver

    @FXML
    private TextField txtStatus;    // Status shows profit or loss

    ObservableList<ObservableList> obList = FXCollections.observableArrayList();

    public Accounts() throws SQLException {
    }   // Constructor


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Abstract method of Initializable
        try {
            // Fills the Table of area and fare
            fillTable();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        // Prints the summary of accounts
        Double totalIncome = totalIncome();
        Double salariesPaid = salariesPaid();
        txtTotalIncome.setText(String.valueOf(totalIncome));
        txtSalariesPaid.setText(String.valueOf(salariesPaid));

        if(totalIncome < salariesPaid)
        {
            txtStatus.setText("LOSS");
        }
        else if(totalIncome == salariesPaid)
        {
            txtStatus.setText("NO PROFIT/LOSS");
        }
        else
        {
            txtStatus.setText("PROFIT");
        }



    }

    // Executed when button to add area and fare is pressed and data is stored in the database
    public void addAreaFare(ActionEvent event) throws SQLException, IOException {
        String area = txtNewArea.getText();
        String fare = txtNewFare.getText();
        if((area.equals("")) || (fare.equals("")))
        {
            lblError.setText("Fill All Fields.");
        }
        else if(!(isOnlyAlphabetsAndDigit(area)))
        {
            lblError.setText("Area includes Alphabets/Digits and '-' and '_' s");
        }
        else if(!(isDigit(fare)))
        {
            lblError.setText("Fare includes Digits and '.'");
        }
        else if(areaAlreadyExists(area))
        {
            lblError.setText("Area already exists.");
        }
        else
        {
            String fareCalculationSql = "insert into fareCalculation ( area, fare ) values ( ?, ? )";
            Connection fareCalculationConnection = connection.getConnection();
            PreparedStatement preparedStatement = fareCalculationConnection.prepareStatement(fareCalculationSql);
            preparedStatement.setString(1, area);
            preparedStatement.setString(2, fare);

            preparedStatement.executeUpdate();

            lblError.setText("Click 'Accounts' button again to see the changes.");
            txtNewArea.setText("");
            txtNewFare.setText("");

        }
    }

    // Method to get the columns of the fareCalculation Table of database
    private void getColumns() throws SQLException {


        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            col.setMinWidth(150);


            AccountsTable.getColumns().addAll(col);


        }
    }
    // Method to get the rows of the fareCalculation Table of database
    private void getRows() throws SQLException {
        while (resultSet.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(resultSet.getString(i));
            }

            obList.add(row);

        }
        AccountsTable.setItems(obList);
    }


    // Fills the Table with the column and rows read from database table fareCalculation
    private void fillTable()
    {
        try {
            getColumns();
            getRows();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Method to check  the fare is digit
    public boolean isDigit(String s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for(int i=0 ; i < charArray.length ; i++)
        {
            char ch = charArray[i];
            if(!(((ch >= '0') && (ch <= '9') || (ch == '.'))))
            {
                return false;
            }
        }
        return true;
    }

    // Method to Check area consists of alphabets and digits
    public boolean isOnlyAlphabetsAndDigit(String  s)
    {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for(int i=0 ; i < charArray.length ; i++)
        {
            char ch = charArray[i];
            if(!(((ch >= '0') && (ch <= '9')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) || (ch == '_') || (ch == '-') || (ch == ' ')))
            {
                return false;
            }
        }
        return true;
    }

    // Method to check that the entered area is not already existing
    public boolean areaAlreadyExists(String area) throws SQLException {
        String areaSql = "select * from fareCalculation where area = ?";
        boolean result = false;
        try{
            Connection areaConnection = connection.getConnection();
            PreparedStatement preparedStatement = areaConnection.prepareStatement(areaSql);
            preparedStatement.setString(1, area);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
            {
                result = false;
            }
            else
            {
                result = true;

            }
        }catch (Exception e)
        {

        }
        return result;

    }

    // method to get the fare of all the students
    public Double getStudentFare()
    {
        Double studentFare= 0.0;
        String studentSql = "select sum(fare) from student";

        try{
            Connection fareConnection = connection.getConnection();
            PreparedStatement preparedStatement = fareConnection.prepareStatement(studentSql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                studentFare = resultSet.getDouble(1);
            }

        }catch (Exception e)
        {

        }
        return studentFare;

    }

    // method to get the fare of all the faculty members
    public Double getFacultyFare()
    {
        Double facultyFare= 0.0;
        String facultySql = "select sum(fare) from facultyMember";

        try{
            Connection fareConnection = connection.getConnection();
            PreparedStatement preparedStatement = fareConnection.prepareStatement(facultySql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                facultyFare = resultSet.getDouble(1);
            }

        }catch (Exception e)
        {

        }
        return facultyFare;

    }

    // method to caluculate total income
    public Double totalIncome()
    {
        Double totalIncome = getFacultyFare() + getStudentFare();
        return totalIncome;
    }

    // Method to Calculate salaries paid to the driver
    public Double salariesPaid()
    {
        Administrator administrator = new Administrator();

        Double salariesPaid = administrator.getVans()*10000.0 +
                administrator.getBuses()*15000.0 +
                administrator.getShuttles()*8000.0;
        return salariesPaid;
    }


}
