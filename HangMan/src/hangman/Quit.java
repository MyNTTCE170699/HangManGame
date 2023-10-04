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
public class Quit extends JFrame implements ActionListener {

    private JPanel buttonPanel;
    private JLabel quitImage;
    private Font customFont;

    public Quit() {
        super("Hangman");

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonConstands.ICON_PATH)));

        setSize(CommonConstands.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);
        
        // Schedule a timer task to close the frame after 5 seconds
        Timer timer = new Timer();
        timer.schedule(new CloseTask(), 5000);

        customFont = CustomTools.createFont(CommonConstands.FONT_PATH);
        
        addGuiComponents();
        CustomTools.stopSound();
        CustomTools.playSound(CommonConstands.SHUTDOWN_PATH);
    }

    private void addGuiComponents() {
        quitImage = CustomTools.loadImage(CommonConstands.QUIT_PATH);
        quitImage.setBounds(0, 0, quitImage.getPreferredSize().width, CommonConstands.FRAME_SIZE.height);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(CommonConstands.BACKGROUND_COLOR);
        buttonPanel.setBounds(0,
                CommonConstands.FRAME_SIZE.height,
                CommonConstands.FRAME_SIZE.width,
                CommonConstands.FRAME_SIZE.height / 10
        );

//        JButton menuButton = new JButton("CONFIRM TO QUIT");
//        menuButton.setFont(customFont.deriveFont(22f));
//        menuButton.setForeground(Color.WHITE);
//        menuButton.setBackground(CommonConstands.SECONDARY_COLOR);
//        menuButton.addActionListener(this);
//        buttonPanel.add(menuButton);

        getContentPane().add(quitImage);
//        getContentPane().add(buttonPanel);
    }
    
    private class CloseTask extends TimerTask {
        public void run() {
            dispose();
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 
        if (command.equals("CONFIRM TO QUIT")) {
            dispose();
            
            System.exit(0);
        }
    }

}
