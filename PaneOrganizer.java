package virtualpiano;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private BorderPane root;

    public PaneOrganizer(){
        this.root = new BorderPane();
        Pane pianoPane = new Pane();
        HBox buttonPane = new HBox(10);
        HBox tempoPane = new HBox(10);
        buttonPane.setPrefSize(Constants.SCENE_WIDTH, Constants.NOTE_BLOCK_HEIGHT * 3);
        buttonPane.setStyle("-fx-background-color: #34dbeb");
        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        tempoPane.setPrefSize(Constants.SCENE_WIDTH, Constants.NOTE_BLOCK_HEIGHT * 3);
        tempoPane.setStyle("-fx-background-color: #34dbeb");
        tempoPane.setPadding(new Insets(10, 10, 10, 10));
        this.root.setCenter(pianoPane);
        this.root.setTop(buttonPane);
        this.root.setBottom(tempoPane);
        new Studio(pianoPane, buttonPane, tempoPane);
    }
    public Pane getRoot(){
        return this.root;
    }
}
