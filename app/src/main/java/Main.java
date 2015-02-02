import java.io.File;
import java.io.IOException;

import net.marcv81.ski.*;

public class Main {

    public static void main(String[] args) {

        try {

            AltitudeMap altitudeMap = AltitudeMapLoader.load(new File("map.txt"));
            Solver solver = new Solver(altitudeMap);
            System.out.println(solver.getVeryGreatestPath());

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
