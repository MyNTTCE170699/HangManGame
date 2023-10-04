/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author MY LAPTOP
 */
public class Intro extends JFrame implements ActionListener {

    private final Timer timer;
    private final Font customFont;
    private JLabel introImage;
    

    public Intro() {
        super("Hangman");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonConstands.ICON_PATH)));
        setSize(CommonConstands.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);

        customFont = CustomTools.createFont(CommonConstands.FONT_PATH);

        addGuiComponents();
        CustomTools.playSound(CommonConstands.INTROSOUND_PATH);

        // Close the frame after set amount of seconds
        timer = new Timer();
        timer.schedule(new CloseFrame(), 4000);
    }

    private void addGuiComponents() {
        introImage = CustomTools.loadImage(CommonConstands.INTROPIC_PATH);
        introImage.setBounds(0, 0, introImage.getPreferredSize().width, CommonConstands.FRAME_SIZE.height);
        getContentPane().add(introImage);
    }

    private class CloseFrame extends TimerTask {
        public void run() {
            dispose();
            new Menu().setVisible(true);
        }
    }
    

    public void actionPerformed(ActionEvent e) {
    }
}
