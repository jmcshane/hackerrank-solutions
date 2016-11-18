import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Inverses {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.parseInt(in.nextLine().trim());
        List<Integer> input = Arrays.stream(in.nextLine().trim().split(" "))
            .map(Integer::parseInt).collect(Collectors.toList());
        System.out.println((new Inverses()).getInverseFunction(length, input));
    }

    public List<String> getInverseFunction(int length, List<Integer> input) {
        List<Pair> output = new ArrayList<>();
        forEachIndexed(input.stream(), (i,elt) -> output.add(new Pair(elt, i + 1)));
        Collections.sort(output, new Comparator<Pair>() {
            @Override
            public int compare(Pair one, Pair two) {
                return one.getA() > two.getA() ? 1 : -1;
            }
        });
        output.map(o -> o.getB()).reduce((a,b) -> a + "\n" + b);

    }
    
    private static <T> void forEachIndexed(Stream<T> stream, BiConsumer<Integer, T> consumer) {
        stream.reduce(0, (index, t) -> {
            consumer.accept(index, t);
            return index + 1;
        }, Integer::max);
    }
    
    static class Pair {
        private int a;
        private int b;
        
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        public int getA() {
            return a;
        }
        
        public int getB() {
            return b;
        }
    }
}