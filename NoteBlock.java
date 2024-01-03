package virtualpiano;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.HashMap;

public class NoteBlock {
    private Rectangle noteBlock;
    private boolean noteSelected;
    private boolean wasPlayed;
    private HashMap<String, String> noteSound;
    private String myType;
    public NoteBlock(int X, int Y, Pane root, String noteType){
        this.myType = noteType;
        this.setUpHashMap();
        this.noteSelected = false;
        this.wasPlayed = false;
        this.noteBlock = new Rectangle(X, Y, Constants.NOTE_BLOCK_WIDTH, Constants.NOTE_BLOCK_HEIGHT);
        this.noteBlock.setStroke(Color.BLACK);
        this.noteBlock.setStrokeWidth(2);
        this.noteBlock.setFill(Color.TRANSPARENT);
        root.getChildren().add(this.noteBlock);
        this.noteBlock.setOnMouseClicked((MouseEvent e) -> this.addNote(Color.PINK));

    }
    private void setUpHashMap(){
        this.noteSound = new HashMap<String, String>();
        this.noteSound.put("B4", "virtualpiano/B4_Sample.wav");
        this.noteSound.put("A4", "virtualpiano/A4_Sample.wav");
        this.noteSound.put("G4", "virtualpiano/G4_Sample.wav");
        this.noteSound.put("F4", "virtualpiano/F4_Sample.wav");
        this.noteSound.put("E4", "virtualpiano/E4_Sample.wav");
        this.noteSound.put("D4", "virtualpiano/D4_Sample.wav");
        this.noteSound.put("C4", "virtualpiano/C4_Sample.wav");
        this.noteSound.put("B3", "virtualpiano/B3_Sample.wav");
        this.noteSound.put("A3", "virtualpiano/A3_Sample.wav");
        this.noteSound.put("G3", "virtualpiano/G3_Sample.wav");
        this.noteSound.put("F3", "virtualpiano/F3_Sample.wav");
        this.noteSound.put("E3", "virtualpiano/E3_Sample.wav");
        this.noteSound.put("D3", "virtualpiano/D3_Sample.wav");
        this.noteSound.put("C3", "virtualpiano/C3_Sample.wav");
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
    public void playSound(){
        URL resource = this.getClass().getResource(this.noteSound.get(this.myType));
        if (resource != null){
            AudioClip clip = new AudioClip(resource.toString());
            clip.play();
            System.out.println("HIT");
        }
    }
    public void reset(){
        this.wasPlayed = false;
    }
}
