import java.io.*;
import java.util.*;

public class FunctionsOne {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int mod = Integer.parseInt(scan.nextLine().trim()) % 11;
        System.out.println(mod);
    }
}