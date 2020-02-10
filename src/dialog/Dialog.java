package dialog;

import javax.swing.*;

public class Dialog extends JFrame {
    private static int sizeX = 600;
    private static int sizeY = 300;

    public Dialog(){
        setTitle("Сапёр");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(sizeX, sizeY);
        setSize(this);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));
        add(new ChoiceSettings(this));
        setSize(600, 600);
        setResizable(false);
        setVisible(true);
    }

    private void setSize(Dialog dialog){
        dialog.setSize(sizeX, sizeY);
    }

}
