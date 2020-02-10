package core;

import settings.Options;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;


public class OneCell extends JPanel{

    private Color defaultBackground;

    OneCell(JFrame jFrame, GameField gameField, int countBombAround, JLabel label, int row, int col,
            int[][] getClickCellArray, int[][] allBombs, int countCell, int countBombs){

        addMouseListener(new MouseAdapter() {
            int[][] getClickCell = getClickCellArray;

            @Override
            public void mouseEntered(MouseEvent e) {
                if(Settings.inGame){
                    if(getClickCellArray[row][col] == 0) {
                        defaultBackground = getBackground();
                        label.setForeground(Color.BLUE);
                        setBackground(Color.BLUE);
                    }else if(getClickCellArray[row][col] == 2){
                        label.setText(GameField.getUnicodeLabel("D83D")+GameField.getUnicodeLabel("DCA3"));
                        label.setForeground(Color.RED);
                    }else {
                        label.setForeground(defaultBackground);
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(Settings.inGame){
                    if (e.getButton() == MouseEvent.BUTTON1){
                        getClickCell[row][col] = 1;
                        getClickCell = changedClickCellValue(row, col, getClickCellArray, countBombAround, allBombs, countCell);
                        if(countBombAround == -1){
                            label.setForeground(Color.BLACK);
                            Settings.inGame = false;
                        }else if(countBombAround > 0){
                            label.setForeground(Options.getColorText(countBombAround));
                        }

                        jFrame.remove(gameField);
                        jFrame.add(new GameField(jFrame, countCell, getClickCell, allBombs, countBombs));
                        jFrame.pack();
                        jFrame.repaint();

                    }else if(e.getButton() == MouseEvent.BUTTON3){
                        getClickCell[row][col] = 2;
                        label.setText(GameField.getUnicodeLabel("D83D")+GameField.getUnicodeLabel("DCA3"));
                        label.setForeground(Color.RED);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Settings.inGame) {
                    if (getClickCellArray[row][col] == 0) {
                        label.setForeground(defaultBackground);
                        setBackground(defaultBackground);
                    } else if (getClickCellArray[row][col] == 2) {
                        setBackground(defaultBackground);
                        label.setText(GameField.getUnicodeLabel("D83D") + GameField.getUnicodeLabel("DCA3"));
                        label.setForeground(Color.RED);
                    } else {
                        setBackground(Color.WHITE);
                        label.setForeground(Options.getColorText(countBombAround));
                    }
                }
            }
        });
    }
    @Override
    public Dimension getPreferredSize() {
        Options options = new Options();
        int sizeCell = options.getSizeCell(options.getCoefficient(options.widthScreen(options.screenSize())));
        return new Dimension(sizeCell, sizeCell);
    }

    private int[][] changedClickCellValue(int row, int col, int[][] getClickCell, int countBombAround, int[][] allBombs, int countCell){
        if(countBombAround == 0){
            ArrayDeque<int[]> queue = new ArrayDeque<>();
            queue.addLast(new int[]{row, col});

            while (!queue.isEmpty()){

                int x = queue.getFirst()[0];
                int y = queue.getFirst()[1];

                if(x-1 >= 0){
                    if(getClickCell[x-1][y] == 0){
                        if(GameField.getCountBombAround(x-1, y, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x-1, y});
                        }
                        getClickCell[x-1][y] = 1;
                    }
                }
                if(x+1 < getClickCell.length){
                    if(getClickCell[x+1][y] == 0){
                        if(GameField.getCountBombAround(x+1, y, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x+1, y});
                        }
                        getClickCell[x+1][y] = 1;
                    }
                }
                if(y-1 >= 0){
                    if(getClickCell[x][y-1] == 0){
                        if(GameField.getCountBombAround(x, y-1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x, y-1});
                        }
                        getClickCell[x][y-1] = 1;
                    }

                }
                if(y+1 < getClickCell.length){
                    if(getClickCell[x][y+1] == 0){
                        if(GameField.getCountBombAround(x, y+1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x, y+1});
                        }
                        getClickCell[x][y+1] = 1;
                    }
                }
                if(x-1 >= 0 && y-1 >= 0){
                    if(getClickCell[x-1][y-1] == 0){
                        if(GameField.getCountBombAround(x-1, y-1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x-1, y-1});
                        }
                        getClickCell[x-1][y-1] = 1;
                    }
                }
                if(x-1 >= 0 && y+1 < getClickCell.length){
                    if(getClickCell[x-1][y+1] == 0){
                        if(GameField.getCountBombAround(x-1, y+1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x-1, y+1});
                        }
                        getClickCell[x-1][y+1] = 1;
                    }
                }
                if(x+1 < getClickCell.length && y-1 >= 0){
                    if(getClickCell[x+1][y-1] == 0){
                        if(GameField.getCountBombAround(x+1, y-1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x+1, y-1});
                        }
                        getClickCell[x+1][y-1] = 1;
                    }
                }
                if(x+1 < getClickCell.length && y+1 < getClickCell.length){
                    if(getClickCell[x+1][y+1] == 0){
                        if(GameField.getCountBombAround(x+1, y+1, countCell, allBombs) == 0){
                            queue.addLast(new int[]{x+1, y+1});
                        }
                        getClickCell[x+1][y+1] = 1;
                    }
                }
                queue.removeFirst();
            }
        }
        return getClickCell;
    }
}
