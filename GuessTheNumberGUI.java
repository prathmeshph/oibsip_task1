import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel messageLabel;
    private JTextField guessField;
    private JButton submitButton;
    private JButton playAgainButton;
    private JLabel scoreLabel;

    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public GuessTheNumberGUI() {
        setTitle("Guess the Number");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        titleLabel = new JLabel("Guess the Number");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        messageLabel = new JLabel("I've generated a number between 1 and 100.");
        add(messageLabel);

        guessField = new JTextField(10);
        add(guessField);

        submitButton = new JButton("Submit");
        add(submitButton);

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        setVisible(true);

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 5;
        score = 0;
    }

    private void processGuess() {
        int guess = Integer.parseInt(guessField.getText());
        attempts++;

        if (guess == secretNumber) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed the number in " + attempts + " attempt(s)!");
            score += maxAttempts - attempts + 1;
            scoreLabel.setText("Score: " + score);
            showPlayAgainDialog();
        } else if (guess < secretNumber) {
            messageLabel.setText("Too low! Guess higher.");
        } else {
            messageLabel.setText("Too high! Guess lower.");
        }

        if (attempts >= maxAttempts) {
            JOptionPane.showMessageDialog(this, "Game over! The number was: " + secretNumber);
            showPlayAgainDialog();
        }

        guessField.setText("");
        guessField.requestFocus();
    }

    private void showPlayAgainDialog() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            initializeGame();
            messageLabel.setText("I've generated a number between 1 and 100.");
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGUI();
            }
        });
    }
}
