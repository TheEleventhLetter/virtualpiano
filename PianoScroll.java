package virtualpiano;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PianoScroll {
    private NoteBlock[][] noteBlocks;
    private ReplayLine line;
    private Timeline timeline;
    public PianoScroll(Pane pianoPane){
        this.noteBlocks = new NoteBlock[16][32];
        this.setUpNoteBlocks(pianoPane);
        this.setUpTimeline();

    }
    private void setUpNoteBlocks(Pane pianoPane){
        for (int i = 0; i < 14; i++){
            for (int j = 0; j < 32; j++){
                this.noteBlocks[i][j] = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane);
            }
        }
    }
    public void playScroll(Pane pianoPane){
        this.line = new ReplayLine(pianoPane, this);
        this.line.startTimeline();
        this.timeline.play();
    }
    private void setUpTimeline(){
        KeyFrame kf1 = new KeyFrame(Duration.millis(1), (ActionEvent e) -> this.checkNotes());
        this.timeline = new Timeline(kf1);
        this.timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void stopScroll(Pane pianoPane){
        this.line.stopTimeline();
        this.timeline.stop();
        this.line.removeLine(pianoPane);
        this.resetNotes();
    }
    private void checkNotes(){
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 32; j++) {
                if (this.noteBlocks[i][j] != null) {
                    NoteBlock currentBlock = this.noteBlocks[i][j];
                    if (this.line.didCollide(currentBlock.getX(), currentBlock.getY(),
                            Constants.NOTE_BLOCK_WIDTH, Constants.NOTE_BLOCK_HEIGHT) && currentBlock.getIsSelected()
                            && !currentBlock.getWasPlayed()) {
                        currentBlock.playSound();
                        currentBlock.setWasPlayed();

                    }
                }
            }
        }
    }
    private void resetNotes(){
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 32; j++) {
                if (this.noteBlocks[i][j] != null) {
                    this.noteBlocks[i][j].reset();
                }
            }
        }
    }

}
