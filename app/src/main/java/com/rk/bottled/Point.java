package com.rk.bottled;

public class Point {
    float x, y;

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Point getDifference(Point q) {
        if (q != null) {
            return new Point(this.x - q.x, this.y - q.y);
        }
        return null;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")\n";
    }

    public float getSlope(Point q) {
        return (this.y - q.y)/(this.x - q.x);
    }
}
