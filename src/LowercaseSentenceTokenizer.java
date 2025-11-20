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
    
    String line = scanner.nextLine();
    List<String> tokens = new ArrayList<>();
    String wordBuild = "";
    
    for(int i = 0; i < line.length(); i++){
        char charToken = Character.toLowerCase(line.charAt(i));
        switch (charToken) {
            case '.':
                //detects if at end of array.
                if (i+1 >= line.length()) {
                    tokens.add(wordBuild);
                    tokens.add(charToken+"");
                    wordBuild = "";
                    break;
                }else{
                //if its not, is the next character a space?
                    if(line.charAt(i+1) != ' '){
                        //if not, add the period to the build and carry on.
                        wordBuild += charToken;
                        break;
                    }else{
                        //if so, add the word, dump it and add the token.
                        tokens.add(wordBuild);
                        wordBuild = "";
                        tokens.add((""+charToken));
                        break;
                    }
                }
            
            case ' ':
                if(!wordBuild.isBlank()){
                    tokens.add(wordBuild);
                    wordBuild = "";
                    break;
                }else{
                    break;
                }
            default:
                wordBuild += charToken;
                break;
        }
    }
    if (!wordBuild.isEmpty()) {
      tokens.add(wordBuild);
    }
    return tokens;
  }
}

