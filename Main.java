package flexTransport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage stage) throws Exception
    {
        // Loads the SignIn.fxml
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

        // Set the fxml in Scene
        Scene scene = new Scene(root);

        // Search The Scene In Stage
        stage.setScene(scene);

        // Set the Title for Stage
        stage.setTitle("FLEX TRANSPORT");
        // Shows the Stage
        stage.show();
    }

    public static void main(String[] args) {
        // Launch The Start Method
        launch(args);

    }

}

