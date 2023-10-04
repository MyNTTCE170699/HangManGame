/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javax.swing.SwingUtilities;

/**
 *
 * @author MY LAPTOP
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //                new Hangman().setVisible(true);
            new Intro().setVisible(true);
        });
    }
}
