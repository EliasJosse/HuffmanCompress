import java.io.File;
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
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class Decomp {
	TreeMap<Integer, String> map;
	TreeMap<String, Integer> mapReversed;
	int numBit;
	
	String name;
	
	
	
	public Decomp() throws IOException, ClassNotFoundException {
		
		System.out.println("Input filename of orinal file");
		
		Scanner scan = new Scanner(System.in);
		name = scan.nextLine();
		
		
		FileInputStream fis = new FileInputStream(name + "Map.ser");
		ObjectInputStream is = new ObjectInputStream(fis);
		map = (TreeMap<Integer, String>) is.readObject();
		reverseMap();
	}
	
	
	private void reverseMap() {
		mapReversed = new TreeMap<>();
		for(Entry<Integer, String> entry : map.entrySet()){
		    mapReversed.put(entry.getValue(), entry.getKey());
		}
		
	}


	public void writenRead() throws IOException {
		FileInputStream fs = new FileInputStream(name + "Compressed.ser");
		FileOutputStream fos = new FileOutputStream(name + "Decompressed.ser");
	
		byte[] buff = new byte[fs.available()];
		fs.read(buff);
	 	BitSet set = BitSet.valueOf(buff);
	 	
	 	String binaryString = "";
	 	
	 	int count = 0;
	 	System.out.println(set.length());
	 	for(int i = 0; i <= set.length(); i++) {
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
	 	
	 	fos.close();
	 	fs.close();
	 	
	 
	
	}


	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Decomp d = new Decomp();
		d.writenRead();
		
		File f = new File(d.name + ".ser");
		File r = new File(d.name + "Decompressed.ser");
		
		System.out.println(FileUtils.contentEquals(f, r)? "successfully decompressed": "unsuccessfully decompressed");
	}

}
