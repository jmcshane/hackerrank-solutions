import java.io.*;
import java.util.*;

public class KeySpace {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] originalInput = Arrays.stream(in.nextLine().trim().split(""))
            .mapToInt(Integer::parseInt).toArray();
        int shift = Integer.parseInt(in.nextLine().trim());
        System.out.println((new KeySpace()).shift(originalInput, shift));
    }

    public String shift(int[] originalInput, int shift) {
        for (int i = 0; i < originalInput.length; i++) {
            originalInput[i] = (originalInput[i] + shift) %10;
        }
        return Arrays.stream(originalInput)
            .mapToObj(String::valueOf).reduce((a,b) -> a+b).get();        
    }
}