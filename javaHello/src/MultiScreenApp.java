import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiScreenApp extends Application {

    private Stage primaryStage;
    private StackPane loginPane;
    private VBox dashboardPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Multi-Screen App");

        createLoginScreen();
        createDashboardScreen();
        showLoginScreen();
    }

    private void createLoginScreen() {
        loginPane = new StackPane();
        loginPane.setPrefSize(400, 300);

        Label titleLabel = new Label("Login");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            // Add authentication logic here
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authenticate(username, password)) {
                showDashboardScreen();
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        });
        VBox loginLayout = new VBox(20);
        loginLayout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton);
        loginLayout.setStyle("-fx-background-color: lightblue; -fx-padding: 20px;");
        loginPane.getChildren().add(loginLayout);
    }

    private void createDashboardScreen() {
        dashboardPane = new VBox(20);
        dashboardPane.setPrefSize(400, 300);
        Label dashboardLabel = new Label("Welcome to the Dashboard!");
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> showLoginScreen());
        dashboardPane.getChildren().addAll(dashboardLabel, logoutButton);
        dashboardPane.setStyle("-fx-background-color: lightgreen; -fx-padding: 20px;");
    }

    private boolean authenticate(String username, String password) {
        // Replace this with your actual authentication logic
        return username.equals("admin") && password.equals("password");
    }

    private void showLoginScreen() {
        primaryStage.setScene(new Scene(loginPane));
    }

    private void showDashboardScreen() {
        primaryStage.setScene(new Scene(dashboardPane));
    }
}
