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
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an",
   * "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as
   * a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at
   * the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is",
   * "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it
   * does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  // for wave 2 i rewrote my method to match the new requirement
  @Override
  public List<String> tokenize(Scanner scanner) {
    List<String> tokens = new ArrayList<>();

    // here i read every word in the file
    while (scanner.hasNext()) {
      String part = scanner.next();
      if (part == null)
        continue;

      // make all lower case
      part = part.toLowerCase().trim();
      if (part.isEmpty())
        continue;

      // here i check if there are dots at the end
      int cut = part.length();
      while (cut > 0 && part.charAt(cut - 1) == '.') {
        cut--;
      }

      // this is the main word before the dots
      String word = part.substring(0, cut).trim();
      if (!word.isEmpty()) {
        tokens.add(word);
      }

      // here i add one period token for every and each dot that was at the end.
      for (int i = cut; i < part.length(); i++) {
        if (part.charAt(i) == '.') {
          tokens.add(".");
        }
      }
    }

    return tokens;
  }

}
