package net.marcv81.ski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class to load altitude maps from files.
 */
public final class AltitudeMapLoader {

    private static final int DIMENSIONS = 2;

    /**
     * Private constructor to prevent instantiation.
     */
    private AltitudeMapLoader() {
    }

    /**
     * Creates an altitude map from a file.
     *
     * @param file File.
     * @return Altitude map.
     * @throws IOException In case an error happened reading/parsing the file.
     */
    public static AltitudeMap load(File file) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Reads dimensions and creates altitudes array.
            int[] dimensions = parseLine(reader.readLine(), DIMENSIONS);
            int width = dimensions[0];
            int height = dimensions[1];
            int[][] altitudes = new int[width][height];

            // Reads altitudes.
            for (int j = 0; j < height; j++) {
                int[] line = parseLine(reader.readLine(), width);
                for (int i = 0; i < width; i++) {
                    int altitude = line[i];
                    altitudes[i][j] = altitude;
                }
            }

            return new AltitudeMap(width, height, altitudes);
        }
    }

    /**
     * Parses a string of space-separated integers.
     *
     * @param line           String to parse.
     * @param expectedLength Expected number of integers in the string.
     * @return Array of parsed integers.
     * @throws IOException In case the string does not contain the expected number of integers.
     */
    private static int[] parseLine(String line, int expectedLength) throws IOException {
        String[] strings = line.split(" ");
        if (strings.length != expectedLength) {
            throw new IOException("Unexpected format");
        }
        int[] integers = new int[expectedLength];
        for (int i = 0; i < expectedLength; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
}
