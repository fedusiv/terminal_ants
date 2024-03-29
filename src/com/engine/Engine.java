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
    UnitBase unitBase;
    static int step = 0;
    static int run_steps = 0;
    static int step_time_ms = 1000;
    public Engine() throws IOException, InterruptedException {
        console = Console.getInstance();
        terrain = Terrain.getInstance();
        timer = new Timer("EngineTimer");
        parser = Parser.getInstance();
        unitBase = UnitBase.getInstance();
        // Until that point all set-up must be ended
        console.clean_console();
        console.draw(terrain,step);
        mainLoop();
    }


    private void runStep() throws IOException {
        step ++;
        unitBase.stepUnits();
        console.draw(terrain,step);
        run_steps--;
    }

    private void executeCmd(Cmd cmd) throws IOException {
        if ( cmd.type == CmdEnum.ERROR)
        {
            console.setLastCmd("Wrong command");
            return;
        }
        if ( cmd.type == CmdEnum.RUN)
        {
            run_steps = cmd.run_steps;
        }
        if ( cmd.type == CmdEnum.GOTO )
        {
            console.set_map_coor_point(cmd.point[0], cmd.point[1]);
            console.draw(terrain,step);
        }
        if ( cmd.type == CmdEnum.PLACE)
        {
            if ( terrain.checkTerrainForUnit(cmd)) {
                //Cell is not busy, can place
                int unit_id = unitBase.createNewUnit(cmd);
                terrain.addUnitToTerrain(cmd, unit_id);
                console.draw(terrain, step);
            }
            else
            {
                // Cell is busy do nothing
                cmd.string = "Cell is busy";
                console.draw(terrain,step);
            }
        }
        console.setLastCmd(cmd.string);
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
