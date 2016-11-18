import java.io.*;
import java.util.*;
import java.util.stream.*;

public class TranspositionCipher {

    private String key;
    private List<String> input;
    private Map<String,String> cipher;

    public TranspositionCipher(String key, List<String> input) {
        this.key = key;
        this.input = input;
        this.cipher = getCipher(key);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < length; i++) {
            String key = in.nextLine().trim();
            List<String> input = Arrays.stream(in.nextLine().trim().split(" ")).collect(Collectors.toList());
            Map<String,String> cipher = getCipher(key);
            System.out.println((new TranspositionCipher(key, input)).doTransformation());
        }
    }

    public String doTransformation() {
        return String.join(" ", input.stream()
                .map(j -> transform(j, cipher))
                .collect(Collectors.toList()));
    }
    
    private static String transform(String input, Map<String,String> cipher) {
        return String.join("",
            Arrays.stream(input.split("")).map(r -> cipher.get(r)).collect(Collectors.toList()));
    }
    
    private static Map<String,String> getCipher(String key) {
        Set<Integer> keyFiltered = Arrays.stream(key.split("")).map(k -> (int)k.charAt(0))
            .collect(Collectors.toCollection(LinkedHashSet::new));
        int keyLength = keyFiltered.size();
        Set<Integer> input = Stream.concat(keyFiltered.stream(),
            IntStream.rangeClosed(65, 90).mapToObj(e -> (Integer)e))
            .collect(Collectors.toCollection(LinkedHashSet::new ));
        List<List<Integer>> sorted = new ArrayList<>();
        IntStream.range(0, keyLength).forEach(i -> sorted.add(new ArrayList<>()));
        int i = 0;
        for (Integer ch : input) {
            sorted.get(i++ % keyLength).add(ch);            
        }
        Collections.sort(sorted, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) < o2.get(0) ? -1 : 1;
            }
        });
        int alphaIx = 65;
        Map<String,String> cipherMap = new HashMap<>();
        for (int j = 0; j < sorted.size(); j++) {
            for (int k = 0; k < sorted.get(j).size(); k++) {
                cipherMap.put(toCh(sorted.get(j).get(k)), toCh(alphaIx));
                alphaIx++;
            }
        }
        return cipherMap;
    }
    
    private static String toCh(int input) {
        return Character.toString((char)input);
    }
}