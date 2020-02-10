package settings;

import javax.swing.*;
import java.awt.*;


public class Options extends JPanel {

    public Options(){
        getSizeCell(getCoefficient(widthScreen(screenSize())));
    }

    public int getSizeCell(int sizeCoefficient){
        return sizeCoefficient * Settings.SIZE_CELL;
    }

    public int getCoefficient(int width){
        return width/Settings.STANDARD_SIZE_SCREEN;
    }

    public Dimension screenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public int widthScreen(Dimension screenSize){
        return screenSize.width;
    }

    public int height(Dimension screenSize){
        return screenSize.height;
    }

    public static Color getColorText(int countBomb){
        Color color = Color.BLACK;
        switch (countBomb){
            case (1):
                color = Color.getHSBColor(1.34f, 0.99f, 0.48f);
                break;
            case (2):
                color = Color.getHSBColor(1.34f, 0.99f, 0.73f);
                break;
            case (3):
                color = Color.ORANGE;
                break;
            case (4):
                color = Color.PINK;
                break;
            case (5):
                color = Color.getHSBColor(0.0f, 0.95f, 0.96f);
                break;
            case (6):
                color = Color.getHSBColor(0.0f, 0.99f, 0.75f);
                break;
            case (7):
                color = Color.getHSBColor(0.0f, 0.99f, 0.62f);
                break;
            case (8):
                color = Color.getHSBColor(0.0f, 0.99f, 0.51f);
                break;
        }
        return color;
    }

    public static Font setFont(int size){
        return new Font("TimesRoman", Font.PLAIN, size);
    }
}
