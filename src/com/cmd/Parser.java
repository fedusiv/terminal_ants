package com.cmd;

import com.enums.CmdEnum;

public class Parser {

    private static Parser singleton = null;

    private Parser ()
    {

    }

    public synchronized static Parser getInstance()
    {
        if(singleton == null) singleton = new Parser();
        return singleton;
    }

    public Cmd parseCommand(String string)
    {
        Cmd cmd = new Cmd();
        String delims = "[ ]+";
        String[] tokens = string.split(delims);
        if ( tokens[0].equals("run"))
        {
            cmd.type = CmdEnum.RUN;
            cmd.run_steps = Integer.parseInt(tokens[1]);
        }

        return cmd;
    }

}
