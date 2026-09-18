import java.util.Scanner;

public class arrayScanner {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter array size: ");
        int n = sc.nextInt();

        int [] numbers = new int[n];
        int sum = 0;
        System.out.println("Enter elements");
        for(int i = 0; i < n; i++){
            numbers[i] =sc.nextInt();
        }

        System.out.println("Array elelments: ");
        for(int i = 0; i < numbers.length; i++){
            System.out.println(sum+= numbers[i]);
        }
    }   
}
