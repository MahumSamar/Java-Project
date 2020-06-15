package flexTransport;

// Controller Class AdminSignInTables.fxml

// All the imports needed
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminSignInTables implements Initializable {

    // MySql Connection
    MysqlConnection connection = new MysqlConnection();
    String sql = Administrator.sql;
    ResultSet resultSet = connection.getConnection().createStatement().executeQuery(sql);

    // fxml ID for Table
    @FXML
    private TableView<ObservableList> StudentTable;

    // ObservableList For Loading Table
    ObservableList<ObservableList> obList = FXCollections.observableArrayList();

    // Constructor
    public AdminSignInTables() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Fills Table
        fillTable();


    }

    // Method to get the columns of the Table of database
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

            StudentTable.getColumns().addAll(col);


        }
    }

    // Method to get the rows of the Table of database
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
        StudentTable.setItems(obList);
    }

    // Method to fill the Table
    private void fillTable()
        {
            try {
                getColumns();
                getRows();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



    }


