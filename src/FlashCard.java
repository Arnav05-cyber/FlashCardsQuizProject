public class FlashCard {
    private String Question;
    private String answer;

    public FlashCard(String question, String answer){
        this.Question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswer() {
        return answer;
    }
}
