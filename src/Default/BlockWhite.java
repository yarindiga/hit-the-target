package Default;
import Collidables.GameLevel;
import Interfaces.HitListener;
import ShownObject.Ball;
import ShownObject.Block;
import java.awt.*;

public class BlockWhite implements HitListener {
    private GameLevel game;

    public BlockWhite(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(Color.WHITE);
        hitter.setVelocity(30, -2);
    }
}
