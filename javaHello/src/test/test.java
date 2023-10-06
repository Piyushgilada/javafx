package test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class test extends Application{
    public static void main(String [] args){
        launch(args);
}
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button=new Button("say hello world");
        StackPane root=new StackPane(button);
        Scene scene=new Scene(root,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0){
                System.out.println("hello world");
            }
        });
        
    } 
}