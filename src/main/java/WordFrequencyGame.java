import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String NEW_LINE_PATTERN = "\n";
    public static final String SPACING_PATTERN = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence){
        try {
            List<WordInfo> wordInfos = calculateWordFrequency(sentence);
            sortWordInfo(wordInfos);
            return joiningWordsAsSentence(wordInfos);

        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private String joiningWordsAsSentence(List<WordInfo> wordInfos) {
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE_PATTERN);

        wordInfos.forEach(word -> {
            stringJoiner.add(word.getWord() + SPACING_PATTERN + word.getWordCount());
        });

        return stringJoiner.toString();
    }

    private void sortWordInfo(List<WordInfo> wordInfos) {
        wordInfos.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = splitSentenceWithSpace(sentence);

        List<WordInfo> wordInfos = new ArrayList<>();
        findDistinctWords(words).forEach(distinctWord -> {
            wordInfos.add(new WordInfo(distinctWord, findDistinctWordsFrequencyFromWords(words, distinctWord)));
        });

        return wordInfos;
    }

    private int findDistinctWordsFrequencyFromWords(List<String> words, String distinctWord) {
        return Math.toIntExact(words.stream().filter(word -> word.equals(distinctWord)).count());
    }

    private List<String> splitSentenceWithSpace(String sentence) {
        return Arrays.asList(sentence.split(SPACE_PATTERN));
    }

    private List<String> findDistinctWords(List<String> words) {
        return words.stream().distinct().collect(Collectors.toList());
    }

}