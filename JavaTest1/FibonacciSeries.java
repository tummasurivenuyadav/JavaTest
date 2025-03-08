import java.util.Scanner;

public class FibonacciSeries {
    public static void printFibonacci(int n) {
        if (n <= 0) return;

        int a = 0, b = 1;  
        System.out.print(a); 

        if (n > 1) {
            System.out.print(" " + b);  
        }

        for (int i = 2; i < n; i++) {  
            int next = a + b;
            System.out.print(" " + next);
            a = b;
            b = next; 
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        scanner.close();

        printFibonacci(n);
    }
}
