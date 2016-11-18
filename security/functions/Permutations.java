import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Permutations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.parseInt(in.nextLine().trim());
        int[] functionOutput = Arrays.stream(in.nextLine().trim().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        System.out.println(String.join(" ", (new Permutations()).getPermutation(length, functionOutput)))
    }

    public List<Integer> getPermutation(int length, int[] functionOutput) {
        Map<Integer,Integer> mapFunc = new HashMap<>();
        for (int i = 0; i < length; i++) {
            mapFunc.put(i + 1, functionOutput[i]);
        }
        return IntStream.range(0, length)
            .mapToObj(i -> mapFunc.get(mapFunc.get(i)))
            .map(String::valueOf)
            .collect(Collectors.toList());
    }
}