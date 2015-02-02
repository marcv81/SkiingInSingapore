package net.marcv81.ski;

/**
 * Point, immutable.
 */
public final class Point {

    private static final int PRIME = 92821;

    /**
     * X coordinate of this point.
     */
    private final int x;

    /**
     * Y coordinate of this point.
     */
    private final int y;

    /**
     * Public constructor.
     *
     * @param x X coordinate of this point.
     * @param y Y coordinate of this point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X coordinate of this point.
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y coordinate of this point.
     */
    public int getY() {
        return y;
    }

    /**
     * @param object An object to compare this point to.
     * @return Whether this point is equal to the object or not.
     */
    @Override
    public boolean equals(Object object) {
        return (object instanceof Point)
                && (this.x == ((Point) object).x)
                && (this.y == ((Point) object).y);
    }

    /**
     * @return The hashcode for this point.
     */
    @Override
    public int hashCode() {
        return PRIME * (PRIME + x) + y;
    }
}
