package virtualpiano;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;

public class NoteBlock {
    private Rectangle noteBlock;
    private boolean noteSelected;
    private boolean wasPlayed;
    private HashMap<String, String> noteSound;
    private String myType;
    public NoteBlock(int X, int Y, Pane root, String noteType, Color color){
        this.myType = noteType;
        this.setUpHashMap();
        this.noteSelected = false;
        this.wasPlayed = false;
        this.noteBlock = new Rectangle(X, Y, Constants.NOTE_BLOCK_WIDTH, Constants.NOTE_BLOCK_HEIGHT);
        this.noteBlock.setStroke(Color.LIGHTBLUE);
        this.noteBlock.setStrokeWidth(2);
        this.noteBlock.setFill(Color.TRANSPARENT);
        root.getChildren().add(this.noteBlock);
        this.noteBlock.setOnMouseClicked((MouseEvent e) -> this.addNote(color));

    }
    private void setUpHashMap(){
        this.noteSound = new HashMap<String, String>();
        this.noteSound.put("B4", "src/virtualpiano/B4_Sample.wav");
        this.noteSound.put("A4", "src/virtualpiano/A4_Sample.wav");
        this.noteSound.put("G4", "src/virtualpiano/G4_Sample.wav");
        this.noteSound.put("F4", "src/virtualpiano/F4_Sample.wav");
        this.noteSound.put("E4", "src/virtualpiano/E4_Sample.wav");
        this.noteSound.put("D4", "src/virtualpiano/D4_Sample.wav");
        this.noteSound.put("C4", "src/virtualpiano/C4_Sample.wav");
        this.noteSound.put("B3", "src/virtualpiano/B3_Sample.wav");
        this.noteSound.put("A3", "src/virtualpiano/A3_Sample.wav");
        this.noteSound.put("G3", "src/virtualpiano/G3_Sample.wav");
        this.noteSound.put("F3", "src/virtualpiano/F3_Sample.wav");
        this.noteSound.put("E3", "src/virtualpiano/E3_Sample.wav");
        this.noteSound.put("D3", "src/virtualpiano/D3_Sample.wav");
        this.noteSound.put("C3", "src/virtualpiano/C3_Sample.wav");
        this.noteSound.put("SNARE", "src/virtualpiano/SnareHit_Sample.wav");
        this.noteSound.put("BASS", "src/virtualpiano/BassHit_Sample.wav");
    }
    private void addNote(Color color){
        if (!this.noteSelected){
            this.noteBlock.setFill(color);
            this.noteSelected = true;
        } else {
            this.noteBlock.setFill(Color.TRANSPARENT);
            this.noteSelected = false;
        }
    }
    public int getX(){
        return (int) this.noteBlock.getX();
    }
    public int getY(){
        return (int) this.noteBlock.getY();
    }
    public boolean getIsSelected(){
        return this.noteSelected;
    }
    public boolean getWasPlayed(){
        return this.wasPlayed;
    }
    public void setWasPlayed(){
        this.wasPlayed = true;
    }
    public void playSound() {
        Media m = new Media(Paths.get(this.noteSound.get(this.myType)).toUri().toString());
        new MediaPlayer(m).play();
        System.out.println("HIT");
    }
    public void reset(){
        this.wasPlayed = false;
    }
    public void clear(){
        this.noteBlock.setFill(Color.TRANSPARENT);
        this.noteSelected = false;
    }
}
