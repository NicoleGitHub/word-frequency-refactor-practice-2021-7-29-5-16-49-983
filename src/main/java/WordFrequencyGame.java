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

        if (isSentenceLengthEqualsOne(sentence)) {
            return sentence + NUMERIC_ONE_PATTERN;
        }

        try {
            List<WordInfo> wordInfos = calculateWordFrequency(sentence);
            sortWordInfo(wordInfos);
            return joiningWordsAsSentence(wordInfos);
        } catch (Exception e) {
            return "Calculate Error";
        }

    }

    private boolean isSentenceLengthEqualsOne(String sentence) {
        return sentence.split(SPACE_PATTERN).length==1;
    }

    private String joiningWordsAsSentence(List<WordInfo> wordInfos) {
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE_PATTERN);

        wordInfos.forEach(word -> {
            stringJoiner.add(word.getWord() + " " +word.getWordCount());
        });

        return stringJoiner.toString();
    }

    private void sortWordInfo(List<WordInfo> wordInfos) {
        wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = splitSentenceWithSpace(sentence);

        List<WordInfo> wordInfos = new ArrayList<>();
        findDistinctWords(words).forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, frequency));
        });

        return wordInfos;
    }

    private List<String> splitSentenceWithSpace(String sentence) {
        return Arrays.asList(sentence.split(SPACE_PATTERN));
    }

    private List<String> findDistinctWords(List<String> words) {
        return words.stream().distinct().collect(Collectors.toList());
    }

}