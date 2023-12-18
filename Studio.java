package virtualpiano;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Studio {
    private PianoScroll scroll;
    public Studio(Pane pianoPane, HBox buttonPane){
        this.scroll = new PianoScroll(pianoPane);
        this.setUpButtonPane(buttonPane, pianoPane);
    }
    private void setUpButtonPane(Pane buttonPane, Pane pianoPane){
        Button quitButton = new Button("Quit");
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        quitButton.setFocusTraversable(false);
        Button playButton = new Button("Play");
        playButton.setOnAction((ActionEvent e) -> this.scroll.playScroll(pianoPane));
        Button stopButton = new Button("Stop");
        stopButton.setOnAction((ActionEvent e) -> this.scroll.stopScroll(pianoPane));
        buttonPane.getChildren().addAll(playButton, stopButton, quitButton);

    }
}
