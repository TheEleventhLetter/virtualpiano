package virtualpiano;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
    private BorderPane root;

    public PaneOrganizer(){
        this.root = new BorderPane();
        Pane pianoPane = new Pane();
        HBox buttonPane = new HBox();
        buttonPane.setPrefSize(Constants.SCENE_WIDTH, Constants.NOTE_BLOCK_HEIGHT * 3);
        buttonPane.setStyle("-fx-background-color: #faeb78");
        this.root.setCenter(pianoPane);
        this.root.setTop(buttonPane);
        new Studio(pianoPane, buttonPane);
    }
    public Pane getRoot(){
        return this.root;
    }
}
