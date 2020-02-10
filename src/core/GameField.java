package core;


import settings.Options;
import settings.Settings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

class GameField extends JPanel {
    static int getCountBombAround(int row, int col, int countCell, int[][] allBombs){
        int count = 0;
        if(row-1 >= 0){
            if(isBomb(allBombs[row-1][col])) count++;
        }

        if(row+1 < countCell){
            if(isBomb(allBombs[row+1][col])) count++;
        }

        if(col-1 >= 0){
            if(isBomb(allBombs[row][col-1])) count++;
        }

        if(col+1 < countCell){
            if(isBomb(allBombs[row][col+1])) count++;
        }

        if(row-1 >= 0 && col-1 >= 0){
            if(isBomb(allBombs[row-1][col-1])) count++;
        }

        if(row-1 >= 0 && col+1 < countCell){
            if(isBomb(allBombs[row-1][col+1])) count++;
        }

        if(row+1 < countCell && col-1 >= 0){
            if(isBomb(allBombs[row+1][col-1])) count++;
        }

        if(row+1 < countCell && col+1 < countCell){
            if(isBomb(allBombs[row+1][col+1])) count++;
        }
        return count;
    }

    private static boolean isBomb(int cell){
        return cell == -1;
    }

    static String getUnicodeLabel(String str){
        int emojiCodePoint = Integer.parseInt(str,16);
        return new String(Character.toChars(emojiCodePoint));
    }

    GameField(JFrame jFrame, int countCell, int[][] getClickCellArray, int[][] allBombs, int countBombs){
        setVisible(true);
        int borderSize = 1;
        int countRows = countCell - borderSize;
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        int openCells = 0;

        for (int row = 0; row < countCell; row++) {
            for (int col = 0; col < countCell; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                String textCell = "";
                int countBombAround = getCountBombAround(row, col, countCell, allBombs);
                if(allBombs[row][col] == -1){
                    countBombAround = -1;
                    textCell = getUnicodeLabel("D83D")+getUnicodeLabel("DCA3");
                }else {
                    textCell = String.valueOf(countBombAround);
                }


                JLabel label = new JLabel(textCell);
                label.setFont(new Font("TimesRoman", Font.BOLD, 20));

                OneCell cellPane = new OneCell(jFrame, this, countBombAround, label, row, col, getClickCellArray, allBombs, countCell, countBombs);

                if(allBombs[row][col] == -1){
                    cellPane.add(label);
                }else {
                    if(countBombAround != 0){
                        cellPane.add(label);
                    }
                }

                if(Settings.inGame){
                    if(getClickCellArray[row][col] == 0){
                        cellPane.setBackground(getBackground());
                        label.setForeground(getBackground());
                    }else if(getClickCellArray[row][col] == 1) {
                        cellPane.setBackground(Color.WHITE);
                        label.setForeground(Options.getColorText(countBombAround));
                        openCells++;
                    }else if(getClickCellArray[row][col] == 2){
                        label.setText(GameField.getUnicodeLabel("D83D")+GameField.getUnicodeLabel("DCA3"));
                        label.setForeground(Color.RED);
                    }
                }else {
                    cellPane.setBackground(Color.WHITE);
                    label.setForeground(Options.getColorText(countBombAround));
                }

                if(openCells + countBombs == countCell*countCell){
                    Settings.isWin = true;
                    Settings.inGame = false;
                }

                Border border = null;
                if (row < countRows) {
                    if (col < countRows) {
                        border = new MatteBorder(borderSize, borderSize, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(borderSize, borderSize, 0, borderSize, Color.GRAY);
                    }
                } else {
                    if (col < countRows) {
                        border = new MatteBorder(borderSize, borderSize, borderSize, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(borderSize, borderSize, borderSize, borderSize, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }
    }

}
