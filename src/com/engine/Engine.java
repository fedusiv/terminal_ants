package com.engine;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    Console console;
    Terrain terrain;
    Timer timer;

    static int step = 0;
    static int run_steps = 0;
    public Engine() throws IOException {
        console = Console.getInstance();
        terrain = Terrain.getInstance();
        timer = new Timer("EngineTimer");

        // Until that point all set-up must be ended
        console.clean_console();
        console.draw(terrain,step);
        mainLoop();
    }

    private void timerTaskInit()
    {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                try {
                    timerTask();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        long delay  = 0L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    private void timerTask() throws IOException {
        console.draw(terrain,step);
    }

    private void mainLoop() throws IOException {
        while ( true)
        {
            if ( run_steps == 0) {
                console.readCommand();
                console.draw(terrain, step);
            }
        }
    }
}
