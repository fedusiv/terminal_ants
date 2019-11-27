package com.engine;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    Console console;
    Terrain terrain;

    public Engine() throws IOException {
        console = Console.getInstance();
        terrain = Terrain.getInstance();


        // Until that point all set-up must be ended
        console.clean_console();
        timerTaskInit();    // run timer Task
    }

    private void timerTaskInit()
    {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                timerTask();
            }
        };
        Timer timer = new Timer("EngineTimer");

        long delay  = 0L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    private void timerTask()
    {
        console.draw(terrain);
    }

}
