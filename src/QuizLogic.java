import java.util.PriorityQueue;
import java.util.Comparator;

public class QuizLogic {
    private FlashCardManager manager;
    private PriorityQueue<FlashCard> hardQuestions;

    public QuizLogic(FlashCardManager manager) {
        this.manager = manager;
        this.hardQuestions = new PriorityQueue<>(Comparator.comparing(FlashCard::getQuestion));
    }

    public boolean checkAnswer(String question, String userAnswer) {
        boolean isCorrect = manager.checkAnswer(question, userAnswer);
        if (!isCorrect) {
            hardQuestions.add(new FlashCard(question, manager.getCorrectAnswer(question))); // Use getter
        }
        return isCorrect;
    }

    public void reviewHardQuestions() {
        System.out.println("Reviewing hard questions...");
        while (!hardQuestions.isEmpty()) {
            FlashCard reviewCard = hardQuestions.poll();
            System.out.println("Question: " + reviewCard.getQuestion());
            System.out.println("Correct Answer: " + reviewCard.getAnswer());
        }
    }
}
