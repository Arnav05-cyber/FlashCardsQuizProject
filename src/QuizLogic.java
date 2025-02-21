import java.util.*;

public class QuizLogic {
    private FlashCardManager manager;
    private PriorityQueue<FlashCard> hardQuestions;

    public QuizLogic(){
        this.manager = new FlashCardManager();
        this.hardQuestions = new PriorityQueue<>(Comparator.comparing(FlashCard::getQuestion));
    }

    public void startQuiz(){
        Scanner sc = new Scanner(System.in);
        FlashCard card;

        while((card = manager.getFlashCard()) != null){
            System.out.println("Question; " + card.getQuestion());
            String userAnswer = sc.nextLine();

            if(manager.checkAnswer(card.getQuestion(),userAnswer)){
                System.out.println("Correct!");
            }
            else {
                System.out.println("Wrong! Correct Answer: " + card.getAnswer());
                hardQuestions.add(card);
            }
        }

        System.out.println("Reviewing hard questions...");
        while (!hardQuestions.isEmpty()) {
            FlashCard reviewCard = hardQuestions.poll();
            System.out.println("Question: " + reviewCard.getQuestion());
            sc.nextLine();
        }
        sc.close();
    }
}
