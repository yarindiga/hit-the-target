package Collidables;
import Shapes.*;
import ShownObject.*;
public class Velocity {
    private double dx;
    private double dy;
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Point applyToPoint(Point p) {
        double x = p.getX() + this.getDx();
        double y = p.getY() + this.getDy();
        Point P = new Point(x, y);
        return P;
    }
    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double x) {
        this.dx = x;
    }

    public void setDy(double y) {
        this.dy = y;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
       double dx =  speed * Math.sin(Math.toRadians(angle));
        double dy =  (-1) * speed *  Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}

