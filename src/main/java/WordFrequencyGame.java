import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_PATTERN);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String s : words) {
            WordInfo wordInfo = new WordInfo(s, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map = getListMap(wordInfoList);

        List<WordInfo> wordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            wordInfos.add(wordInfo);
        }
        return wordInfos;
    }
    
    private List<WordInfo> calculateWordFrequencyTemp(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
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
