import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A tokenizer that converts text input to lowercase and splits it 
 * into a list of tokens, where each token is either a word or a period.
 */
public class LowercaseSentenceTokenizer implements Tokenizer {
  /**
   * Tokenizes the text from the given Scanner. The method should 
   * convert the text to lowercase and split it into words and periods.
   * Words are separated by spaces, and periods are treated as separate tokens.
   * 
   * For example:
   * If the input text is: "Hello world. This is an example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an", "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  public List<String> tokenize(Scanner scanner) {
    // TODO: Implement this function to convert the scanner's input to a list of words and periods

    List<String> tokenList = new ArrayList<>(); //creating a List called tokenList to hold Strings
    while (scanner.hasNext()) { // looping through scanner input while there are more words

      String word = scanner.next().toLowerCase(); // reads the next input and assigns it to the String variable word/converts to lower case

      char lastChar = word.charAt(word.length() - 1); // Calculates the index of the last character word and retrieves/saved it in lastChar variable

      if (lastChar == '.') {
      tokenList.add(word.substring(0, word.length() - 1)); // Creates substring of word starting from index 0 to the last char of word not inclusive and adds it to the tokenList

      tokenList.add("."); // adds the period as a seperate token
      } else {
        tokenList.add(word); // adds the word to the tokenList when there isnt a period at the end
      }
    }
     return tokenList; // return the tokenList
  }
}

