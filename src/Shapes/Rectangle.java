package Shapes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private final double width;
    private final double height;
    private Point upperLeft;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;
    private Color c;

    public Rectangle(Point upperLeft, double width, double height) {
        this.c = Color.black;
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        this.downLeft = new Point(x, y + height);
        this.upperRight = new Point(x + width, y);
        this.downRight = new Point(x + width, y + height);
    }
    public List<Point> intersectionPoints(Line line) {
        List<Point> l = new ArrayList<Point>();
        double x = this.getUpperLeft().getX();
        double y = this.getUpperLeft().getY();
        Point p = new Point(x, y + getHeight());
        Line l1 = new Line(getUpperLeft(), p);
        if (l1.intersectionWith(line) != null) {
            l.add(l1.intersectionWith(line));
        }
        p = new Point(x + getWidth(), y);
        //Creates a line of the rectangle.
        l1 = new Line(getUpperLeft(), p);
        //Checks if there is a collision between the 2 lines and if so puts the collision point in the array.
        if (l1.intersectionWith(line) != null) {
            l.add(l1.intersectionWith(line));
        }
        p = new Point(x + getWidth(), y + getHeight());
        Point p1 = new Point(x, y + getHeight());
        l1 = new Line(p1, p);
        if (l1.intersectionWith(line) != null) {
            l.add(l1.intersectionWith(line));
        }
        p = new Point(x + getWidth(), y + getHeight());
        p1 = new Point(x + getWidth(), y);
        l1 = new Line(p1, p);
        if (l1.intersectionWith(line) != null) {
            l.add(l1.intersectionWith(line));
        }
        return l;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public Point getUpperRight() {
        return this.upperRight;
    }

    public Point getDownLeft() {
        return this.downLeft;
    }

    public Point getDownRight() {
        return this.downRight;
    }

    public void setRec(Point p, double step) {
        this.upperLeft = new Point(p.getX() + step, p.getY());
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        this.downLeft = new Point(x, y + height);
        this.upperRight = new Point(x + width, y);
        this.downRight = new Point(x + width, y + height);
    }

}





