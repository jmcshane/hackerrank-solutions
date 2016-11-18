import java.io.*;
import java.util.*;
import java.util.stream.*;

public class PRNG {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer length = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < length; i++) {
            int[] times = Arrays.stream(in.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] nums = new int[10];
            for (int j = 0; j < 10; j++) {
                nums[j] = Integer.parseInt(in.nextLine().trim());
            }
            System.out.println((new PRNG()).printOutput(times, nums));
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    
    public String printOutput(int[] times, int[] nums) {
        List<Integer> possibleList = new ArrayList<>();
        for (int i = times[0]; i <= times[1]; i++) {
            if ((new Random(i)).nextInt(1000) == nums[0]) {
                possibleList.add(i);
            }
        }
        int solution = possibleList.stream()
            .filter(j -> {
                Random rand = new Random(j);
                for (int i =0; i < 10; i++) {
                    if (rand.nextInt(1000) != nums[i]) {
                        return false;
                    }
                }
                return true;
            }).findFirst().get();
        Random rand = new Random(solution);
        for (int i = 0; i < 10; i++) {
            rand.nextInt(1000);
        }
        List<Integer> output = new ArrayList<>();
        output.add(solution);
        for (int i = 0; i < 10; i++) {
            output.add(rand.nextInt(1000));
        }
        return String.join(" ", output.stream().map(String::valueOf).collect(Collectors.toList()));
    }
}