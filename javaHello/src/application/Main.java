package application;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Main extends Application{
    public void start(Stage primaryStage){
        Group root =new Group();
        Text text=new Text();
        text.setText("welcome");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Times New Roman",60));
        text.setFill(Color.RED);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(0.5);
        root.getChildren().add(text);
        Scene scene =new Scene(root,800,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("graphics");
    }
    public  static void main(String []args){
        launch(args);
    }
}
        