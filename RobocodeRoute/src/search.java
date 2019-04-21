import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import robocode.control.*;
import robocode.control.RandomFactory;
import robocode.control.events.*;

public class search {

	final static int NumPixelRows = 960;
	final static int NumPixelCols = 960;
	final static int NumGridRows = NumPixelRows / 64;
	final static int NumGridCols = NumPixelCols / 64;

	final static int NumObstacles = 70;

	public static ArrayList<Node> path;
	public static int[][] blocksArray = new int[NumObstacles][2];
	public static int AgentGridRow;
	public static int AgentGridCol;
	static double InitialAgentRow;
	static double InitialAgentCol;
	static double FinalAgentRow, FinalAgentCol;

	Random rd = new Random();
	GridPixelChanger gpc = new GridPixelChanger();
	checkCollisions cc = new checkCollisions();

	public static void main(String[] args) {
		// Create the RobocodeEngine
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		// Run from D:/robocode
		// Show the Robocode battle view
		engine.setVisible(true);
		// Create the battlefield
		BattlefieldSpecification battlefield = new BattlefieldSpecification(NumPixelRows, NumPixelCols);
		// 800x600
		// Setup battle parameters
		int numberOfRounds = 5;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = false;
		/*
		 * * Create obstacles and place them at random so that no pair of obstacles *
		 * are at the same position
		 */

		RobotSpecification[] modelRobots = engine.getLocalRepository("sample.SittingDuck, RouteBot*");
		RobotSpecification[] existingRobots = new RobotSpecification[NumObstacles + 1];
		RobotSetup[] robotSetups = new RobotSetup[NumObstacles + 1];

		for (int NdxObstacle = 0; NdxObstacle < NumObstacles; NdxObstacle++) {

			int ObstacleGridRow = Math.abs(RandomFactory.getRandom().nextInt() % 15);
			int ObstacleGridCol = Math.abs(RandomFactory.getRandom().nextInt() % 15);

			double InitialObstacleRow = GridPixelChanger.gridToPixel(ObstacleGridRow);
			double InitialObstacleCol = GridPixelChanger.gridToPixel(ObstacleGridCol);

			boolean found = checkCollisions.checkCollisions(robotSetups, NdxObstacle, InitialObstacleRow,
					InitialObstacleCol);

			if (found == true) {
				NdxObstacle--;
				continue;
			}

			blocksArray[NdxObstacle][0] = ObstacleGridRow;
			blocksArray[NdxObstacle][1] = ObstacleGridCol;

			existingRobots[NdxObstacle] = modelRobots[0];
			robotSetups[NdxObstacle] = new RobotSetup(InitialObstacleRow, InitialObstacleCol, 0.0);
		}
		/*
		 * * Create the agent and place it in a random position without obstacle
		 */
		existingRobots[NumObstacles] = modelRobots[1];

		do {

			AgentGridRow = Math.abs(RandomFactory.getRandom().nextInt() % 15);
			AgentGridCol = Math.abs(RandomFactory.getRandom().nextInt() % 15);

			InitialAgentRow = GridPixelChanger.gridToPixel(AgentGridRow);
			InitialAgentCol = GridPixelChanger.gridToPixel(AgentGridCol);
		} while (checkCollisions.checkCollisions(robotSetups, NumObstacles, InitialAgentRow, InitialAgentCol));

		robotSetups[NumObstacles] = new RobotSetup(InitialAgentRow, InitialAgentCol, 0.0);

		do {
			AgentGridRow = Math.abs(RandomFactory.getRandom().nextInt() % 15);
			AgentGridCol = Math.abs(RandomFactory.getRandom().nextInt() % 15);

			FinalAgentRow = GridPixelChanger.gridToPixel(AgentGridRow);
			FinalAgentCol = GridPixelChanger.gridToPixel(AgentGridCol);
		} while (checkCollisions.checkCollisions(robotSetups, NumObstacles, FinalAgentRow, FinalAgentCol)
				|| checkCollisions.checkCollision(InitialAgentRow, InitialAgentCol, FinalAgentRow, FinalAgentCol));

		Node initialNode = new Node(GridPixelChanger.pixelToGrid(InitialAgentRow),
				GridPixelChanger.pixelToGrid(InitialAgentCol));
		Node finalNode = new Node(GridPixelChanger.pixelToGrid(FinalAgentRow),
				GridPixelChanger.pixelToGrid(FinalAgentCol));

		AStar as = new AStar(NumGridRows, NumGridCols, initialNode, finalNode);
		as.setBlocks(blocksArray);
		path = (ArrayList<Node>) as.findPath();
		
		MapToTxt mtxt = new MapToTxt();
		mtxt.save("map.txt");
	
		

		/*
		 * Create and run the battle
		 */
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);
		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true);
		// waits till the battle finishes
		// Cleanup our RobocodeEngine
		engine.close();
		// Make sure that the Java VM is shut down properly
		System.exit(0);
	}
}