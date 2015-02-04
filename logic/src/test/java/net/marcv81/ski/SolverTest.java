package net.marcv81.ski;

import org.junit.*;

import java.io.File;
import java.io.IOException;

public class SolverTest {

    private static final Path EXPECTED_PATH = new Path(5, 8);

    /**
     * Tests that the solver calculates the right greatest path for the sample map.
     *
     * @throws IOException In case an error happened reading/parsing the file.
     */
    @Test
    public void testSolver() throws IOException {

        File file = new File(getClass().getResource("/map.txt").getFile());
        AltitudeMap altitudeMap = AltitudeMapLoader.load(file);

        Solver solver = new Solver(altitudeMap);

        Assert.assertEquals(EXPECTED_PATH, solver.getVeryGreatestPath());
    }
}
