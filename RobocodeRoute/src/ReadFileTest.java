import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileTest {

	public void ReadFile() {
		FileInputStream fileIn;
		String[] splitted = {};
		try {
			fileIn = new FileInputStream("map.txt");

			Scanner scan = new Scanner(fileIn);
			String loaded = scan.nextLine();
			splitted = loaded.split("\\s+");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Node> nodeList = new ArrayList();
		for (int i = 0; i < splitted.length; i += 2) {
			nodeList.add(new Node(new Integer(splitted[i]), new Integer(splitted[i + 1])));
		}

		Node currentNode = nodeList.get(0);
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int prevRow, prevCol;

	}
}
	
	

