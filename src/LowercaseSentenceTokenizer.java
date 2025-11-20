import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
    List<String> wordSet = new ArrayList<>();

    while (scanner.hasNext()) {
      String word = scanner.next().toLowerCase();
      // if word contains "." and if period is at the end.
      if (word.contains(".") && word.charAt(word.length() - 1) == '.' ) {
        // sperate word from period
        String trimmed = word.substring(0, word.length() - 1);
        //add the word
        wordSet.add(trimmed);
        // add the period.
        wordSet.add(".");
      }
      else {
        // else only add the word
        wordSet.add(word);
      }
    }
    return wordSet;
    
  }
  // DEBUGGING
  
  // public static void main(String[] args) {
  //   LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
  //   Scanner scanner = new Scanner("Hello world. This is Dr.Smith's example.");
  //   List<String> wordSet = new ArrayList<>();
  //   List<String> tokens = tokenizer.tokenize(scanner);
  //   System.out.println(wordSet);
    
  //   while (scanner.hasNext()) {
  //     String word = scanner.next();
  //     // if word contains "." and if period is at the end (word.length(i) == ".")

  //     if (word.contains(".") && word.charAt(word.length() - 1) == '.' ) {
  //       System.out.println(word.contains("."));
  //       System.out.println(word);
  //       // System.out.println(".");
  //       String trimmed = word.substring(0, word.length() - 1);
  //       //add the word
  //       wordSet.add(trimmed);
  //       // add the period.
  //       wordSet.add(".");
        
      
  //     }
  //     else {
  //       wordSet.add(word);
  //     }
  //   }
  //   System.out.println(wordSet);
  //   System.out.println(tokens);

  // }
}

