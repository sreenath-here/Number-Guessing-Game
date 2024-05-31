import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NumberGuessingGame extends JFrame implements ActionListener {

    private JLabel titleLabel, messageLabel, guessLabel;
    private JTextField guessField;
    private JButton guessButton, newGameButton;
    private int randomNumber, guesses;

    public NumberGuessingGame() {
        super("Number Guessing Game");

        // Set layout and background color
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        getContentPane().setBackground(new Color(230, 255, 255)); // Light blue

        // Title label
        titleLabel = new JLabel("Guess the Number (1-10)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel);

        // Message label
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        add(messageLabel);

        // Guess label and field
        guessLabel = new JLabel("Enter your guess:");
        guessLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(guessLabel);
        guessField = new JTextField(5);
        guessField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(guessField);

        // Guess button
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.setBackground(new Color(153, 204, 255)); // Light blue
        guessButton.addActionListener(this);
        add(guessButton);

        // New game button
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGameButton.setBackground(new Color(255, 204, 153)); // Light pink
        newGameButton.setEnabled(false); // Initially disabled
        newGameButton.addActionListener(this);
        add(newGameButton);

        // Start a new game
        startGame();
    }

    public void startGame() {
        randomNumber = (int) (Math.random() * 10) + 1; // Generate random number (1-10)
        guesses = 0;
        messageLabel.setText("");
        guessField.setText("");
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                if (guess < 1 || guess > 10) {
                    messageLabel.setText("Guess must be between 1 and 10!");
                } else {
                    guesses++;
                    if (guess == randomNumber) {
                        messageLabel.setText("You guessed it in " + guesses + " tries! Play again?");
                        guessButton.setEnabled(false);
                        newGameButton.setEnabled(true);
                    } else if (guess > randomNumber) {
                        messageLabel.setText("Too high! Try again.");
                    } else {
                        messageLabel.setText("Too low! Try again.");
                    }
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Please enter a number.");
            }
        } else if (e.getSource() == newGameButton) {
            startGame();
        }
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.setSize(400, 200);
        game.setLocationRelativeTo(null); // Center the window
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
