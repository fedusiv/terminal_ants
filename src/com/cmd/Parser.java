package com.cmd;

import com.enums.CmdEnum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private static Parser singleton = null;

    private Parser () throws IOException {

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

    private Cmd cmd_gd(String[] tokens)
    {
        Cmd cmd = new Cmd(CmdEnum.GODOWN);
        if ( tokens.length > 1)
        {
            cmd.map_steps = Integer.parseInt(tokens[1]);
        }else
        {
            cmd.map_steps = 1;
        }
        cmd.string = "Go down in map  " + Integer.toString(cmd.run_steps);
        return cmd;
    }

}
