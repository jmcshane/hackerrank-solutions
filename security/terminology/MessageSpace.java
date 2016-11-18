import java.io.*;
import java.util.*;

public class MessageSpace {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] originalInput = Arrays.stream(in.nextLine().trim().split(""))
            .mapToInt(Integer::parseInt).toArray();
        System.out.println((new MessageSpace()).getOutput(originalInput));
    }

    public String getOutput(int[] originalInput) {
        for (int i = 0; i < originalInput.length; i++) {
            originalInput[i] = (originalInput[i] + 1) %10;
        }
        String output = Arrays.stream(originalInput)
            .mapToObj(String::valueOf).reduce((a,b) -> a+b).get();
        return output;        
    }
}