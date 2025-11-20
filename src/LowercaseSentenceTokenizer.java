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

    // Create list to store the words.
    List<String> list = new ArrayList<>();

    // Declare a String variable.
    String token;
    //Test token: "Dr.Smith's"

    // Loop through, using hasNext to see if there is a available token, then add that token to the list. 
    while(scanner.hasNext()){
      token = scanner.next().toLowerCase(); //changes Dr.Smith's to lower case -> dr.smith's
      //first check to see if it contains a period.
      if (token.contains(".")) {
        //Declare and initialize both the current token with the period, then one cleaned only checking the end.
        
        String currentToken = "";
        String tokenCleaned = ""; 
        
        //loop through the current token and go through each character.
        //Test token: "dr.smith's"
        for (int i = 0; i < token.length(); i++) {
          //we will iterate through the current token and add the current char to the currentToken.
          //this will add all the chars without any filter.
          Character currentCharacter = token.charAt(i);
          currentToken += currentCharacter;

          //likewise this will do the same, but it will filter the period.  
          if (currentCharacter != '.') {
            tokenCleaned += currentCharacter;
          }
        }
        //after fully looping, we can save the last character of the current token to see if it is a period
        //only then will we add the cleaned token as well as its subsequent period.
        //after looping:
        //currentToken = dr.smith's 
        //tokenCleaned = drsmith's
        Character lastChar = currentToken.charAt(currentToken.length() - 1);
        //lastChar = s
        

        //lastChar 's' does not apply to the first if statment which means this isn't a token
        //that has a period as its final character, so we can just return the add the string
        //dr.smith's stored in our current token otherwise we would just add the tokenCleaned
        if (lastChar == '.') {
          list.add(tokenCleaned);
          list.add(".");
        } else {
          //since we know that the last character is not a period we can just add the Token so we can
          //get the full string "dr.smith's"
          list.add(currentToken);
        }
        
      }
      //Otherwise if it doesn't contain a period add the token
      else {
        list.add(token);
      }
      
    }
    //return the list of strings.
    return list;
  }
}

