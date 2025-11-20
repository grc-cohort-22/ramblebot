import java.util.*;

// had allot of free time on my way back so wanted to add this which uses two words to guess the next word i wanted to do it really bad so i did have some help from friends
public class BigramWordPredictor implements WordPredictor {
    private Map<String, List<String>> neighborMap;
    private Tokenizer tokenizer;

    // make a new bigram predictor
    public BigramWordPredictor(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.neighborMap = new HashMap<>();
    }

    // read the words and store what comes after each two word pair
    @Override
    public void train(Scanner scanner) {
        List<String> words = tokenizer.tokenize(scanner);
        neighborMap.clear();

        for (int i = 0; i < words.size() - 2; i++) {
            String first = words.get(i);
            String second = words.get(i + 1);
            String pair = first + " " + second;
            String next = words.get(i + 2);

            // here i need to make a new list if this pair was not seen yet
            if (!neighborMap.containsKey(pair)) {
                neighborMap.put(pair, new ArrayList<>());
            }

            // adding the next word to that pairs list
            neighborMap.get(pair).add(next);
        }
    }

    // pick a random next word based on last two words
    @Override
    public String predictNextWord(List<String> context) {
        if (context == null || context.size() < 2)
            return null;

        String first = context.get(context.size() - 2);
        String second = context.get(context.size() - 1);
        String pair = first + " " + second;

        List<String> list = neighborMap.get(pair);
        if (list == null || list.isEmpty())
            return null;

        int randomIndex = (int) (Math.random() * list.size());
        return list.get(randomIndex);
    }

    // returns a safe copy of the map
    public Map<String, List<String>> getNeighborMap() {
        Map<String, List<String>> copy = new HashMap<>();
        for (Map.Entry<String, List<String>> e : neighborMap.entrySet()) {
            copy.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        return copy;
    }
}
