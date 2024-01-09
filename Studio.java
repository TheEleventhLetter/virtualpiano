package virtualpiano;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Studio {
    private PianoScroll scroll;
    private int tempo;
    public Studio(Pane pianoPane, HBox buttonPane){
        this.tempo = 40;
        this.scroll = new PianoScroll(pianoPane, this);
        this.setUpButtonPane(buttonPane, pianoPane);
    }
    private void setUpButtonPane(Pane buttonPane, Pane pianoPane){
        Button quitButton = new Button("Quit");
        quitButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 3);
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        Button playButton = new Button("Play");
        playButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 3);
        playButton.setOnAction((ActionEvent e) -> this.scroll.playScroll(pianoPane));
        Button stopButton = new Button("Stop");
        stopButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 3);
        stopButton.setOnAction((ActionEvent e) -> this.scroll.stopScroll(pianoPane));
        Button restartButton = new Button("Restart");
        restartButton.setPrefHeight(Constants.NOTE_BLOCK_HEIGHT * 3);
        restartButton.setOnAction((ActionEvent e) -> this.scroll.restartNotes());
        Slider tempoSlider = new Slider();
        tempoSlider.setMin(40);  tempoSlider.setMax(240);   tempoSlider.setBlockIncrement(10);
        Label tempoLabel = new Label("Tempo: " + this.tempo);
        tempoLabel.setFont(new Font(20));
        tempoLabel.setTextFill(Color.WHITE);
        tempoSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Studio.this.tempo = (int) Math.round(tempoSlider.getValue());
                tempoLabel.setText("Tempo: " + Studio.this.tempo);
            }
        });

        buttonPane.getChildren().addAll(playButton, stopButton, restartButton, tempoLabel, tempoSlider, quitButton);

    }
    public int getTempo(){
        return this.tempo;
    }
}
