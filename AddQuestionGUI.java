package ClassesOfProject.quiz_question;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;

public class AddQuestionGUI extends JFrame {
    private JTextField questionField;
    private JTextField[] optionFields;
    private JTextField answerField;
    private JButton submitButton;

    public AddQuestionGUI(DataBaseManager db) {
        setTitle("Add Custom Question");
        setSize(400, 350);
        setLayout(new GridLayout(7, 2));

        questionField = new JTextField();
        optionFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            optionFields[i] = new JTextField();
        }
        answerField = new JTextField();
        submitButton = new JButton("Add Question");

        add(new JLabel("Question:"));
        add(questionField);
        for (int i = 0; i < 4; i++) {
            add(new JLabel("Option " + (i + 1) + ":"));
            add(optionFields[i]);
        }
        add(new JLabel("Correct Answer (exactly as typed above):"));
        add(answerField);
        add(new JLabel(""));
        add(submitButton);

        submitButton.addActionListener(e -> {
            String question = questionField.getText();
            String[] options = Arrays.stream(optionFields).map(JTextField::getText).toArray(String[]::new);
            String answer = answerField.getText();

            if (question.isEmpty() || answer.isEmpty() || Arrays.stream(options).anyMatch(String::isEmpty)) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {
                db.insertQuestion(question, options, answer);
                JOptionPane.showMessageDialog(this, "Question added successfully!");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding question: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}

