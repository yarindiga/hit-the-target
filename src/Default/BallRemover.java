package Default;
import Collidables.GameLevel;
import Interfaces.HitListener;
import ShownObject.Ball;
import ShownObject.Block;

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;

    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.remainingBall = removedBall;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(this.game);
        this.remainingBall.decrease(1);
    }
}
