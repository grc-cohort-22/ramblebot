import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

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
    // ("the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", ".")
    List<String> trainingWords = tokenizer.tokenize(scanner);
    // clear the hash map
    neighborMap = new HashMap<>();

    for (int i = 0; i < trainingWords.size() - 1; i++) {
      // current iteration of word
      String Word = trainingWords.get(i);
      String WordAfter = trainingWords.get(i + 1);
      //if the neighbormap does not have the key of the current iteration of words in it
      if (!neighborMap.containsKey(Word)) {
        if (WordAfter != null) {
          // create a key and value
          List<String> list = new ArrayList<>();
          list.add(WordAfter);
          neighborMap.put(Word, list);
        }
      }
      else {
        if (WordAfter != null) {
          List<String> Newlist = neighborMap.get(Word);
          Newlist.add(WordAfter);
          neighborMap.put(Word, Newlist);
        }
      }
        //grab the neighborMap Value section, aka grab the current list
        // List<String> Newlist = neighborMap.get(Word);
      
    }

    // neighborMap = Map.of(
    //     "the", List.of("cat", "cat", "dog"),
    //     "cat", List.of("sat", "slept"),
    //     ".", List.of("the", "the"),
    //     "dog", List.of("barked"),
    //     "sat", List.of("."),
    //     "slept", List.of("."),
    //     "barked", List.of(".")
    // );

    // TODO: Convert the trainingWords into neighborMap here
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
    // grab the end of the word from the parameter into a string
    String word = context.get(context.size() - 1);
    // check if the word is in the neighborMap
    if (neighborMap.containsKey(word)) {
      // grab the values of the words that comes next after the current word, which should be a list of words.
      List<String> WordValueList = neighborMap.get(word);
      // using random java, capture of one the words in the list based on the size. 1 divided by size?
      int size = WordValueList.size();
      Random rand = new Random();
      int randomIndex = rand.nextInt(size);
      // String randomWord = WordValueList.get(randomIndex);
      word = WordValueList.get(randomIndex);
    }
    // Hint: only the last word in context should be looked at
    return word;
    
  }

  // DEBUGGING
  // public static void main(String[] args) {
  //   FakeTokenizer fakeTokenizer = new FakeTokenizer(
  //           List.of("the", "quick", "brown", "fox", ".", "a", "quick", "red", "fox", ".", "the", "slow", "green", "turtle", ".")
  //   );
  //   UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
    
  //   predictor.train(null);
  //   String hi = predictor.predictNextWord(List.of("hi"));
  //   System.out.println(hi);
  // }
  
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
