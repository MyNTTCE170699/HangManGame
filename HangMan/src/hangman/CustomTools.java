/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author MY LAPTOP
 */
class CustomTools {
    private static Clip clip;
    //create a JLabel with an image
    public static JLabel loadImage(String resource) {
        BufferedImage image;
        try {
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer, String resource) {
        BufferedImage image;
        try {
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void playSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void playLoopSound(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public static Font createFont(String resource) {
        //get font file path
        String filePath = CustomTools.class.getClassLoader().getResource(resource).getPath();

        //check for empty space in path (bug)
        if (filePath.contains("%20")) {
            filePath = filePath.replaceAll("%20", "");
        }

        //create font
        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return null;
    }

    public static String hiddenWords(String word) {
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (!(word.charAt(i) == ' ')) {
                hiddenWord += "*";
            } else {
                hiddenWord += " ";
            }
        }
        return hiddenWord;
    }
}
