package com.cmd;

import com.engine.Terrain;
import com.enums.CmdEnum;

import com.engine.Console;
import com.enums.UnitsEnum;

import java.io.IOException;

public class Parser {

    private static Parser singleton = null;
    private Console console;
    private Terrain terrain;
    private Parser () throws IOException {
        console = Console.getInstance();
        terrain = Terrain.getInstance();
    }

    public synchronized static Parser getInstance() throws IOException {
        if(singleton == null) singleton = new Parser();
        return singleton;
    }

    public Cmd parseCommand(String string)
    {
        Cmd cmd = new Cmd();
        String delims = "[ ]+";
        String[] tokens = string.split(delims);
        switch (tokens[0])
        {
            case "r":
                cmd = cmd_r(tokens);
                break;
            case "gd":
                cmd = cmd_gd(tokens);
                break;
            case "gt":
                cmd = cmd_gt(tokens);
                break;
            case "place":
                cmd = cmd_place(tokens);
                break;
        }

        return cmd;
    }

    private Cmd cmd_r(String[] tokens)
    {
        Cmd cmd = new Cmd(CmdEnum.RUN);
        if ( tokens.length > 1)
        {
            cmd.run_steps = Integer.parseInt(tokens[1]);
        }else
        {
            cmd.run_steps = 1;
        }
        cmd.string = "Run step " + Integer.toString(cmd.run_steps);
        return cmd;
    }

    private Cmd cmd_gt(String[] tokens)
    {
        Cmd cmd = new Cmd(CmdEnum.GOTO);
        if ( tokens.length > 1)
        {
            cmd.point[0] = Integer.parseInt(tokens[1]) - 1;
            cmd.point[1] = Integer.parseInt(tokens[2]) - 1;
        }else
        {
            cmd.type = CmdEnum.ERROR;
            return cmd;
        }

        if ( cmd.point[0] < 0 ) cmd.point[0] = 0;
        if ( cmd.point[1] < 0 ) cmd.point[1] = 0;


        // check if maybe entered out of array value
        if ( cmd.point[0] + console.mapDisplaySize() > terrain.terrain_size )
        {
            cmd.point[0] = terrain.terrain_size - console.mapDisplaySize();
        }
        if ( cmd.point[1] + console.mapDisplaySize() > terrain.terrain_size )
        {
            cmd.point[1] = terrain.terrain_size - console.mapDisplaySize();
        }
        cmd.string = "Go to coordinates  " + ( cmd.point[0] + 1 ) + " " +  ( cmd.point[1]+ 1) ;
        return cmd;
    }
    private Cmd cmd_gd(String[] tokens)
    {
        Cmd cmd = new Cmd(CmdEnum.GODOWN);
        if ( tokens.length > 3)
        {
            cmd.map_steps = Integer.parseInt(tokens[1]);
        }else
        {
            cmd.map_steps = 1;
        }
        cmd.string = "Go down in map  " + Integer.toString(cmd.run_steps);
        return cmd;
    }

    private Cmd cmd_place(String[] tokens)
    {
        Cmd cmd = new Cmd(CmdEnum.PLACE);
        if ( tokens.length < 4) {
            cmd.type = CmdEnum.ERROR;
            cmd.string = "Wrong command params";
            return cmd;
        }

        switch (tokens[1])
        {
            case "plant":
                cmd.unit = UnitsEnum.PLANT;
                break;
            default:
                cmd.string = "Wrong unit name";
                cmd.type = CmdEnum.ERROR;
                break;
        }
        if ( cmd.type == CmdEnum.ERROR )
        {
            return cmd;
        }
        cmd.point[0] = Integer.parseInt(tokens[2]) - 1;
        cmd.point[1] = Integer.parseInt(tokens[3]) - 1;
        if ( cmd.point[0] > terrain.terrain_size  || cmd.point[1] > terrain.terrain_size)
        {
            cmd.string = "Wrong coordinates";
            cmd.type = CmdEnum.ERROR;
        }
        cmd.string = "Add new plant at " + tokens[2] + " " + tokens[3];
        return cmd;
    }

}
