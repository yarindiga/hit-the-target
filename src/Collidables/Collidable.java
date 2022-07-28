
package Collidables;

import Shapes.*;
import ShownObject.*;
public interface Collidable {
    Rectangle getCollisionRectangle();
    Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity);
}
