package ClassesOfProject.quiz_question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizGameGUI extends JFrame implements ActionListener {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    public QuizGameGUI(List<QuizQuestion> questions) {
        this.questions = questions;

        setTitle("Quiz Game");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here");
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        displayQuestion();

        setVisible(true);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion q = questions.get(currentQuestionIndex);
            questionLabel.setText(q.getQuestion());
            String[] opts = q.getOptions();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(opts[i]);
            }
            optionsGroup.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QuizQuestion currentQ = questions.get(currentQuestionIndex);
        for (JRadioButton btn : optionButtons) {
            if (btn.isSelected() && btn.getText().equals(currentQ.getAnswer())) {
                score++;
                break;
            }
        }
        currentQuestionIndex++;
        displayQuestion();
    }
}

