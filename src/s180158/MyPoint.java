package s180158;

import java.awt.*;

public class MyPoint extends Point {
    public MyPoint() {
        super();
    }

    public MyPoint(Point p) {
        super(p);
    }

    public MyPoint(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "[x=" + this.x + ",y=" + this.y + "]";
    }
}
