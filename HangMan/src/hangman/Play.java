/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Play extends JFrame implements ActionListener {
    //counts numebr of incorrect guesses playes has made

    private int score = 0;
    private int incorrectGuesses = 0;

    private static final int MAX_INCORRECT_GUESSES = 6;
    private static final int SCORE_INCREMENT = 200;
    private static final int SCORE_DECREMENT = 20;

    //store the challenge from the WordDB here
    private String[] wordChallenge;

    private final WordDB wordDB;
    private JLabel hangmanImage, categoryLabel, hiddenWordLabel, resultLabel, scoreLabel, scoreDialogLabel, wordLabel;
    private final JButton[] letterButtons;
    private JDialog resultDialog;
    private final Font customFont;

    public Play() {
        super("Hangman"); //title
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonConstands.ICON_PATH)));
        setSize(CommonConstands.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);

        //init vars
        wordDB = new WordDB();
        letterButtons = new JButton[26];
        wordChallenge = wordDB.loadChallenge();
        customFont = CustomTools.createFont(CommonConstands.FONT_PATH);

        CustomTools.stopSound();

        createResultDialog();
        addGuiComponents();
    }

    private void addGuiComponents() {
        //hangman image
        hangmanImage = CustomTools.loadImage(CommonConstands.IMAGE_PATH);
        hangmanImage.setBounds(0, 0, hangmanImage.getPreferredSize().width, hangmanImage.getPreferredSize().height);

        //categrory display 
        categoryLabel = new JLabel(wordChallenge[0]);
        categoryLabel.setFont(customFont.deriveFont(30f));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setOpaque(true);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBackground(CommonConstands.SECONDARY_COLOR);
        categoryLabel.setBorder(BorderFactory.createLineBorder(CommonConstands.SECONDARY_COLOR));

        categoryLabel.setBounds(
                0,
                hangmanImage.getPreferredSize().height - 28,
                CommonConstands.FRAME_SIZE.width,
                categoryLabel.getPreferredSize().height
        );

        //score
        scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setFont(customFont.deriveFont(30f));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.GRAY);
        scoreLabel.setBorder(BorderFactory.createLineBorder(CommonConstands.SECONDARY_COLOR));

        scoreLabel.setBounds(
                0,
                categoryLabel.getY() + categoryLabel.getPreferredSize().height,
                CommonConstands.FRAME_SIZE.width,
                scoreLabel.getPreferredSize().height
        );

        //hidden word
        hiddenWordLabel = new JLabel(CustomTools.hiddenWords(wordChallenge[1]));
        hiddenWordLabel.setFont(customFont.deriveFont(64f));
        hiddenWordLabel.setForeground(Color.WHITE);
        hiddenWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenWordLabel.setBounds(
                0,
                categoryLabel.getY() + categoryLabel.getPreferredSize().height + 50,
                CommonConstands.FRAME_SIZE.width,
                hiddenWordLabel.getPreferredSize().height
        );

        //letter buttons
        GridLayout gridLayout = new GridLayout(4, 7);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(
                -5,
                hiddenWordLabel.getY() + hiddenWordLabel.getPreferredSize().height,
                CommonConstands.BUTTON_PANEL_SIZE.width,
                CommonConstands.BUTTON_PANEL_SIZE.height
        );
        buttonPanel.setLayout(gridLayout);

//        create the letter buttons
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));

            button.setBackground(Color.GRAY);
            button.setFont(customFont.deriveFont(22f));
            button.setForeground(Color.WHITE);
            button.setBackground(CommonConstands.PRIMARY_COLOR);
            button.setFont(customFont.deriveFont(22f));
            button.setForeground(Color.WHITE);

            button.addActionListener(this);

            //using ASCII values to calculate the current index
            int currentIndex = c - 'A';

            letterButtons[currentIndex] = button;
            buttonPanel.add(letterButtons[currentIndex]);
        }

        //reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(customFont.deriveFont(22f));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(CommonConstands.SECONDARY_COLOR);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        //quit button
        JButton menuButton = new JButton("Menu"); //close Play
        menuButton.setFont(customFont.deriveFont(22f));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(CommonConstands.SECONDARY_COLOR);
        menuButton.addActionListener(this);
        buttonPanel.add(menuButton);

        getContentPane().add(categoryLabel);
        getContentPane().add(hangmanImage);
        getContentPane().add(scoreLabel);
        getContentPane().add(hiddenWordLabel);
        getContentPane().add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Reset": {
                score = 0;
                scoreLabel.setText(String.valueOf(score));
//                CustomTools.stopSound();
                
                resultDialog.setVisible(false);
                resetGame();
            }
            break;
            case "Continue": {
                resultDialog.setVisible(false);
                resetGame();
            }
            break;
            case "Menu":
                new Menu().setVisible(true);
                dispose();
                break;
            default: //letter buttons 
                JButton button = (JButton) e.getSource();
                button.setEnabled(false);

                if (wordChallenge[1].contains(command)) {
                    CorrectGuess(button, command);
                } else {
                    IncorrectGuess(button);
                }
                wordLabel.setText("Answer: " + wordChallenge[1]);
                break;
        }
    }

    private void CorrectGuess(JButton button, String command) {
        button.setBackground(Color.GREEN);
        CustomTools.playSound(CommonConstands.CORRECT_PATH);
        score += SCORE_INCREMENT;
        updateScoreLabel();
        char[] hiddenWord = hiddenWordLabel.getText().toCharArray();
        for (int i = 0; i < wordChallenge[1].length(); i++) {
            if (wordChallenge[1].charAt(i) == command.charAt(0)) {
                hiddenWord[i] = command.charAt(0);
            }
        }
        hiddenWordLabel.setText(String.valueOf(hiddenWord));
        if (!hiddenWordLabel.getText().contains("*")) {
            CustomTools.playSound(CommonConstands.WIN_PATH);
            resultLabel.setForeground(Color.green);
            resultLabel.setText("YOU WIN!");
            scoreDialogLabel.setText("Score: " + score);
            resultDialog.setVisible(true);
        }
    }

    private void IncorrectGuess(JButton button) {
        button.setBackground(Color.RED);
        CustomTools.playSound(CommonConstands.INCORRECT_PATH);
        incorrectGuesses++;

//        if (incorrectGuesses == 4) {
//            CustomTools.playLoopSound(CommonConstands.WARNING_PATH);
//        }

        if (score > 0) {
            score -= SCORE_DECREMENT;
        }
        updateHangmanImage();
        updateScoreLabel();

        if (incorrectGuesses >= MAX_INCORRECT_GUESSES) {
            CustomTools.playSound(CommonConstands.LOST_PATH);
            resultLabel.setForeground(Color.red);
            resultLabel.setText("GAME OVER");
            scoreDialogLabel.setText("Score: " + score);
            resultDialog.setVisible(true);

            score = 0;
            scoreLabel.setText(String.valueOf(score));
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText(String.valueOf(score));
    }

    private void updateHangmanImage() {
        CustomTools.updateImage(hangmanImage, String.format("/resources/%d.png", incorrectGuesses + 1));
    }

    private void createResultDialog() {
        resultDialog = new JDialog();
        resultDialog.setTitle("Result");
        resultDialog.setSize(CommonConstands.RESULT_DIALOG_SIZE);
        resultDialog.getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);
        resultDialog.setResizable(false);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setModal(true); //not allowed to click other button until modal closed
        resultDialog.setLayout(new GridLayout(4, 1)); //3 rows, 1 columns
        resultDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resetGame();
            }
        });

        resultLabel = new JLabel();
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(customFont.deriveFont(30f));

        wordLabel = new JLabel();
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setFont(customFont.deriveFont(20f));

        scoreDialogLabel = new JLabel();
        scoreDialogLabel.setForeground(Color.WHITE);
        scoreDialogLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreDialogLabel.setFont(customFont.deriveFont(20f));

        JButton restartButton = new JButton("Continue");
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(CommonConstands.SECONDARY_COLOR);
        restartButton.setFont(customFont.deriveFont(20f));
        restartButton.addActionListener(this);

        resultDialog.add(resultLabel);
        resultDialog.add(wordLabel);
        resultDialog.add(scoreDialogLabel);
        resultDialog.add(restartButton);
    }

    private void resetGame() {
        //load new challenge
        wordChallenge = wordDB.loadChallenge();
        incorrectGuesses = 0;

        //load starting image
        CustomTools.stopSound();
        CustomTools.updateImage(hangmanImage, CommonConstands.IMAGE_PATH);

        //update category
        categoryLabel.setText(wordChallenge[0]);

        //update hiddenWord
        String hiddenWord = CustomTools.hiddenWords(wordChallenge[1]);
        hiddenWordLabel.setText(hiddenWord);

        //enable all buttons again
        for (JButton letterButton : letterButtons) {
            letterButton.setEnabled(true);
            letterButton.setBackground(CommonConstands.PRIMARY_COLOR);
        }
    }
}
