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

    public String getResult(String wordPattern){


        if (wordPattern.split(SPACE_PATTERN).length==1) {
            return wordPattern + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = wordPattern.split(SPACE_PATTERN);

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
