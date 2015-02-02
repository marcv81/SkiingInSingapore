package net.marcv81.ski;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Map, immutable.
 */
class Map implements Iterable<Point> {

    /**
     * Width of this map.
     */
    private final int width;

    /**
     * Height of this map.
     */
    private final int height;

    /**
     * Public constructor.
     *
     * @param width  Width of the map.
     * @param height Height of the map.
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @return The width of this map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of this map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param point A point.
     * @return Whether this map contains the point or not.
     */
    public boolean containsPoint(Point point) {
        return (point.getX() >= 0) && (point.getX() < width)
                && (point.getY() >= 0) && (point.getY() < height);
    }

    /**
     * @param point A point.
     * @return A list of points contained in this map that are adjacent to the point.
     */
    public List<Point> getAdjacentPoints(Point point) {

        List<Point> adjacentPoints = new LinkedList<>();

        Point north = new Point(point.getX(), point.getY() - 1);
        if (containsPoint(north)) {
            adjacentPoints.add(north);
        }
        Point south = new Point(point.getX(), point.getY() + 1);
        if (containsPoint(south)) {
            adjacentPoints.add(south);
        }
        Point east = new Point(point.getX() + 1, point.getY());
        if (containsPoint(east)) {
            adjacentPoints.add(east);
        }
        Point west = new Point(point.getX() - 1, point.getY());
        if (containsPoint(west)) {
            adjacentPoints.add(west);
        }

        return adjacentPoints;
    }

    /**
     * @return An iterator over all the points contained in this map.
     */
    @Override
    public Iterator<Point> iterator() {
        return new MapIterator();
    }

    /**
     * Iterator over all the points contained in this map.
     */
    private class MapIterator implements Iterator<Point> {

        private int i = 0, j = 0;

        @Override
        public boolean hasNext() {
            return j < height;
        }

        @Override
        public Point next() {
            Point point = new Point(i, j);
            i++;
            if (i == width) {
                i = 0;
                j++;
            }
            return point;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
