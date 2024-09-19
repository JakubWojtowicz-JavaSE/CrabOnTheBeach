package Main;

import javax.swing.*;

public class Main {

    public static final String GAME_NAME = "LifeOnTheBeach";

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle(GAME_NAME);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();
        game.openCredits(false);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
//        window.addWindowFocusListener(new WindowFocusListener() {
//            @Override
//            public void windowGainedFocus(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowLostFocus(WindowEvent e) {
//
//            }
//        });
        game.setFocusable(true);
        game.requestFocus();
        game.startGameThread();

        window.setIconImage(game.getIcon());

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                game.afterWinClose();
            }
        });
    }
}
