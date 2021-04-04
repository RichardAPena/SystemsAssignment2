package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

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
    private final static String HOST = "localhost";
    private final static int PORT = 4999;
    public int index1;
    public int index2;
    public String selectedItem1;
    public String selectedItem2;
    public File clientDir = new File("shared");
    public static File serverDir = new File("C:\\Downloads\\SERVER");
    public TextArea preview1 = new TextArea();
    public TextArea preview2 = new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Socket s = new Socket("localhost", PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("DIR");
        String[] sArr = in.readLine().split(":");

        preview1.setEditable(false);
        preview2.setEditable(false);
        Button downloadButton = new Button("Download");
        Button uploadButton = new Button("Upload");
        ListView<String> list1 = new ListView<>();
        ListView<String> list2 = new ListView<>();
        list1.setPrefWidth(300);
        list2.setPrefWidth(300);
        Label label1 = new Label("Selected client side item:");
        Label label2 = new Label("Selected server side item:");
        HBox hbox1 = new HBox(downloadButton,uploadButton);
        HBox hbox2 = new HBox(list1,list2,preview1,preview2);
        VBox vbox = new VBox(hbox1,hbox2,label1,label2);



        ObservableList<String> items1 = FXCollections.observableArrayList (clientDir.list());
        ObservableList<String> items2 = FXCollections.observableArrayList (sArr);
        list1.setItems(items1);
        list2.setItems(items2);
        list1.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
            selectedItem1 = list1.getSelectionModel().getSelectedItem();
            index2 = list1.getSelectionModel().getSelectedIndex();
            label1.setText("Selected item: " + selectedItem1);
            System.out.println("Item selected : " + selectedItem1 + ", Item index : " + index1);
            preview1.appendText("Client side file preview:");
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("shared\\"+selectedItem1)))) {

                String line;
                while ((line = reader.readLine()) != null)
                    preview1.appendText("\n"+line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        list2.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String oldVal, String newVal) -> {
            selectedItem2 = list2.getSelectionModel().getSelectedItem();
            index1 = list2.getSelectionModel().getSelectedIndex();
            label2.setText("Selected client side item: " + selectedItem2);
            System.out.println("Item server side selected : " + selectedItem2 + ", Item index : " + index2);
            preview2.setText("");
            preview2.setText("Server side file preview:");
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Downloads\\SERVER\\"+selectedItem2)))) {

                String line;
                while ((line = reader.readLine()) != null)
                    preview2.appendText("\n" + line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        downloadButton.setOnAction(e -> {


        });
        uploadButton.setOnAction(e -> {


        });



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assignment 2");
        primaryStage.setScene(new Scene(vbox, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
