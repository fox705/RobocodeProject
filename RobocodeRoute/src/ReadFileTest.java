

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileTest {

	public void ReadFile() throws FileNotFoundException {

		 List<Integer> path = new ArrayList<>();

		File file = new File("C:\\Users\\Micha³\\Documents\\RobocodeProject\\RobocodeRoute\\map.txt");
		Scanner sc = new Scanner(file);

		while (sc.hasNext()) {
			System.out.println(sc.nextLine()); 
			path.add(sc.useDelimiter(" ").nextInt());
		}
	}
}
