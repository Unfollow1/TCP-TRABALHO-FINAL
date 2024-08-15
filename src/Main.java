import Constants.ConstraintsConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializePrimaryStage(primaryStage);
        primaryStage.show();
    }

    private void initializePrimaryStage(Stage primaryStage) throws Exception {
        Parent root = loadFXML();
        primaryStage.setTitle(ConstraintsConstants.USER_INTERFACE_WINDOW_NAME);
        primaryStage.setScene(createScene(root));
    }

    private Parent loadFXML() throws Exception {
        return FXMLLoader.load(getClass().getResource(ConstraintsConstants.USER_INTERFACE_PATH));
    }

    private Scene createScene(Parent root) {
        return new Scene(root, ConstraintsConstants.WINDOW_WIDTH, ConstraintsConstants.WINDOW_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}