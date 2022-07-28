package Shapes;
public class Line {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    public double length() {
        double len = (start.getX() - end.getX())
                * (start.getX() - end.getX()) + (start.getY() - end.getY()) * (start.getY() - end.getY());
        len = Math.sqrt(len);
        return len;
    }

    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        x = Math.abs(x);
        double y = (this.start.getY() + this.end.getY()) / 2;
        y = Math.abs(y);
        Point mid = new Point(x, y);
        return mid;
    }

    public Point start() {
        return start;
    }

    public Point end() {
        return end;
    }

    public boolean check(Line l, Point p) {
        double dis1, dis2, d1, d2;
        double min = 0.00000000001;
        dis1 = p.distance(this.start) + p.distance(this.end);
        d1 = this.start.distance(this.end);
        dis2 = p.distance(l.start) + p.distance(l.end);
        d2 = l.start.distance(l.end);
        return (Math.abs(dis1 - d1)) < min && (Math.abs(dis2 - d2) < min);
    }

    public boolean isIntersecting(Line other) {
        double m1, m2, b1, b2;
        double x, y;
        Point p;
        m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        b1 = this.start.getY() - (this.start.getX() * m1);
        m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        b2 = other.start.getY() - (other.start.getX() * m2);
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() != other.end.getX())) {
            y = (m2 * this.start.getX()) + b2;
            p = new Point(this.start.getX(), y);
            return check(other, p);
        }
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            y = (m1 * other.start.getX()) + b1;
            p = new Point(other.start.getX(), y);
            return check(other, p);
        }
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            if (this.start.equals(other.start)) {
                return true;
            }
            if (this.start.equals(other.end)) {
                return true;
            }
            if (this.end.equals(other.start)) {
                return true;
            }
            return this.end.equals(other.end);
        }
        if (m1 == m2) {
            if (b1 != b2) {
                return false;
            }
            if (b1 == b2) {
                return this.check(other, other.start)
                        || this.check(other, other.end)
                        || other.check(this, this.start)
                        || other.check(this, this.end);
            }
        }
        x = (b2 - b1) / (m1 - m2);
        y = (m1 * x) + b1;
        p = new Point(x, y);
        return check(other, p);
    }

    public Point intersectionWith(Line other) {
        double x, y;
        double m1, m2, b1, b2;
        Point p;
        if (this.isIntersecting(other)) {
            if (((this.start.getX() == this.end.getX()) && (this.start.getY() == this.end.getY()))
                    && ((other.start.getX() == other.end.getX()) && (other.start.getY() == other.end.getY()))) {
                return this.start;
            }
            if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
                if (this.start.equals(other.start)) {
                    return this.start;
                }
                if (this.start.equals(other.end)) {
                    return this.start;
                }
                if (this.end.equals(other.end)) {
                    return this.end;
                }
                if (this.end.equals(other.start)) {
                    return this.end;
                }
                return null;
            }
            m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            b1 = this.start.getY() - (this.start.getX() * m1);
            m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            b2 = other.start.getY() - (other.start.getX() * m2);
            x = (b2 - b1) / (m1 - m2);
            y = (m1 * x) + b1;
            if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
                x = this.start.getX();
                y = (m2 * x) + b2;
                p = new Point(x, y);
                return p;
            }
            if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
                x = other.start.getX();
                y = (m1 * x) + b1;
                p = new Point(x, y);
                return p;

            }
            if (m1 == m2 && b1 == b2) {
                if (this.start.equals(other.start)) {
                    return this.start;
                }
                if (this.start.equals(other.end)) {
                    return this.start;
                }
                if (this.end.equals(other.end)) {
                    return this.end;
                }
                if (this.end.equals(other.start)) {
                    return this.end;
                }
                return null;
            }
            p = new Point(x, y);
            return p;
        }
        return null;
    }

    public boolean equals(Line other) {
        return ((other.start.getX() == this.start.getX()) && (other.start.getY() == this.start.getY()))
                && ((other.end.getX() == this.end.getX()) && (other.end.getY() == this.end.getY()));
    }

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double x = rect.getUpperLeft().getX();
        double y = rect.getUpperLeft().getY();
        Point[] arr = new Point[4];
        int count = 0, c = 0;
        double min;
        Point p1;
        Point p = new Point(x + rect.getWidth(), y);
        Line l = new Line(rect.getUpperLeft(), p);
        if (intersectionWith(l) != null) {
            arr[count] = intersectionWith(l);
            count++;
        }
        p = new Point(x, y + rect.getHeight());
        l = new Line(rect.getUpperLeft(), p);
        //check if there is intersetion.
        if (intersectionWith(l) != null) {
            arr[count] = intersectionWith(l);
            count++;
        }
        p = new Point(x, y + rect.getHeight());
        p1 = new Point(x + rect.getWidth(), y + rect.getHeight());
        l = new Line(p1, p);
        if (intersectionWith(l) != null) {
            arr[count] = intersectionWith(l);
            count++;
        }
        p = new Point(x + rect.getWidth(), y);
        p1 = new Point(x + rect.getWidth(), y + rect.getHeight());
        l = new Line(p1, p);
        if (intersectionWith(l) != null) {
            arr[count] = intersectionWith(l);
            count++;
        }
=        if (count == 0) {
            return null;
        } else {
            min = start.distance(arr[0]);
            for (int i = 1; i < count; i++) {
                if (start.distance(arr[i]) < min) {
                    min = start.distance(arr[i]);
                    c = i;
                }
            }
        }
        return arr[c];
    }

}


