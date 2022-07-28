/**
 * ass5
 * name: bar balanga
 * ID: 322818543
 */
package ShownObject;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import Shapes.Rectangle;
import Shapes.Point;
import Collidables.*;

/**
 * class paddle.
 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor key;
    private Rectangle rectangle;
    private  Color color = Color.black;
    /**
     * constructor.
     * @param key biuoop.KeyboardSensor.
     * @param r Rectangle.
     */
    public Paddle(biuoop.KeyboardSensor key, Rectangle r) {
        this.rectangle = r;
        this.key = key;
    }
    /**
     * move the paddle Left.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 20) {
            this.rectangle.setRec(this.rectangle.getUpperLeft(), -5.0);
        }
    }
    /**
     * move the paddle Right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < 780) {
            this.rectangle.setRec(this.rectangle.getUpperLeft(), 5.0);
        }
    }
    /**
     * @param c is the surface of the ball.
     * this set the color.
     */
    public void setColor(Color c) {
        this.color = c;
    }
    /**
     * this check if the user press the key and move the paddle accordingly.
     */
    @Override
    public void timePassed() {
        if (this.key.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        if (this.key.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }
    /**
     * @param d is the surface.
     * it is drow the paddle.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        //set the color of the paddle.
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
    }

     /**
      * @return rectangle Rectangle.
      */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * @param collisionPoint point.
     * @param currentVelocity velocity.
     */
    @Override
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        //if there is no hit the velocityof the ball is not changing.
        if (collisionPoint == null) {
            return currentVelocity;
        }
        Velocity v = currentVelocity;
        double d = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + 200 - collisionPoint.getX();
        //check if the collision is on part 1.
        if (d > 240) {
            return Velocity.fromAngleAndSpeed(300, 6);
        }
        //check if the collision is on part 2.
        if (d < 241 && d > 180) {
            return Velocity.fromAngleAndSpeed(330, 3);
        }
        //check if the collision is on part 3.
        if (d < 181 && d > 120) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        //check if the collision is on part 4.
        if (d < 121 && d > 60) {
            return Velocity.fromAngleAndSpeed(30, 5);
        }
        if (d <= 60) {
            return Velocity.fromAngleAndSpeed(120, 2);
        }
        v = new Velocity(-v.getDx(), v.getDy());
        return v;
    }
     /**
      *  Add paddle to the game.
      * @param g game.
      */
     public void addToGame(GameLevel g) {
         g.addSprite(this);
         g.addCollidable(this);
     }
}
