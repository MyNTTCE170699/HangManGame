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
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author MY LAPTOP
 */
public class Menu extends JFrame implements ActionListener {

    private final Font customFont;

    private JLabel logoImage, quitLabel, versionLabel;
    private JDialog quitDialog;

    public Menu() {
        super("Hangman"); //title
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonConstands.ICON_PATH)));
        setSize(CommonConstands.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);

        customFont = CustomTools.createFont(CommonConstands.FONT_PATH);

        CustomTools.stopSound();
        CustomTools.playLoopSound(CommonConstands.THEME_PATH);

        createQuitDialog();
        addGuiComponents();
    }

    private void addGuiComponents() {
        logoImage = CustomTools.loadImage(CommonConstands.LOGO_PATH);
        logoImage.setBounds(0, 0, logoImage.getPreferredSize().width, logoImage.getPreferredSize().height);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 279, CommonConstands.FRAME_SIZE.width, CommonConstands.FRAME_SIZE.height - 309); //309 is thr psdding of the menu
        menuPanel.setLayout(new GridLayout(4, 1));

        List<JButton> buttons = Arrays.asList(
                createButton("PLAY", 22f, CommonConstands.SECONDARY_COLOR),
                createButton("ABOUT US", 22f, CommonConstands.SECONDARY_COLOR),
                createButton("HOW TO PLAY", 22f, CommonConstands.SECONDARY_COLOR),
                createButton("QUIT", 22f, CommonConstands.SECONDARY_COLOR)
        );

        // Add buttons to panel
        for (JButton button : buttons) {
            button.addActionListener(this);
            menuPanel.add(button);
        }

        getContentPane().add(logoImage);
        getContentPane().add(menuPanel);
    }

    private JButton createButton(String text, float fontSize, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(customFont.deriveFont(fontSize));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        return button;
    }

    private static final int DIALOG_ROWS = 3;
    private static final int DIALOG_COLUMNS = 1;

    private void createQuitDialog() {
        quitDialog = new JDialog();
        quitDialog.setTitle("Quit");
        quitDialog.setSize(CommonConstands.RESULT_DIALOG_SIZE);
        quitDialog.getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);
        quitDialog.setResizable(false);
        quitDialog.setLocationRelativeTo(this);
        quitDialog.setModal(true); //not allowed to click other button until modal closed
        quitDialog.setLayout(new GridLayout(DIALOG_ROWS, DIALOG_COLUMNS)); //3 rows, 1 columns
        quitDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Menu().setVisible(true);
                dispose();
            }
        });

        quitLabel = new JLabel();
        quitLabel.setForeground(Color.WHITE);
        quitLabel.setFont(customFont.deriveFont(22f));
        quitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quitLabel.setText("Are you sure you want to quit?");

        JButton yesButton = new JButton("Yes");
        yesButton.setForeground(Color.WHITE);
        yesButton.setBackground(CommonConstands.PRIMARY_COLOR);
        yesButton.setFont(customFont.deriveFont(22f));
        yesButton.addActionListener((ActionEvent e) -> {
            quitDialog.setVisible(false);
            new Quit().setVisible(true);
            dispose();
        });

        JButton noButton = new JButton("No");
        noButton.setForeground(Color.WHITE);
        noButton.setBackground(CommonConstands.SECONDARY_COLOR);
        noButton.setFont(customFont.deriveFont(22f));
        noButton.addActionListener((ActionEvent e) -> {
            quitDialog.setVisible(false);
            new Menu().setVisible(true);
            dispose();
        });

        quitDialog.add(quitLabel);
        quitDialog.add(yesButton);
        quitDialog.add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "PLAY":
                new Play().setVisible(true);
                break;
            case "QUIT":
                quitDialog.setVisible(true);
                break;
            case "ABOUT US":
                new AboutUs().setVisible(true);
                break;
            case "HOW TO PLAY":
                new HowToPlay().setVisible(true);
                break;
        }
        dispose();
    }
}
