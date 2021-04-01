package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    Server
    The server does not need to have any user interface,but it must be multi-threaded. Each incoming client connection
    should be handled with a separate thread (ClientConnectionHandler). This thread,and its corresponding socket, will
    remain open only until the command has been handled.

    Client
    The client will have a simple user interface. When the
    client is started, the computer name and shared folder path are passed as command-line arguments.The client will
    then show a split screen showing two lists. Both lists will consist of filenames. On the left will be the list of
    all files in the shared folder of the local client. On the right will be the list of files in the shared folder of
    the server.
    */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
