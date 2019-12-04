package com.units;

public class Unit {

    protected  int life_time;
    protected int life_count;
    final  int id;
    public Unit(int id)
    {
        this.id = id;
    }
    public String symbolGui()
    {
        return "u";
    }
    // method must be called for each unit on each step
    public void lifeProcess()
    {
        life_count++;
    }

}
