package settings;

public class Settings {
    public String TITLE = "Сапёр";
    static final int SIZE_CELL = 16; // Размер ячейки
    static final int STANDARD_SIZE_SCREEN = 1200;
    public static int score = 0;
    public static boolean inGame = true;
    public static boolean isWin = false;
    public static String[] levels = new String[]{
            "Новичок",
            "Средний уровень",
            "Профессионал",
            "Гуру"
    };

    public static String[] gameStatus = new String[]{
            "Игра идет",
            "Game Over!",
            "You Win!"
    };
}
