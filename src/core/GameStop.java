package core;

import settings.Settings;

class GameStop {
    static String gameStop(){
        if (Settings.isWin){
            return Settings.gameStatus[2];
        }else if(!Settings.inGame){
            return Settings.gameStatus[1];
        }
        return Settings.gameStatus[0];
    }
}
