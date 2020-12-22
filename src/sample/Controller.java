package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Get all element from fxml file
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> eventList;

    @FXML
    Label timeLabel;
    @FXML
    Label dateTodayLabel;

    @FXML
    WebView webView;
    @FXML
    Hyperlink hlBackward;
    @FXML
    Hyperlink hlForward;
    @FXML
    Hyperlink hlReload;
    @FXML
    Hyperlink hlHome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Set the default value of date picker to today
        datePicker.setValue(LocalDate.now());

        //Set the current time
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText("Time now: " + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        //Set day of the date
        Format dateFormat = new SimpleDateFormat("EEEEEEEEE");
        String today = dateFormat.format(new Date());
        dateTodayLabel.setText("Today is "+today);

        //Setup the mini website and its hyperlink action
        WebEngine webEngine = webView.getEngine();
        webView.setZoom(0.75);
        webEngine.load("https://www.google.com");
        hlReload.setOnAction(e -> webEngine.reload());
        WebHistory webHistory = webEngine.getHistory();
        hlHome.setOnAction(e -> webEngine.load("https://www.google.com"));
        hlForward.setOnAction(e -> {
            try{
                webHistory.go(1);
            }catch(Exception ignored){}});
        hlBackward.setOnAction(e -> {
            try{
                webHistory.go(-1);
            }catch(Exception ignored){}});
    }
    //Make a arraylist to store event
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    //Funtion of the button name "Add" that add an event into the list
    @FXML
    private void addEvent() {
        list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
        eventList.setItems(list);
        refresh();
    }

    //Funciton of the button name "Delete" that remove the last event in the list
    @FXML
    private void deleteEvent() {
        int index = list.size() - 1;
        try {
            list.remove(index);
        }
        catch (Exception ignored) {
        }
    }

    //Function that refresh the value of date picker and description text field after add event
    private void refresh() {
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);
    }
}