/**
 * ass5
 * name: bar balanga
 * ID: 322818543
 */
package ShownObject;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Shapes.Point;
import biuoop.DrawSurface;
import Shapes.*;
import Collidables.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
* class ball.
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private final int size;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment gameEnvironment = new GameEnvironment();
    private List<HitListener> hitListeners;
    // constructor

    /**
     * constructor of the ball.
     * @param p is the center point of the ball.
     * @param size  is the size of the ball .
     * @param color is the color of the ball .
     */
    public Ball(Point p, int size, java.awt.Color color) {
        this.center = p;
        this.color = color;
        this.size = size;
        this.vel = new Velocity(1, 1);
        hitListeners = new ArrayList<>();
    }

    /**
     * set new velocity to the ball.
     * @param dx double.
     * @param dy double.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.vel = v;
    }

    /**
     * constructor of the ball.
     * @param x is x point of the ball.
     * @param y is y point of the ball.
     * @param size is the size of the ball.
     * @param color is the color of the ball.
     * @param v is the velocity.
     */
    public Ball(int x, int y, int size, java.awt.Color color, Velocity v) {
        this.center = new Point(x, y);
        this.color = color;
        this.size = size;
        this.vel = v;
    }

    /**
     * @return the x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface is the surface of the ball .
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Ball.this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * @param g game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * @param v is the Velocity of the ball and change it.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * set color.
     * @param c is the color.
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * set a new center point and changes the velocity according to the collisions.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo cf = this.gameEnvironment.getClosestCollision(trajectory);
        // if there is no hit the velocity is not change.
        if (cf == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        double x = this.center.getX();
        double y = this.center.getY();
        // if there is a hit we change the velocity accordingly.
        if (this.vel.getDy() > 0) {
            this.center = new Point(x, y - 1);
        }
        // if there is a hit we change the velocity accordingly.
        if (this.vel.getDy() < 0) {
            this.center = new Point(x, y + 1);
        }
        // if there is a hit we change the velocity accordingly.
        if (this.vel.getDx() > 0) {
            this.center = new Point(x - 1, y);
        }
        // if there is a hit we change the velocity accordingly.
        if (this.vel.getDx() < 0) {
            this.center = new Point(x + 1, y);
        }
        this.setVelocity(cf.collisionObject().hit(this, cf.collisionPoint(), this.getVelocity()));
    }

    /**
     * move the ball one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add the ball to the game.
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param game game.
     * remove the Sprite from the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
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
    private void notifyHit(Block hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(hitter, this);
        }
    }
}
