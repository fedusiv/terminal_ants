package com.engine;

import com.cmd.Cmd;
import com.cmd.Parser;
import com.enums.CmdEnum;


import java.io.IOException;
import java.util.Timer;

public class Engine {

    Console console;
    Terrain terrain;
    Timer timer;
    Parser parser;

    static int step = 0;
    static int run_steps = 0;
    static int step_time_ms = 1000;
    public Engine() throws IOException, InterruptedException {
        console = Console.getInstance();
        terrain = Terrain.getInstance();
        timer = new Timer("EngineTimer");
        parser = Parser.getInstance();
        // Until that point all set-up must be ended
        console.clean_console();
        console.draw(terrain,step);
        mainLoop();
    }


    private void runStep() throws IOException {
        step ++;
        console.draw(terrain,step);
        run_steps--;
    }

    private void executeCmd(Cmd cmd)
    {
        console.setLastCmd(cmd.string);
        if ( cmd.type == CmdEnum.RUN)
        {
            run_steps = cmd.run_steps;
        }

    }


    private void mainLoop() throws IOException, InterruptedException {
        String s_cmd = "";
        while ( true)
        {
            if ( run_steps == 0)
            {
                console.draw(terrain,step);
                s_cmd = console.readCommand();
                Cmd cmd = parser.parseCommand(s_cmd);
                executeCmd(cmd);
            }else
            {
                runStep();
                Thread.sleep(step_time_ms);
            }
        }
    }
}
