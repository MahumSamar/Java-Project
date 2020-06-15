package flexTransport;
// Class For Establishing the Connection To MySql DataBase
import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection
{
    // Connection Variable
    Connection con = null;
    // Method to Establish Connection
    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/flexTransport","root","guest");
            return connection;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
