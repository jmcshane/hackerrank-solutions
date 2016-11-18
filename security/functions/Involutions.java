import java.io.*;
import java.util.*;

public class Involutions {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.parseInt(in.nextLine().trim());
        int[] functionOutput = Arrays.stream(in.nextLine().trim().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        System.out.println((new Involutions()).getOutput(length, functionOutput));
    }

    public String getOutput(int length, int[] functionOutput) {
        Map<Integer,Integer> mapFunc = new HashMap<>();
        for (int i = 0; i < length; i++) {
            mapFunc.put(i + 1, functionOutput[i]);
        }
        boolean isInvolution = true;
        for (int i = 1; i <= length; i++) {
            if (i != mapFunc.get(mapFunc.get(i))) {
                isInvolution = false;
                break;
            }
        }
        if (isInvolution) {
            return "YES";
        } else {
            return "NO";
        }        
    }
}