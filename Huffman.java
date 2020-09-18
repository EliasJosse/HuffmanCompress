import java.util.PriorityQueue; 
import java.util.Scanner;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.Comparator; 
  


class HuffmanNode implements Serializable { 
  
    int data; 
    char c; 
  
    HuffmanNode left; 
    HuffmanNode right; 
} 
  




class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y) 
    { 
        return x.data - y.data; 
    } 
} 




  
public class Huffman implements Serializable { 
	
	HuffmanNode root = null;
  

    public static void printCode(HuffmanNode rootE, String s) 
    { 
        if (rootE.left 
                == null
            && rootE.right 
                   == null
            && Character.isLetter(rootE.c)) { 
  
            System.out.println(rootE.c + ":" + s); 
  
            return; 
        } 
  
        printCode(rootE.left, s + "0"); 
        printCode(rootE.right, s + "1"); 
    } 
    
    public void getNewEncoding(HuffmanNode rootE,TreeMap<Integer, String> map, String s)	{
        if (rootE.left 
                == null
            && rootE.right 
                   == null
            && Character.isLetter(rootE.c)) { 
  
        	map.put((int) rootE.c, s);
            return; 
        }
  
        getNewEncoding(rootE.left, map, s + "0");
        getNewEncoding(rootE.right, map, s + "1");
    }
    
    public Huffman(int size, char[] chars, int[] nums){
        PriorityQueue<HuffmanNode> q 
        = new PriorityQueue<HuffmanNode>(size, new MyComparator()); 

        for (int i = 0; i < size; i++) { 
	        HuffmanNode hn = new HuffmanNode(); 
	
	        hn.c = chars[i]; 
	        hn.data = nums[i]; 
	
	        hn.left = null; 
	        hn.right = null; 
	        q.add(hn); 
    	} 
	    while (q.size() > 1) { 
	        HuffmanNode x = q.peek(); 
	        q.poll(); 
	
	        HuffmanNode y = q.peek(); 
	        q.poll(); 
	
	        HuffmanNode f = new HuffmanNode(); 
	
	        f.data = x.data + y.data; 
	        f.c = '-'; 
	        f.left = x; 
	        f.right = y; 
	        root = f; 
	        q.add(f); 
	    } 
    	
    }
} 