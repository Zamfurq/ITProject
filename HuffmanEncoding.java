import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


public class HuffmanEncoding {
  static HashMap<Character, String> codes = new HashMap<Character, String>();
  
  public static void printCode(HuffmanNode root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {

      System.out.println(root.c + "   |  " + s);

      return;
    }
    printCode(root.left, s + "0");
    printCode(root.right, s + "1");
  }
  public static void storeCode(HuffmanNode root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {

      codes.put(root.c, s);

      return;
    }
    storeCode(root.left, s + "0");
    storeCode(root.right, s + "1");
  }

  public static void huffmanCodes(char c[], int weight[],int n){
    PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());

    for (int i = 0; i < n; i++) {
      HuffmanNode hn = new HuffmanNode();

      hn.c = c[i];
      hn.data = weight[i];

      hn.left = null;
      hn.right = null;

      q.add(hn);
    }

    HuffmanNode root = null;

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
    System.out.println(" Char | Huffman code ");
    System.out.println("--------------------");
    printCode(root, "");
    storeCode(root, "");
    
  }
  static char[] removeDuplicate(char[] array){
    String newArray = "";
    for(int i = 0;i < array.length;i++) {
      if(newArray.indexOf(array[i]) == -1) {
        newArray += array[i];
      }
    }
    return newArray.toCharArray();
  }

  static int countChar(String msg, char c){
    int count = 0;
    for(int i=0;i<msg.length();i++){
      if(msg.charAt(i) == c){
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the message that want to be encoded:");
    String message = sc.nextLine();
    char[] charArray = removeDuplicate(message.toCharArray());
    int n = charArray.length;
    int[] charfreq = new int[n];
    for(int i=0;i<n;i++) {
      charfreq[i] = countChar(message, charArray[i]);
    }
    huffmanCodes(charArray,charfreq,n);
    char[] msgArr = message.toCharArray();
    System.out.println("Encoded messages:");
    for(int i=0;i<msgArr.length;i++){
      System.out.print(codes.get(msgArr[i]) + " ");
    }
    
    
  }
}

class HuffmanNode {
  int data;
  char c;

  HuffmanNode left;
  HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
  public int compare(HuffmanNode x, HuffmanNode y) {
    return x.data - y.data;
  }
}