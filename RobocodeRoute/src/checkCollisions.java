import robocode.control.RobotSetup;

public class checkCollisions {

	public static boolean checkCollision(double x1, double y1, double x2, double y2) {
		return (x1 == x2 && y1 == y2);
	}

	public static boolean checkCollisions(RobotSetup[] robotSetups, int NdxObstacle, double x, double y) {
		boolean found = false;
		for (int p = 0; p < NdxObstacle; p++) {
			if (checkCollision(robotSetups[p].getX(), robotSetups[p].getY(), x, y)) {
				return true;
			}
		}
		return false;
	}
}