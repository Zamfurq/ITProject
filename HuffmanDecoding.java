import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class HuffmanDecoding {
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

  static String decode(Map<String,Character> codes, String s) {
    String ans = "";
    ArrayList exist = new ArrayList<Integer>();
    for(int i =0;i<s.length();i++){
      for (int j = i+1;j<=s.length();j++){
        if(exist.contains(i)){
          continue;
        }
        if(codes.containsKey(s.substring(i,j))){
          ans += codes.get(s.substring(i,j));
          for(int k =i;k<j;k++){
            exist.add(k);
          }
          break;
        } else {
          continue;
        }
      }
    }
    return ans;
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
    Map<String, Character> newCodes = codes.entrySet()
    .stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the message that want to be decoded:");
    String message = sc.nextLine();
    System.out.println("Decoded messages:");
    String msg = decode(newCodes, message);
    System.out.println(msg);
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the character (should be different):");
    String base = sc.nextLine();
    char[] charArray = base.toCharArray();
    int n = charArray.length;
    int[] charfreq = new int[n];
    System.out.println("Enter the frequency of character:");
    for(int i=0;i<n;i++) {
      charfreq[i] = sc.nextInt();
    }
    huffmanCodes(charArray,charfreq,n);
  }
}

