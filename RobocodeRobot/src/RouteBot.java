import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

import robocode.AdvancedRobot;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class RouteBot extends AdvancedRobot {

	List<Integer> path = new ArrayList<>();
	Integer x, y;
	Integer x1, y1;

	public void run() {

		Scanner sc;
		try {
			sc = new Scanner(this.getDataFile("map.txt"));

			while (sc.hasNext()) {
				path.add(sc.useDelimiter(" ").nextInt());
			}

			for (int i = 0; i <= path.size(); i += 2) {
				x = path.get(i);
				y = path.get(i + 1);
				x1 = path.get(i + 2);
				y1 = path.get(i + 3);

				if (x > x1) {
					turnLeft(90);
					ahead(64);
					turnRight(90);
					
				}

				if (x < x1) {
					turnRight(90);
					ahead(64);
					turnLeft(90);
				}

				if (y < y1) {
					ahead(64);
				}

				if (y > y1) {
					back(64);
					;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}