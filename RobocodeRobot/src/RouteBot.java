import robocode.Robot;
import robocode.ScannedRobotEvent;

public class RouteBot extends Robot {

	public void run() {
		turnLeft(getHeading() % 9);
		turnGunRight(90);
		while (true) {
			ahead(1000);
			turnRight(90);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		fire(100);

	}
}
