import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * A class for predicting the next word in a sequence using a unigram model.
 * The model is trained on input text and maps each word to a list of 
 * words that directly follow it in the text.
 */
public class UnigramWordPredictor implements WordPredictor {
  private Map<String, List<String>> neighborMap;
  private Tokenizer tokenizer;

  /**
   * Constructs a UnigramWordPredictor with the specified tokenizer.
   * 
   * @param tokenizer the tokenizer used to process the input text
   */
  public UnigramWordPredictor(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  /**
   * Trains the predictor using the text provided by the Scanner.
   * The method tokenizes the text and builds a map where each word 
   * is associated with a list of words that immediately follow it 
   * in the text. The resultant map is stored in the neighborMap
   * instance variable.
   * 
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * After tokenizing, the tokens would be: ["the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", "."]
   * 
   * The resulting map (neighborMap) would be:
   * {
   *   "the" -> ["cat", "cat", "dog"],
   *   "cat" -> ["sat", "slept"],
   *   "sat" -> ["."],
   *   "." -> ["the", "the"],
   *   "slept" -> ["."],
   *   "dog" -> ["barked"],
   *   "barked" -> ["."]
   * }
   * 
   * The order of the map and the order of each list is not important.
   * 
   * @param scanner the Scanner to read the training text from
   */
  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);
    // TODO: Convert the trainingWords into neighborMap here
    // create a empty hashmap, the key will be string while the value will be string
     Map<String, List<String>> possiblites = new HashMap<>();

    // first we loop through the training words going through each word in the list
    // next place the first token as the key while making the value a new ArrayList
    for(String word : trainingWords){
      possiblites.put(word, new ArrayList<>());
    }
    // here we want to save the values in place1 and place2, in this case we are getting the i and i+1 places
    // so we get pairs we can set respectively to keys and values, I also -1 on the size() as to not get an error
    for (int i = 0; i < trainingWords.size()-1; i++){
      // Store follow up words as key/values
        String key = trainingWords.get(i);
        String value = trainingWords.get(i+1);

        // loop through using entryset
        for (Map.Entry<String, List<String>> sorting : possiblites.entrySet()){
          // since we have an entry of each token stored in possiblites as a key
          // all we have to do is compare the key using equals to the keys in possiblites
            if(sorting.getKey().equals(key)){
              // we can then can acess the list of sortings key/value pair and directly add it to that specfic list
              sorting.getValue().add(value);
            }
        }
    }
    //link neighborMap to my HashMap
    this.neighborMap = possiblites;
  }

  /**
   * Predicts the next word based on the given context.
   * The prediction is made by randomly selecting from all words 
   * that follow the last word in the context in the training data.
   * 
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * 
   * The resulting map (neighborMap) would be:
   * {
   *   "the" -> ["cat", "cat", "dog"],
   *   "cat" -> ["sat", "slept"],
   *   "sat" -> ["."],
   *   "." -> ["the", "the"],
   *   "slept" -> ["."],
   *   "dog" -> ["barked"],
   *   "barked" -> ["."]
   * }
   * 
   * When predicting the next word given a context, the predictor should use 
   * the neighbor map to select a word based on the observed frequencies in 
   * the training data. For example:
   * 
   * - If the last word in the context is "the", the next word should be randomly chosen 
   *   from ["cat", "cat", "dog"]. In this case, "cat" has a 2/3 probability 
   *   of being selected, and "dog" has a 1/3 probability, reflecting the 
   *   original distribution of words following "the" in the text.
   * 
   * - If the last word in the context is "cat", the next word should be randomly chosen 
   *   from ["sat", "slept"], giving each an equal 1/2 probability.
   * 
   * - If the last word in the context is ".", the next word should be randomly chosen 
   *   from ["the", "the"], meaning "the" will always be selected 
   *   since it's the only option.
   * 
   * - If the last word in the context is "dog", the next word should be "barked" because 
   *   "barked" is the only word that follows "dog" in the training data.
   * 
   * The probabilities of selecting each word should match the relative 
   * frequencies of the words that follow in the original training data. 
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */
  public String predictNextWord(List<String> context) {
    // TODO: Return a predicted word given the words preceding it
    // Hint: only the last word in context should be looked at
    /*
      store the size and random number to compare later
      get frequencies of each word divide that by the size to get its probablity
      store back inside map as <String, Double>
      loop through again check the target random and check it against all the probablites
      if statement to decide which word to predict next word
     */
    int size = context.size();
    Double random = Math.random();
    String predictedWord = "";

    // Creates a map with exact amount of Probability frequencies
    // This essentially does the same thing as tracking frequency but where multipleing by the orignal size and add 1 to update the value correctly
    // Then we can divide and put it in the hashmap again with the correct value.
    Map<String, Double> wordProbability = new HashMap<>();
    for(String word: context){
        if (!wordProbability.containsKey(word)) {
            wordProbability.put(word, 1.0 / size);
        } else {
            wordProbability.put(word, ((wordProbability.get(word) * size + 1)) / size);
        }
    }
    // Got stuck here 
    for(Map.Entry<String, Double> checkProbability : wordProbability.entrySet()){
        Double currentProbability = checkProbability.getValue();
        if (currentProbability >= random){
            predictedWord = checkProbability.getKey();
        } else {
            predictedWord = checkProbability.getKey();
        }
    }
    return predictedWord;
  }
  
  /**
   * Returns a copy of the neighbor map. The neighbor map is a mapping 
   * from each word to a list of words that have followed it in the training data.
   * 
   * You do not need to modify this method for your project.
   * 
   * @return a copy of the neighbor map
   */
  public Map<String, List<String>> getNeighborMap() {
    Map<String, List<String>> copy = new HashMap<>();

    for (Map.Entry<String, List<String>> entry : neighborMap.entrySet()) {
      List<String> newList = new ArrayList<>(entry.getValue());
      copy.put(entry.getKey(), newList);
    }

    return copy;
  }
}
