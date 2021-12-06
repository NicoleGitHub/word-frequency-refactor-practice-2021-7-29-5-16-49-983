import java.util.*;
import java.util.stream.Collectors;

// naming
// magic string
// temp var
// for loop
// long method
// if/else

// duplicate code -> else {}

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String NEW_LINE_PATTERN = "\n";
    public static final String NUMERIC_ONE_PATTERN = " 1";

    public String getResult(String sentence){


        if (sentence.split(SPACE_PATTERN).length==1) {
            return sentence + NUMERIC_ONE_PATTERN;
        } else {

            try {

                List<WordInfo> wordInfos = calculateWordFrequency(sentence);

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner stringJoiner = new StringJoiner(NEW_LINE_PATTERN);
                for (WordInfo wordInfo : wordInfos) {
                    String s = wordInfo.getWord() + " " +wordInfo.getWordCount();
                    stringJoiner.add(s);
                }
                return stringJoiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }
    
    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, frequency);

            wordInfos.add(wordInfo);
        });

        return wordInfos;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> stringWordInfoListHashMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfos){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!stringWordInfoListHashMap.containsKey(wordInfo.getWord())){
                ArrayList newWordInfos = new ArrayList<>();
                newWordInfos.add(wordInfo);
                stringWordInfoListHashMap.put(wordInfo.getWord(), newWordInfos);
            }

            else {
                stringWordInfoListHashMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return stringWordInfoListHashMap;
    }


}
