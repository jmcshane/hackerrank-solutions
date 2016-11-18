import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Cryptanalysis {

    public static void main(String[] args) {
        List<String> input = Arrays.stream((new Scanner(System.in)).nextLine().trim().split(" "))
            .collect(Collectors.toList());
        List<String> dictionary = null;
        try {
            dictionary = Files.lines(Paths.get("dictionary.lst"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        } catch(IOException e){
            
        }
        System.out.println((new Cryptanalysis()).getTranslation(input, dictionary));
    }
    
    private String getTranslation(List<String> input, List<String> dictionary) {
        Set<String> inputSet = new HashSet<>(input);
        Map<String,List<String>> isomorphicStrings = new HashMap<>();
        for (String val : inputSet) {
            isomorphicStrings.put(val, dictionary.stream()
                .filter(d -> isIsomorphic(d,val)).collect(Collectors.toList()));
        }
        //Map<String,List<String>> isomorphicStrings = inputSet.stream()
        //    .collect(Collectors.toMap(s -> s, s -> dictionary.stream().filter(d -> isIsomorphic(d,s))));
        List<String[]> pairs = isomorphicStrings.entrySet().stream()
            .filter(e -> e.getValue().size() == 1)
            .map(e -> new String[]{e.getKey(), e.getValue().get(0)})
            .collect(Collectors.toList());
        Map<String,String> cipher = new HashMap<>();
        int k = 0;
        for (int i = 0; i < pairs.size(); i++) {
            String encrypted = pairs.get(i)[0];
            String word = pairs.get(i)[1];
            k += addPairs(cipher, encrypted, word);
            if (k == 26) {
                break;
            }
        }
        input.stream()
            .forEach(wrd -> increaseCipherSize(isomorphicStrings.get(wrd), cipher, wrd));
        return input.stream().map(wrd -> translate(cipher, wrd))
            .reduce((a,b) -> a + " " + b).get();
    }
    
    private void increaseCipherSize(List<String> possibleWords, Map<String,String> cipher, String word) {
        if (possibleWords.size() == 1) {
            return;
        }
        boolean isComplete = true;
        String[] splitWord = word.split("");
        for (String ch : splitWord) {
            if (!cipher.containsKey(ch)) {
                isComplete = false;
            } 
        }
        if (isComplete) {
            return;
        }

        for (int i = 0; i < splitWord.length; i++) {
            final int k = i;
            if (cipher.containsKey(splitWord[i])) {
                final String matchChar = cipher.get(splitWord[i]);
                possibleWords = possibleWords.stream().filter(w -> w.split("")[k].equals(matchChar))
                    .collect(Collectors.toList());
                if (possibleWords.size() == 1) {
                    break;
                }
            }
        }

        if (possibleWords.size() == 1) {
            for (int i = 0; i < splitWord.length; i++) {
                if (!cipher.containsKey(splitWord[i])) {
                    cipher.put(splitWord[i], possibleWords.get(0).split("")[i]);
                }
            }
        }
        
    }
    
    private String translate(Map<String,String> cipher, String word) {
        return Arrays.stream(word.split(""))
            .map(ch -> cipher.get(ch))
            .reduce((a,b) -> a + b).get();
    }
    
    private int addPairs(Map<String,String> cipher, String encrypted, String word) {
        int counter = 0;
        String[] encChars = encrypted.split("");
        String[] wordChars = word.split("");
        for (int i = 0; i < encChars.length; i++) {
            if (!cipher.containsKey(encChars[i])) {
                cipher.put(encChars[i], wordChars[i]);
                counter++;
            }
        }
        return counter;
    }
    
    private boolean isIsomorphic(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String[] s1Split = s1.split("");
        String[] s2Split = s2.split("");
        List<String> s1Seen = new ArrayList<>();
        List<String> s2Seen = new ArrayList<>();
        for (int i =0; i < s1.length(); i++) {
            if (s1Seen.contains(s1Split[i])) {
                if (s2Seen.contains(s2Split[i])) {
                    if (s1Seen.indexOf(s1Split[i]) != s2Seen.indexOf(s2Split[i])) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (s2Seen.contains(s2Split[i])) {
                    return false;
                } else {
                    s1Seen.add(s1Split[i]);
                    s2Seen.add(s2Split[i]);
                }
            }
        }
        return true;
    }
}