import java.io.*;
import java.util.*;

public class EncryptionScheme {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input = Integer.parseInt(in.nextLine().trim());
        System.out.println(factorial(input));
    }
    
    private static int factorial(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return n * factorial(n - 1);
    }
}