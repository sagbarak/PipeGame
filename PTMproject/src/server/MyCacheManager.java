package server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MyCacheManager implements CacheManager<ArrayList<String>> {

	HashMap<ArrayList<String>,ArrayList<String>> hm;
/*-------------Constructors---------------------*/
	public MyCacheManager() {
		hm=new HashMap<ArrayList<String>,ArrayList<String>>();
	}
	
	public MyCacheManager(HashMap<ArrayList<String>,ArrayList<String>> hm) {
		this.hm=hm;
	}

/*-----------------------------------------------*/
	
	
	public void writeCM() {//write the whole hashmap to file
		try
		{
			FileOutputStream outputFile = new FileOutputStream("cachemanager.ser");
			ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);
			outputObject.writeObject(hm);
			outputObject.close();
			outputFile.close();
			// System.out.printf("Cache Manager been saved.\n");
		}catch(IOException ioe)
		{
			//   ioe.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readCM() {//load solution from file
		File f= new File("cachemanager.ser");
		if(f.exists()&& !f.isDirectory()) {
			try{
				FileInputStream inputFile = new FileInputStream("cachemanager.ser");
				ObjectInputStream inputObject = new ObjectInputStream(inputFile);
				this.hm = (HashMap<ArrayList<String>,ArrayList<String>>) inputObject.readObject();
				inputObject.close();
				inputFile.close();
			}
			catch(IOException ioe){
				//ioe.printStackTrace();
				return;
			}
			catch(ClassNotFoundException c){
				//System.out.println("Class not found");
				//c.printStackTrace();
				return;
			}
		}
	}

	public boolean isExist(ArrayList<String> problem) { //check if solution exist in CM
		if(hm.containsKey(problem))
			return true;
		else
			return false;

	}

	public ArrayList<String> getSolutioncm(ArrayList<String> problem) {
		if(isExist(problem)) {
			return hm.get(problem);
		}
		else
			return null;
	}

	public void addSolution(ArrayList<String> solution,ArrayList<String> problem) {
		hm.put(problem,solution);
		writeCM();
	}
}