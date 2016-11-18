import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BijectiveFunctions {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.parseInt(in.nextLine().trim());
        List<Integer> functionOutput = Arrays.stream(in.nextLine().trim().split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        System.out.println(isBijective(length, functionOutput));
    }

    public String isBijective(int length, List<Integer> functionOutput) {
        Set<Integer> uniqueness = new HashSet<>(functionOutput);
        if (functionOutput.size() > uniqueness.size()) {
            return "NO";
        }
        if (Collections.min(functionOutput) == 1 && Collections.max(functionOutput) == length) {
            return "YES";
        } else {
            return "NO";
        }

    }
}