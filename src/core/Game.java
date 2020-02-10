package core;

import settings.Options;
import settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game(int countCell, int countBomb, String name){
        int[][] allBombs = Bombs.getBombs(countBomb, countCell);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame(new Settings().TITLE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());

                GameField gameField = new GameField(frame, countCell, getClickCell(countCell), allBombs, countBomb);
                frame.add(new Sidebar(name, Settings.inGame, countBomb, countCell, frame, gameField));
                frame.add(gameField);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static int[][] getClickCell(int countCell){
        return new int[countCell][countCell];
    }

    public static void deleteGameField(JFrame frame, GameField jPanel){
        frame.remove(jPanel);
    }


}
