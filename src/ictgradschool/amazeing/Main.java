package ictgradschool.amazeing;

import ictgradschool.amazeing.gui.AppWindow;

import javax.swing.*;

public class Main {

    private Main() {

        AppWindow window = new AppWindow();
        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}
