package net.marcv81.ski;

/**
 * Path, immutable.
 */
public final class Path implements Comparable<Path> {

    private static final int PRIME = 92821;

    /**
     * Total length of this path.
     */
    private final int length;

    /**
     * Total drop of this path.
     */
    private final int drop;

    /**
     * Public constructor from drop only.
     * The length is set to 1.
     *
     * @param drop Total drop of the path.
     */
    public Path(int drop) {
        this(1, drop);
    }

    /**
     * Public constructor from length and drop.
     *
     * @param length Total length of this path.
     * @param drop   Total drop of this path.
     */
    public Path(int length, int drop) {
        this.length = length;
        this.drop = drop;
    }

    /**
     * Adds this path to another.
     *
     * @param path The path to add this path to.
     * @return A new path accumulating the length and drop of both paths.
     */
    public Path add(Path path) {
        return new Path(this.length + path.length, this.drop + path.drop);
    }

    /**
     * Compares this path to another.
     * The greatest path is the longest. If both paths have the same length the greatest path
     * is the one with the steepest drop.
     *
     * @param path The path to compare this path to.
     * @return 1 if this path is greater, -1 if this path is lower, 0 if both paths are equal.
     */
    @Override
    public int compareTo(Path path) {
        if (path.length > length) {
            return -1;
        }
        if (path.length < length) {
            return 1;
        }
        if (path.drop > drop) {
            return -1;
        }
        if (path.drop < drop) {
            return 1;
        }
        return 0;
    }

    /**
     * @param object An object to compare this path to.
     * @return Whether this path is equal to the object or not.
     */
    @Override
    public boolean equals(Object object) {
        return (object instanceof Path)
                && (this.length == ((Path) object).length)
                && (this.drop == ((Path) object).drop);
    }

    /**
     * @return The hashcode for this path.
     */
    @Override
    public int hashCode() {
        return PRIME * (PRIME + length) + drop;
    }

    /**
     * @return A string representing this path.
     */
    @Override
    public String toString() {
        return "Length: " + length + ", Drop: " + drop;
    }
}
