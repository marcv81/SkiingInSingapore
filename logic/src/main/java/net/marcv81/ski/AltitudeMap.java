package net.marcv81.ski;

import java.util.LinkedList;
import java.util.List;

/**
 * Altitude map, immutable.
 */
public final class AltitudeMap extends Map {

    /**
     * Array containing the altitudes of this map.
     */
    private final int[][] altitudes;

    /**
     * Public constructor.
     * No checks are made on the dimensions of the altitudes array, use with care.
     *
     * @param width     Width of the map.
     * @param height    Height of the map.
     * @param altitudes Array containing the altitudes of this map.
     */
    public AltitudeMap(int width, int height, int[][] altitudes) {
        super(width, height);
        this.altitudes = altitudes.clone();
    }

    /**
     * @param point A point.
     * @return The altitude of this map at the point.
     */
    public int getAltitude(Point point) {
        return altitudes[point.getX()][point.getY()];
    }

    /**
     * @param point A point.
     * @return A list of points contained in this map that are downhill from the point.
     */
    public List<Point> getDownhillPoints(Point point) {

        int altitude = getAltitude(point);
        List<Point> downhillPoints = new LinkedList<>();

        for (Point adjacentPoint : getAdjacentPoints(point)) {
            if (getAltitude(adjacentPoint) < altitude) {
                downhillPoints.add(adjacentPoint);
            }
        }

        return downhillPoints;
    }
}
