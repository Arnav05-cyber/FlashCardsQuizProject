import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashcardUI extends Application {
    private Label questionLabel;
    private Label answerLabel;
    private TextField answerInput;
    private Button showAnswerButton;
    private Button nextButton;
    private Button submitButton;

    private FlashCardManager flashCardManager;
    private QuizLogic quizLogic;
    private FlashCard currentCard;

    @Override
    public void start(Stage primaryStage) {
        flashCardManager = new FlashCardManager();
        quizLogic = new QuizLogic(flashCardManager);

        // Add some sample flashcards
        flashCardManager.addFlashCard("What is OOP?", "Object-Oriented Programming");
        flashCardManager.addFlashCard("What is Java?", "A programming language");
        flashCardManager.addFlashCard("What is a HashMap?", "A key-value data structure");

        // 📌 UI Components
        questionLabel = new Label("Click Next to Start");
        questionLabel.getStyleClass().add("question-label"); // Apply CSS

        answerLabel = new Label("");
        answerLabel.getStyleClass().add("answer-label");

        answerInput = new TextField();
        answerInput.setPromptText("Enter your answer...");
        answerInput.getStyleClass().add("answer-input");
        answerInput.setVisible(false); // Initially hidden

        showAnswerButton = new Button("Show Answer");
        showAnswerButton.getStyleClass().add("quiz-button");
        showAnswerButton.setVisible(false);

        nextButton = new Button("Next");
        nextButton.getStyleClass().add("quiz-button");

        submitButton = new Button("Submit Answer");
        submitButton.getStyleClass().add("quiz-button");
        submitButton.setVisible(false);

        // 🎯 Button Actions
        showAnswerButton.setOnAction(e -> {
            if (currentCard != null) {
                answerLabel.setText(currentCard.getAnswer());
            }
        });

        submitButton.setOnAction(e -> {
            if (currentCard != null) {
                String userAnswer = answerInput.getText();
                if (quizLogic.checkAnswer(currentCard.getQuestion(), userAnswer)) {
                    answerLabel.setText("✅ Correct!");
                } else {
                    answerLabel.setText("❌ Wrong! Correct Answer: " + currentCard.getAnswer());
                }
                answerInput.clear();
            }
        });

        nextButton.setOnAction(e -> {
            currentCard = flashCardManager.getFlashCard();
            if (currentCard != null) {
                questionLabel.setText(currentCard.getQuestion());
                answerLabel.setText("");
                answerInput.setVisible(true);
                submitButton.setVisible(true);
                showAnswerButton.setVisible(true);
                answerInput.clear();
            } else {
                questionLabel.setText("No more flashcards!");
                answerLabel.setText("");
                answerInput.setDisable(true);
            }
        });

        // 📌 Layout
        VBox layout = new VBox(15, questionLabel, nextButton, showAnswerButton, submitButton, answerInput, answerLabel);
        layout.getStyleClass().add("vbox");

        // Scene setup
        Scene scene = new Scene(layout, 450, 450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Attach CSS
        primaryStage.setTitle("Flashcard Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
