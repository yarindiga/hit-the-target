package Default;
import Collidables.GameLevel;
import Interfaces.HitListener;
import ShownObject.Ball;
import ShownObject.Block;

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    public void hitEvent(Block beingHit, Ball hitter) {
       beingHit.removeHitListener(this);
       beingHit.removeFromGame(this.game);
       this.remainingBlocks.decrease(1);
    }
}
