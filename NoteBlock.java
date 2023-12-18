package virtualpiano;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NoteBlock {
    private Rectangle noteBlock;
    private boolean noteSelected;
    private boolean wasPlayed;
    public NoteBlock(int X, int Y, Pane root){
        //AudioClip clip1 = new AudioClip("SUCKMYBALLS");
        this.noteSelected = false;
        this.wasPlayed = false;
        this.noteBlock = new Rectangle(X, Y, Constants.NOTE_BLOCK_WIDTH, Constants.NOTE_BLOCK_HEIGHT);
        this.noteBlock.setStroke(Color.BLACK);
        this.noteBlock.setStrokeWidth(2);
        this.noteBlock.setFill(Color.TRANSPARENT);
        root.getChildren().add(this.noteBlock);
        this.noteBlock.setOnMouseClicked((MouseEvent e) -> this.addNote());
    }
    private void addNote(){
        if (!this.noteSelected){
            this.noteBlock.setFill(Color.PINK);
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
        System.out.println("HIT");
    }
    public void reset(){
        this.wasPlayed = false;
    }
}
