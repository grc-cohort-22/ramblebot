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
      List<String> tokens = new ArrayList<>();
      //spliting the words which is one or more character (space, newline)
      scanner.useDelimiter("\\s+");
      //runs as long as there are more tokens in scanner input
      while (scanner.hasNext()) {
        //
        //scanner.next();
        // place to store the list of string in myString, reads the next token from input
      String myString = scanner.next();
       // all the output need to be in lowercase
      myString = myString.toLowerCase();
      // check if the string ends with period.
      
      
    boolean checkPeriod = myString.endsWith(".");
      /*
       if myString ends with a period. we want period to be a seperate token(words).
      fine + period.

       else add myString to tokens.
       
       "hello how are you" {"hello" , "how", "are", "you"}
        "I am fine."["i", "am","fine","."]
        "I am Dr.Smith"["i", "am","dr.smith"]
       */
        
       if(checkPeriod == true){
        //Find some way to remove last character from myString
        String myString2 = myString.substring(0, myString.length() - 1);
        tokens.add(myString2);
          tokens.add(".");
       }else{
       tokens.add(myString);
       }
   
    
     
      
      
      
      }
 
    return tokens;
  }
}

