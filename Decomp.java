import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Decomp {

	TreeMap<Integer, String> map;
	TreeMap<String, Integer> mapReversed;
	int numBit;
	
	public Decomp() throws IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream("map.ser");
		ObjectInputStream is = new ObjectInputStream(fis);
		map = (TreeMap<Integer, String>) is.readObject();
		
		reverseMap();
		
		System.out.println(map.toString());
		System.out.println(mapReversed.toString());
	}
	
	
	private void reverseMap() {
		mapReversed = new TreeMap<>();
		for(Entry<Integer, String> entry : map.entrySet()){
		    mapReversed.put(entry.getValue(), entry.getKey());
		}
		
	}


	public void writenRead() throws IOException {
		FileInputStream fs = new FileInputStream("codedTest.ser");
		FileOutputStream fos = new FileOutputStream("backtoTest.ser");
	
		byte[] buff = new byte[fs.available()];
		fs.read(buff);
	 	BitSet set = BitSet.valueOf(buff);
	 	
	 	System.out.println(set.length());
	 	System.out.println(set.toString());
	 	String binaryString = "";
	 	for(int i = 0; i < set.length(); i++) {
	 	    if(set.get(i)) {
	 	        binaryString += "1";
	 	    } else {
	 	        binaryString += "0";
	 	    }
	 	    
	 	    if(mapReversed.containsKey(binaryString)){
	 	    	int n = mapReversed.get(binaryString);
	 	    
	 	    	fos.write(n);
	 	    	binaryString = "";
	 	    }
	 	}
	 	
	 
	
	}


	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Decomp d = new Decomp();
		d.writenRead();
	}

}
