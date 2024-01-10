// this is the clean code (after)
import java.util.*;

public class cleancode {
    public String getResult(String inputStr) {
        if (isSingleWord(inputStr)) {
            return inputStr + " 1";
        } else {
            try {
                String[] words = inputStr.split("\\s+");
                List<Input> inputList = createInputList(words);
                Map<String, List<Input>> wordCountMap = getWordCountMap(inputList);
                List<Input> sortedInputList = getSortedInputList(wordCountMap);
                return buildResultString(sortedInputList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private boolean isSingleWord(String input) {
        return input.split("\\s+").length == 1;
    }

    private List<Input> createInputList(String[] words) {
        List<Input> inputList = new ArrayList<>();
        for (String word : words) {
            inputList.add(new Input(word, 1));
        }
        return inputList;
    }

    private Map<String, List<Input>> getWordCountMap(List<Input> inputList) {
        Map<String, List<Input>> wordCountMap = new HashMap<>();
        for (Input input : inputList) {
            wordCountMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        }
        return wordCountMap;
    }

    private List<Input> getSortedInputList(Map<String, List<Input>> wordCountMap) {
        List<Input> sortedInputList = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : wordCountMap.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            sortedInputList.add(input);
        }
        sortedInputList.sort(Comparator.comparingInt(Input::getWordCount).reversed());
        return sortedInputList;
    }

    private String buildResultString(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input input : inputList) {
            joiner.add(input.getValue() + " " + input.getWordCount());
        }
        return joiner.toString();
    }
}
