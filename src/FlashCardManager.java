import java.util.*;

public class FlashCardManager {
    private Map<String,String> flashcards;
    private Queue<FlashCard> reviewQueue;

    public FlashCardManager(){
        flashcards = new HashMap<>();
        reviewQueue = new LinkedList<>();
    }

    public void addFlashCard(String question, String answer){
        flashcards.put(question,answer);
        reviewQueue.offer(new FlashCard(question,answer));
    }

    public FlashCard getFlashCard(){
        return reviewQueue.poll();
    }

    public boolean checkAnswer(String question, String userAnswer){
        return flashcards.getOrDefault(question, "").equalsIgnoreCase(userAnswer);
    }
}
