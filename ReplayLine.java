package virtualpiano;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ReplayLine {
    private Line line;
    private Timeline timeline;
    private PianoScroll scroll;
    public ReplayLine(Pane pianoPane, PianoScroll myScroll){
        this.line = new Line(0, 0, 0, Constants.SCENE_HEIGHT);
        this.line.setStroke(Color.RED);
        this.line.setStrokeWidth(4);
        pianoPane.getChildren().add(this.line);
        this.setUpTimeLine(pianoPane);
        this.scroll = myScroll;
    }
    public void removeLine(Pane pianoPane){
        pianoPane.getChildren().remove(this.line);
    }
    private void setUpTimeLine(Pane pianoPane){
        KeyFrame kf1 = new KeyFrame(Duration.millis(20), (ActionEvent e) -> this.moveLine(pianoPane));
        this.timeline = new Timeline(kf1);
        this.timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void startTimeline(){
        this.timeline.play();
    }
    public void stopTimeline(){
        this.timeline.stop();
    }
    private void moveLine(Pane pianoPane){
        this.line.setStartX(this.line.getStartX() + 2);
        this.line.setEndX(this.line.getEndX() + 2);
        if (this.line.getStartX() > Constants.SCENE_WIDTH){
            this.stopTimeline();
            this.scroll.stopScroll(pianoPane);
        }
    }
    public boolean didCollide(int X, int Y, int width, int height){
        boolean didCollide = false;
        if (this.line.intersects(X, Y, width, height)){
            didCollide = true;
        }
        return didCollide;
    }


}
