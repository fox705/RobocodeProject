import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MapToTxt {

	ArrayList<Node> path = search.path;

	public void save(String fileName){
	    String mapTxt="";
		for (int i=0; i<path.size(); i++) {
	    	mapTxt+=path.get(i).getRow()+" ";
	    	mapTxt+=path.get(i).getCol()+" ";
	    }
	    PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(fileName));
			pw.write(mapTxt);
		    pw.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		}
		

	
}
