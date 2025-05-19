import java.sql.*;
import java.util.*;

public class DataBaseManager {
    private Connection connection;

    public DataBaseManager() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/game?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "@Waseem3534"; // Set your DB password
        connection = DriverManager.getConnection(url, user, password);
    }

    public List<QuizQuestion> getAllQuestions() throws SQLException {
        List<QuizQuestion> questions = new ArrayList<>();
        String query = "SELECT * FROM quiz_questions";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String question = rs.getString("question");

            List<String> optionsList = new ArrayList<>();
            optionsList.add(rs.getString("option1"));
            optionsList.add(rs.getString("option2"));
            optionsList.add(rs.getString("option3"));
            optionsList.add(rs.getString("option4"));

            String correctAnswer = rs.getString("answer");

            // Shuffle options
            Collections.shuffle(optionsList);

            String[] shuffledOptions = optionsList.toArray(new String[0]);

            questions.add(new QuizQuestion(id, question, shuffledOptions, correctAnswer));
        }

        return questions;
    }

    public void insertQuestion(String question, String[] options, String answer) throws SQLException {
        String sql = "INSERT INTO quiz_questions (question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, question);
        for (int i = 0; i < 4; i++) {
            stmt.setString(i + 2, options[i]);
        }
        stmt.setString(6, answer);
        stmt.executeUpdate();
    }
}





   
