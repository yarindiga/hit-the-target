package Default;

import Interfaces.HitListener;
import ShownObject.Ball;
import ShownObject.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}