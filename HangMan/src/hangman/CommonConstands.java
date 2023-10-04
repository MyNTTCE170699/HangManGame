/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author MY LAPTOP
 */
public class CommonConstands {

    //file paths
    public static final String DATA_PATH = "resources/data.txt";
    public static final String IMAGE_PATH = "/resources/1.png";
    public static final String LOGO_PATH = "/resources/logo.png";
    public static final String FONT_PATH = "resources/Cartoonero.ttf";
    public static final String ABOUTUS_PATH = "/resources/aboutUs.png";
    public static final String ICON_PATH = "/resources/icon.png";
    public static final String HOWTOPLAY_PATH = "/resources/howToPlay.jpg";
    public static final String INTROPIC_PATH = "/resources/intro.png";
    public static final String QUIT_PATH = "/resources/quit.png";
    
    //SFX
    public static final String THEME_PATH = "./src/resources/theme.wav";
    public static final String CORRECT_PATH = "./src/resources/correct.wav";
    public static final String INCORRECT_PATH = "./src/resources/incorrect.wav";
    public static final String WARNING_PATH = "./src/resources/warning.wav";
    public static final String WIN_PATH = "./src/resources/win.wav";
    public static final String LOST_PATH = "./src/resources/lost.wav";
    public static final String SHUTDOWN_PATH = "./src/resources/shutdown.wav";
    public static final String INTROSOUND_PATH = "./src/resources/intro.wav";


    //color config
    public static final Color PRIMARY_COLOR = Color.decode("#14212D");    //black-blue
//    public static final Color SECONDARY_COLOR = Color.decode("#FCA311");  //yellow
    public static final Color BACKGROUND_COLOR = Color.decode("#101820"); //black

    public static final Color SECONDARY_COLOR = Color.decode("#2b5554"); // green-ass
    // size config
    public static final Dimension FRAME_SIZE = new Dimension(540, 760);
//    public static final Dimension FRAME_SIZE = new Dimension(1920, 1080);
    public static final Dimension BUTTON_PANEL_SIZE = new Dimension(FRAME_SIZE.width, (int) (FRAME_SIZE.height * 0.42));
    public static final Dimension RESULT_DIALOG_SIZE = new Dimension((int) (FRAME_SIZE.width / 2), (int) (FRAME_SIZE.height / 4));
}
