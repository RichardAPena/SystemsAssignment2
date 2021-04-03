package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

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

        Button downloadButton = new Button("Download");
        Button uploadButton = new Button("Upload");
        ListView<String> list1 = new ListView<>();
        ListView<String> list2 = new ListView<>();

        HBox hbox1 = new HBox(downloadButton,uploadButton);
        HBox hbox2 = new HBox(list1,list2);
        VBox vbox = new VBox(hbox1,hbox2);
        File clientDir = new File("shared");
        File serverDir = new File("C:\\Downloads\\SERVER");
        ObservableList<String> items1 = FXCollections.observableArrayList (clientDir.list());
        ObservableList<String> items2 = FXCollections.observableArrayList (serverDir.list());
        list1.setItems(items1);
        list2.setItems(items2);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(new Scene(vbox, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
