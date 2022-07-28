/**
 * ass5
 * name: bar balanga
 * ID: 322818543
 */
package ShownObject;
import Default.Counter;
import biuoop.DrawSurface;
import Collidables.*;
import java.awt.*;

/**
 * class ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter CountScore;

    /**
     * @param c Counter.
     * constructor.
     */
    public ScoreIndicator(Counter c) {
        this.CountScore = c;
    }

    /**
     * @param num int.
     * set the score.
     */
    public void setCountScore(int num) {
        this.CountScore.increase(num);
    }

    /**
     * @param d DrawSurface.
     * draw the score.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(360, 16, "Score: " + this.CountScore.getValue(), 16);
    }

    /**
     */
    @Override
    public void timePassed() {
    }

    /**
     * @param g game.
     * add the score to the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
