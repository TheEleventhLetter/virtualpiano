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
                NoteBlock newNoteBlock = null;
                switch (i){
                    case 0:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "B4");
                        break;
                    case 1:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "A4");
                        break;
                    case 2:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "G4");
                        break;
                    case 3:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "F4");
                        break;
                    case 4:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "E4");
                        break;
                    case 5:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "D4");
                        break;
                    case 6:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "C4");
                        break;
                    case 7:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "B3");
                        break;
                    case 8:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "A3");
                        break;
                    case 9:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "G3");
                        break;
                    case 10:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "F3");
                        break;
                    case 11:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "E3");
                        break;
                    case 12:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "D3");
                        break;
                    case 13:
                        newNoteBlock = new NoteBlock(j * Constants.NOTE_BLOCK_WIDTH, i * Constants.NOTE_BLOCK_HEIGHT, pianoPane, "C3");
                        break;
                }
                this.noteBlocks[i][j] = newNoteBlock;
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
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 32; j++) {
                this.noteBlocks[i][j].reset();
            }

        }
    }

}
