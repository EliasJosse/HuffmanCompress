import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

// Class for compressing files according to Huffman: https://en.wikipedia.org/wiki/Huffman_coding

public class Comp {
	File file;
	Huffman tree;
	TreeMap<Integer, String> map;
	
	
	
	public Comp(String filename) throws IOException{
		file = new File(filename);
		//Extract content 
		int[] list = Extract();
		tree = new Huffman(listSize(list), listChars(list), listNums(list));
		map = new TreeMap<>();
		String s = "";
		tree.getNewEncoding(tree.root, map, s);
	}
	

	private char[] listChars(int[] list) {
		String text = "";
		for (int i = 0; i < list.length; i++) {
			if(list[i] > 0)text+=(char)i;
		}
		System.out.println(text);
		char[] dd = text.toCharArray();
		for(char d : dd) System.out.println(d);
		return dd;
	}


	private int listSize(int[] list) {
		int size = 0;
		for (int i = 0; i < list.length; i++) {
			if(list[i] > 0) size++;
		}
		System.out.println(size);
		return size;
	}


	private int[] listNums(int[] list) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		for (int i = 0; i < list.length; i++) {
			if(list[i] > 0) l.add(list[i]);
		}
		
		int[] k = new int[l.size()];
		
		for (int i = 0; i < l.size(); i++) {
			k[i] = l.get(i);
			System.out.println(k[i]);
		}
		
		return k;
	}


	private int[] Extract() throws IOException {
		int[] list =  new int[256];
		FileInputStream is = new FileInputStream(file);
		byte[] buffer = new byte[2];
		while(is.available() > 0){
			is.read(buffer);
			String text = new String(buffer, "UTF-8");
			char c = text.charAt(0);
			list[c]++;
		}
		return list;
	}

	
	
	public void Write(String filename) throws IOException{
		FileInputStream is = new FileInputStream(file);
		FileOutputStream us = new FileOutputStream(filename);
		byte[] buffer = new byte[1];
		String tot = "";
		while(is.available() > 0){
			is.read(buffer);
			String text = new String(buffer, "UTF-8");
			tot += map.get((int)text.charAt(0)); 
		}	
		System.out.println("new content " + tot);
		BitSet bs = new BitSet(tot.length());
		
		int bitcounter = 0;
		for(Character c : tot.toCharArray()) {
		    if(c.equals('1')) {
		        bs.set(bitcounter);
		    }
		    bitcounter++;
		}
		
		us.write(bs.toByteArray());
	}

	public static void main(String[] args) throws IOException {
		Comp a = new Comp("test.ser");
		String s = "";
		Huffman.printCode(a.tree.root, s);
		TreeMap<Integer, String> map = new TreeMap<>();
		s = "";
		a.tree.getNewEncoding(a.tree.root, map, s);
		System.out.println(map.toString());
		a.Write("codedTest.ser");
		
		FileOutputStream fos = new FileOutputStream("map.ser");
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(map);
		
	}

}
