package Shapes;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double dis = (this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY());
        dis = Math.sqrt(dis);
        return dis;
    }


    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
            return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
