package Trackme;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Jorda on 10/12/2016.
 */
public class InputController implements Initializable
{
    @FXML AnchorPane mainPane;
    @FXML CheckBox morning, afternoon, evening;
    @FXML Button submit;
    @FXML TextField hour1, hour2, hour3, min1, min2, min3, ampm1, ampm2, ampm3;

    //Method getChoice to get the time the user specifies
    private String getChoice(TextField hour, TextField minn, TextField ampm)
    {
        String h = hour.getText();
        String m = minn.getText();
        String ap = ampm.getText();
        String time = h + ":" + m + ap;

        //Check to make sure user specifies a correct hour, minute, and AM/PM
        if(Integer.parseInt(h) > 12 || Integer.parseInt(h) < 0)
        {
            error();
            System.out.println("Don't do that!");
        }
        if(Integer.parseInt(m) > 59 || Integer.parseInt(m) < 0)
        {
            error();
            System.out.println("Don't do that!");
        }
        if(!ap.equals("AM") && !ap.equals("PM"))
        {
            error();
            System.out.println("Don't do that!");
        }
        return time;
    }

    //Handle specific notification boxes
    private void handleSubmit(CheckBox morning, CheckBox afternoon, CheckBox evening) throws IOException {
        //String message = "Selected:\n";
        System.out.println("Selected:\n");

        if(morning.isSelected())
        {
            //message += "morning\n";
            String time1 = getChoice(hour1, min1, ampm1);
            System.out.println("Morning " + time1);
        }

        if(afternoon.isSelected())
        {
           // message += "afternoon\n";
            String time2 = getChoice(hour2, min2, ampm2);
            System.out.println("Afternoon " + time2);
        }

        if(evening.isSelected())
        {
            //message += "evening\n";
            String time3 = getChoice(hour3, min3, ampm3);
            System.out.println("Evening " + time3);
        }
    }

    //Method to show error
    private void error()
    {
        Label error = new Label("[ERROR] Hour: 0-12, Minute: 0-59, AM/PM: AM or PM!");
        error.setStyle("-fx-text-fill: red;");
        error.setTranslateY(320);
        error.setTranslateX(225);
        mainPane.getChildren().add(error);
        FadeTransition fade = new FadeTransition(Duration.seconds(8), error);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    //Timer method
    private void timer() throws IOException {
        System.out.println("Inside Timer");
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("h:ma");
        if(ft.format(date).equals(getChoice(hour1, min1, ampm1)) || ft.format(date).equals(getChoice(hour2, min2, ampm2)) || ft.format(date).equals(getChoice(hour3, min3, ampm3)))
        {
            Stage stage = new Stage();
            stage.setTitle("Reminder");
            Parent root = FXMLLoader.load(getClass().getResource("notification.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        submit.setOnAction(e -> {
            try {
                handleSubmit(morning, afternoon, evening);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.minutes(1),
                ae -> {
                    try {
                        System.out.println("You are in Try\n");
                        timer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }
}
