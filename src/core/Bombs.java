package core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Bombs {
    static int[][] getBombs(int countBomb, int countCell){
        int[][] bombs = new int[countCell][countCell];

        int start = 0;
        Set<Integer> bombsPositions = new HashSet<>();

        while (bombsPositions.size() < countBomb){
            int random = start + (int) (Math.random() * (countCell * countCell));
            bombsPositions.add(random);
        }

        int index = 0;
        for(int row = 0; row < countCell; row++){
            for (int col = 0; col < countCell; col++){
                if(bombsPositions.contains(index)){
                    bombs[row][col] = -1;
                }
                index++;
            }
        }
        return bombs;
    }
}
