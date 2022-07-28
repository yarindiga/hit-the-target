package Collidables;
import Interfaces.HitListener;
import ShownObject.Ball;
import ShownObject.Block;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}