package core;

import settings.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameTimer{
    static Timer timer;

    GameTimer(JLabel time, JLabel status, JLabel score, JPanel panel) {
        final int[] second = {0};
        final int[] minute = {0};
        final int[] hour = {0};

        timer = new Timer( 1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Settings.inGame){
                    StringBuilder timeStr = new StringBuilder();
                    second[0]++;

                    if(second[0] == 60){
                        minute[0]++;
                        second[0] = 0;
                    }
                    if(minute[0] == 60){
                        hour[0]++;
                        minute[0] = 0;
                    }

                    if(hour[0] == 60){
                        second[0] = 0;
                        minute[0] = 0;
                        hour[0] = 0;
                        Settings.inGame = false;
                    }

                    if(hour[0] < 10){
                        timeStr.append(0).append(hour[0]).append(":");
                    }else {
                        timeStr.append(hour[0]).append(":");
                    }
                    if(minute[0] < 10){
                        timeStr.append(0).append(minute[0]).append(":");
                    }else {
                        timeStr.append(minute[0]).append(":");
                    }
                    if(second[0] < 10){
                        timeStr.append(0).append(second[0]);
                    }else {
                        timeStr.append(second[0]);
                    }

                    time.setText(String.valueOf(timeStr));
                    score.setText(String.valueOf(Settings.score));
                    panel.repaint();


                }else {
                    status.setText(GameStop.gameStop());
                    score.setText(String.valueOf(Settings.score));
                    panel.repaint();
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}
