package virtualpiano;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Studio {
    private PianoScroll scroll;
    private int tempo;
    public Studio(Pane pianoPane, HBox buttonPane, HBox tempoPane){
        this.tempo = 40;
        this.scroll = new PianoScroll(pianoPane, this);
        this.setUpButtonPane(buttonPane, pianoPane, tempoPane);
    }
    private void setUpButtonPane(Pane buttonPane, Pane pianoPane, Pane tempoPane){
        Button quitButton = new Button("Quit", new ImageView(new Image("virtualpiano/quit_symbol.png",
                Constants.NOTE_BLOCK_HEIGHT, Constants.NOTE_BLOCK_HEIGHT, true, true)));
        quitButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 2);
        quitButton.setStyle("-fx-background-color: #ffffff");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        Button playButton = new Button("Play", new ImageView(new Image("virtualpiano/play_symbol.png",
                Constants.NOTE_BLOCK_HEIGHT, Constants.NOTE_BLOCK_HEIGHT, true, true)));
        playButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 2);
        playButton.setStyle("-fx-background-color: #ffffff");
        playButton.setOnAction((ActionEvent e) -> this.scroll.playScroll(pianoPane));
        Button stopButton = new Button("Stop", new ImageView(new Image("virtualpiano/pause_symbol.png",
                Constants.NOTE_BLOCK_HEIGHT, Constants.NOTE_BLOCK_HEIGHT, true, true)));
        stopButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 2);
        stopButton.setStyle("-fx-background-color: #ffffff");
        stopButton.setOnAction((ActionEvent e) -> this.scroll.stopScroll(pianoPane));
        Button restartButton = new Button("Restart", new ImageView(new Image("virtualpiano/replay_symbol.png",
                Constants.NOTE_BLOCK_HEIGHT, Constants.NOTE_BLOCK_HEIGHT, true, true)));
        restartButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 2);
        restartButton.setStyle("-fx-background-color: #ffffff");
        restartButton.setOnAction((ActionEvent e) -> this.scroll.restartNotes());
        Slider tempoSlider = new Slider();
        tempoSlider.setMin(40);  tempoSlider.setMax(240);   tempoSlider.setBlockIncrement(10);
        Label tempoLabel = new Label("Tempo");
        tempoLabel.setFont(new Font(20));
        tempoLabel.setTextFill(Color.BLACK);
        Label numberLabel = new Label(Integer.toString(this.tempo));
        tempoSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Studio.this.tempo = (int) Math.round(tempoSlider.getValue());
                numberLabel.setText(Integer.toString(Studio.this.tempo));
            }
        });

        buttonPane.getChildren().addAll(playButton, stopButton, restartButton, quitButton);
        tempoPane.getChildren().addAll(tempoLabel, tempoSlider, numberLabel);

    }
    public int getTempo(){
        return this.tempo;
    }
}
