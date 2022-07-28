package Interfaces;
import ShownObject.Ball;
import ShownObject.Block;

public interface HitListener {
    void hitEvent(Block beingHit, Ball hitter);
}