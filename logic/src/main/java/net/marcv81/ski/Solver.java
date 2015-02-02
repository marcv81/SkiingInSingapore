package net.marcv81.ski;

/**
 * Solver, immutable.
 */
public final class Solver {

    /**
     * Altitude map for which to find the very greatest path.
     */
    private final AltitudeMap map;

    /**
     * For each point in the altitude map, the greatest path if we started skiing from there.
     */
    private final Path[][] greatestPaths;

    /**
     * The very greatest path in the entire altitude map.
     */
    private final Path veryGreatestPath;

    /**
     * Public constructor.
     *
     * @param map Altitude map for which to find the very greatest path.
     */
    public Solver(AltitudeMap map) {

        this.map = map;
        greatestPaths = new Path[map.getWidth()][map.getHeight()];

        // Iterates through all the points in the altitude map and searches for the very greatest path.
        Path greatestPath = new Path(0);
        for (Point point : map) {
            Path path = getGreatestPath(point);
            if (path.compareTo(greatestPath) > 0) {
                greatestPath = path;
            }
        }
        veryGreatestPath = greatestPath;
    }

    /**
     * @return The very greatest path in the entire altitude map.
     */
    public Path getVeryGreatestPath() {
        return veryGreatestPath;
    }

    /**
     * Gets the greatest path if we started skiing from a particular point.
     *
     * @param point A point.
     * @return The greatest path from the point.
     */
    private Path getGreatestPath(Point point) {

        // Retrieves and returns the greatest path from the point if previously calculated.
        Path greatestPath = greatestPaths[point.getX()][point.getY()];
        if (greatestPath != null) {
            return greatestPath;
        }

        // The greatest path from the point is the greatest of the different possible path
        // if we headed towards each of the downhill points and followed the greatest path
        // after that.
        // Desirable side effect: if there is no downhill point (i.e.: bottom of the hill)
        // then the greatest path from the point is length = 1 and drop = 0.
        greatestPath = new Path(0);
        for (Point nextPoint : map.getDownhillPoints(point)) {
            Path nextPath = getNextPath(point, nextPoint);
            if (nextPath.compareTo(greatestPath) > 0) {
                greatestPath = nextPath;
            }
        }

        // Stores and returns the greatest path from the point.
        greatestPaths[point.getX()][point.getY()] = greatestPath;
        return greatestPath;
    }

    /**
     * Gets the path if we started from a particular point, headed towards another,
     * and followed the greatest path after that. The two points must be adjacent,
     * and the first one must be at a higher altitude than the second one for the
     * result to make sense.
     *
     * @param from A point.
     * @param to   Another point.
     * @return The path if we started from "from", headed towards "to", and followed
     * the greatest path after that.
     */
    private Path getNextPath(Point from, Point to) {
        return getGreatestPath(to).add(new Path(map.getAltitude(from) - map.getAltitude(to)));
    }
}
