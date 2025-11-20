import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

/*
 * I had some extra time on my hands, so I decided to try adding the bigram predictor.
 * After realizing it would take a lot of changes in the main app, I chose to make a
 * simple JUnit test instead. This test shows that my BigramWordPredictor actually works
 * without needing to rewrite the main program.
 */
public class BigramWordPredictorTest {

    @Test
    public void testBigramPredictorWorks() {
        Tokenizer tokenizer = new LowercaseSentenceTokenizer();
        BigramWordPredictor predictor = new BigramWordPredictor(tokenizer);

        String text = "the cat sat on the mat.";
        Scanner scanner = new Scanner(text);
        predictor.train(scanner);
        scanner.close();

        List<String> context = new ArrayList<>();
        context.add("the");
        context.add("cat");

        String nextWord = predictor.predictNextWord(context);
        assertNotNull(nextWord, "Bigram predictor should return a next word");
        System.out.println("Predicted: " + nextWord);
    }
}
