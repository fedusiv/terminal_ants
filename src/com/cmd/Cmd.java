package com.cmd;

import com.enums.CmdEnum;
import com.enums.UnitsEnum;

// class stores information about command to run
public class Cmd {
    public CmdEnum type;
    public int run_steps = 0;
    public int map_steps = 0;
    public int[] point = {0,0};
    public String string = "";
    public UnitsEnum unit;
    public Cmd(CmdEnum type)
    {

        this.type = type;
    }
    public Cmd()
    {

    }
}
