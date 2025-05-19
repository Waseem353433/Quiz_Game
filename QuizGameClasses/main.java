import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataBaseManager db = new DataBaseManager();

            String[] options = {"Play Quiz", "Add Question"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Quiz Game",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                List<QuizQuestion> questions = db.getAllQuestions();
                Collections.shuffle(questions);
                new QuizGameGUI(questions);
            } else if (choice == 1) {
                new AddQuestionGUI(db);
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}