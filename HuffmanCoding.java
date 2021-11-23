import java.util.Scanner;

public class HuffmanCoding{

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1: for encoding, 2: for decoding");
        int input = sc.nextInt();
        if(input == 1){
            HuffmanEncoding.main(args);;
        }
        else if(input == 2){
            HuffmanDecoding.main(args);;
        }
        else {
            System.out.println("Sorry, try again");
        }

    }
}