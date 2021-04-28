package ictgradschool.amazeing;

import ictgradschool.amazeing.gui.AppWindow;

import javax.swing.*;

/**
 * Main program entry point.
 *
 * @author Andrew Meads
 */
public class Main {

    /**
     * Creates the main app window ({@link AppWindow}), centers it, and displays it.
     */
    private Main() {
        AppWindow window = new AppWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Will create a new instance of this class, on Swing's ED thread.
     * <p>
     * For more information on the Main::new syntax, see
     * https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
     *
     * @param args not used in this program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}
