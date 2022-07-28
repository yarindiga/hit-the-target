/**
 * ass5
 * name: bar balanga
 * ID: 322818543
 */
package ShownObject;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Shapes.Rectangle;
import Shapes.Point;
import Collidables.*;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
/**
 * class block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle r;
    private  Color color;
    private  static final double MIN = 0.000000001;
    private List<HitListener> hitListeners;
    /**
     *  constructor - create a block.
     * @param rec rectangle.
     */
    public Block(Rectangle rec) {
        this.r = rec;
        this.color = Color.black;
        this.hitListeners = new ArrayList<>();
    }

    /**
     *  constructor - create a block.
     * @param rec rectangle.
     * @param c color.
     */
    public Block(Rectangle rec, Color c) {
        this.r = rec;
        this.color = c;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * @return r Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.r;
    }
    /**
     * check where the hit and change the velocity accordingly.
     * @param collisionPoint is the collision point.
     * @param  currentVelocity is the current Velocity.
     * @return  the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }
        double x = currentVelocity.getDx();
        double y = currentVelocity.getDy();
        Velocity v = new Velocity(x, y);
        this.notifyHit(hitter);
        //check if the collision is on the upper line of the rectangle and change the velocity accordingly.
        if (Math.abs(collisionPoint.getY() - this.r.getUpperLeft().getY()) < MIN) {
            v = new Velocity(x, -y);
            return v;
        }
        //check if the collision is in the line down of the rectangle and change the velocity accordingly.
        if (Math.abs(collisionPoint.getY() - this.r.getDownLeft().getY()) < MIN) {
            v = new Velocity(x, -y);
            return v;
        }
        //check if the collision is on the left line of the rectangle and change the velocity accordingly.
        if (Math.abs(collisionPoint.getX() - this.r.getUpperLeft().getX()) < MIN) {
            v = new Velocity(-x, y);
            return v;
        }
        //check if the collision is on the right line of the rectangle and change the velocity accordingly.
        if (Math.abs(collisionPoint.getX() - this.r.getUpperRight().getX()) < MIN) {
            v = new Velocity(-x, y);
            return v;
        }
        return v;
    }
    /**
     * it draw the rectangle on the DrawSurface.
     * @param surface DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.r.getUpperLeft().getX(), (int) this.r.getUpperLeft().getY(),
                (int) this.r.getWidth(), (int) this.r.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.r.getUpperLeft().getX(), (int) this.r.getUpperLeft().getY(),
                (int) this.r.getWidth(), (int) this.r.getHeight());
    }
    /**
     * @param c color and set it.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void timePassed() {
    }
    /**
     * Add the block to the game.
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @param game Game.
     * remove from the game the sprite and the collidable.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * @param hl HitListener.
     * add the HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl HitListener.
     * remove the HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter ball.
     * notify the Hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}
