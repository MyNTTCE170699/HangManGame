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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MY LAPTOP
 */
public class HowToPlay extends JFrame implements ActionListener{
    
    private JPanel buttonPanel;
    private JLabel howToPLayImage;
    private Font customFont;

    public HowToPlay() {
        super("Hoangman");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonConstands.ICON_PATH)));
        
        setSize(CommonConstands.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstands.BACKGROUND_COLOR);

        customFont = CustomTools.createFont(CommonConstands.FONT_PATH);
        
        addGuiComponents();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("MENU")) {
            new Menu().setVisible(true);
            dispose();
        }
    }

    private void addGuiComponents() {
        howToPLayImage = CustomTools.loadImage(CommonConstands.HOWTOPLAY_PATH);
        howToPLayImage.setBounds(0, 0, howToPLayImage.getPreferredSize().width, CommonConstands.FRAME_SIZE.height);
    
        buttonPanel = new JPanel();
        buttonPanel.setBackground(CommonConstands.BACKGROUND_COLOR);
        buttonPanel.setBounds(0,
                CommonConstands.FRAME_SIZE.height*9/10,
                CommonConstands.FRAME_SIZE.width,
                CommonConstands.FRAME_SIZE.height/10
        );

        JButton menuButton = new JButton("MENU");
        menuButton.setFont(customFont.deriveFont(22f));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(CommonConstands.SECONDARY_COLOR);
        menuButton.addActionListener(this);
        buttonPanel.add(menuButton);

        getContentPane().add(howToPLayImage);
        getContentPane().add(buttonPanel);
    }
    
}
