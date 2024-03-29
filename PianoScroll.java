package virtualpiano;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PianoScroll {
    private NoteBlock[][] noteBlocks;
    private ReplayLine line;
    private Timeline timeline;
    private boolean isPlaying;
    private Studio myStudio;
    public PianoScroll(Pane pianoPane, Studio studio){
        this.isPlaying = false;
        this.noteBlocks = new NoteBlock[16][32];
        this.setUpNoteBlocks(pianoPane);
        this.setUpTimeline();
        this.myStudio = studio;
    }
    private void setUpNoteBlocks(Pane pianoPane){
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 32; j++){
                NoteBlock newNoteBlock = null;
                switch (i){
                    case 0:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "B4", Color.PINK);
                        break;
                    case 1:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "A4", Color.VIOLET);
                        break;
                    case 2:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "G4", Color.DARKSEAGREEN);
                        break;
                    case 3:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "F4", Color.LAWNGREEN);
                        break;
                    case 4:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "E4", Color.YELLOW);
                        break;
                    case 5:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "D4", Color.ORANGE);
                        break;
                    case 6:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "C4", Color.RED);
                        break;
                    case 7:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "B3", Color.PINK);
                        break;
                    case 8:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "A3", Color.VIOLET);
                        break;
                    case 9:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "G3", Color.DARKSEAGREEN);
                        break;
                    case 10:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "F3", Color.LAWNGREEN);
                        break;
                    case 11:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "E3", Color.YELLOW);
                        break;
                    case 12:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "D3", Color.ORANGE);
                        break;
                    case 13:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "C3", Color.RED);
                        break;
                    case 14:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "SNARE", Color.GRAY);
                        break;
                    case 15:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "BASS", Color.DARKGRAY);
                        break;
                }
                this.noteBlocks[i][j] = newNoteBlock;
            }
        }
    }
    public void playScroll(Pane pianoPane){
        if (!this.isPlaying){
            this.line = new ReplayLine(pianoPane, this, this.myStudio.getTempo());
            this.line.startTimeline();
            this.timeline.play();
            this.isPlaying = true;
        } else {
            this.stopScroll(pianoPane);
            this.line = new ReplayLine(pianoPane, this, this.myStudio.getTempo());
            this.line.startTimeline();
            this.timeline.play();
        }
    }
    private void setUpTimeline(){
        KeyFrame kf1 = new KeyFrame(Duration.millis(1), (ActionEvent e) -> this.checkNotes());
        this.timeline = new Timeline(kf1);
        this.timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void stopScroll(Pane pianoPane){
        if (this.line != null){
            this.line.stopTimeline();
            this.timeline.stop();
            this.line.removeLine(pianoPane);
            this.resetNotes();
        }
    }
    private void checkNotes(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
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
    private void resetNotes(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                this.noteBlocks[i][j].reset();
            }

        }
    }
    public void setIsPlaying(){
        this.isPlaying = false;
    }
    public void restartNotes(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                this.noteBlocks[i][j].clear();
            }
        }
        this.resetNotes();
    }

}
