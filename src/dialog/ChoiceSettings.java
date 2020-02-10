package dialog;

import core.Game;
import settings.Options;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChoiceSettings extends JPanel {
    private final int MAX_FIELD = 100;
    private final int MIN_FIELD = 10;


    private JLabel getLabel(String labelText){
        JLabel label = new JLabel(labelText);
        label.setFont(Options.setFont(28));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        return label;
    }

    private JTextField getInputField(){
        JTextField input = new JTextField(20);
        input.setFont(Options.setFont(25));
        input.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        //input.setMaximumSize(input.getPreferredSize());
        return input;
    }

    private JButton getButton(){
        JButton button = new JButton("OK, создать!");
        button.setFont(Options.setFont(25));
        button.setBorderPainted(false);
        button.setBackground(Color.magenta);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        return button;
    }

    private JLabel error(String text){
        JLabel label = new JLabel(text);
        label.setFont(Options.setFont(25));
        label.setForeground(Color.getHSBColor(1.34f, 0.99f, 0.48f));
        return label;
    }

    private JComboBox getAllLevels(){
        JComboBox<String> choiceLevel = new JComboBox<>(Settings.levels);
        choiceLevel.setEditable(true);
        choiceLevel.setFont(Options.setFont(25));
        return choiceLevel;
    }

    ChoiceSettings(Dialog dialog){
        setLayout(new GridLayout(8, 10, 0, 0));
        JTextField name = getInputField();
        JButton button = getButton();
        JTextField input = getInputField();
        String note = "* Допускается только число от " + MIN_FIELD + " до " + MAX_FIELD;
        JLabel error = error(note);
        JComboBox levels = getAllLevels();

        add(getLabel("Ваше имя или логин:"));
        add(name);
        add(getLabel("Выберите уровень:"));
        add(levels);
        add(getLabel("Введите сторону игрового поля * :"));
        add(input);
        add(error);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = input.getText();

                if(isCorrect(inputText)){
                    int countCell = Integer.valueOf(inputText);
                    int level = levels.getSelectedIndex();
                    error.setText("");

                    dialog.setVisible(false);
                    Game game = new Game(countCell, countBomb(level, countCell), name.getText());
                    game.setLocationRelativeTo(null);


                }else {
                    error.setText("Ошибка! Требуется ввести от " + MIN_FIELD + " до " + MAX_FIELD);
                    error.setForeground(Color.RED);
                }
            }
        });
    }

    private int countBomb(int level, int countCell){
        return (level + 1) * countCell;
    }

    private boolean isCorrect(String data){
        if(data.matches("[-+]?\\d+")){
            int countCell = Integer.valueOf(data);
            return countCell >= MIN_FIELD && countCell <= MAX_FIELD;
        }
        return false;
    }
}
