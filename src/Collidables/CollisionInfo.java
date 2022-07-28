package Collidables;
import Shapes.*;
import ShownObject.*;
public class CollisionInfo {
    private Point point;
    private Collidable collidable;

    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    public Point collisionPoint() {
        return this.point;
    }

    public Collidable collisionObject() {
        return this.collidable;
    }
}