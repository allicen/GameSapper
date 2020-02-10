package core;

import settings.Options;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Sidebar extends JPanel {
    Sidebar(String name, boolean inGame, int countBomb, int countCell, JFrame frame, GameField gameField){
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        Box vBox = Box.createVerticalBox();

        JButton button = getButton();
        JLabel score = getLabel(String.valueOf(Settings.score), 40, Options.getColorText(1), 40);
        JLabel status = getLabel(GameStop.gameStop(), 30, Options.getColorText(1), 20);
        JLabel time = getLabel("00:00:00", 25, Color.BLACK, 40);

        vBox.add(getLabel("Привет, " + name + "!", 30, Options.getColorText(5), 40));
        vBox.add(getLabel("Статус игры: ", 18, Color.BLACK, 15));
        vBox.add(status);

        new GameTimer(time, status, score,  this);
        vBox.add(time);

        vBox.add(getLabel("Количество очков: ", 18, Color.BLACK, 15));
        vBox.add(score);
        vBox.add(button);
        vBox.revalidate();
        add(vBox);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] allBombs = Bombs.getBombs(countBomb, countCell);
                frame.setVisible(false);
                GameTimer.timer.stop();
                new Game(countCell, countBomb, name);
                Settings.inGame = true;
                Settings.isWin = false;
            }
        });
    }

    private JLabel getLabel(String text, int fontSize, Color color, int marginBottom){
        JLabel label = new JLabel(text);
        label.setFont(Options.setFont(fontSize));
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, marginBottom, 0));
        return label;
    }

    private JButton getButton(){
        JButton button = new JButton("Начать сначала");
        button.getModel().setRollover(false);
        button.setFont(Options.setFont(20));
        button.setBorderPainted(false);
        button.setBackground(Color.darkGray);
        button.setForeground(Color.darkGray);
        button.setFocusPainted(false);
        return button;
    }
}
