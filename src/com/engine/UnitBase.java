package com.engine;

import com.cmd.Cmd;
import com.units.Plant;
import com.units.Unit;

import java.util.Vector;

public class UnitBase {

    private Vector<Unit> units;

    private UnitBase(){
        units = new Vector<Unit>();
    }
    private static UnitBase singleton = null;
    public synchronized static UnitBase getInstance()  {
        if(singleton == null) singleton = new UnitBase();
        return singleton;
    }
    public int createNewUnit(Cmd cmd)
    {

        switch (cmd.unit)
        {
            case PLANT:
                Plant unit = new Plant(units.size());
                units.add(unit);
                break;
        }

        return units.size() - 1;
    }

    // method call step for each unit
    public void stepUnits()
    {
        if ( units == null ) return;

        for ( int i = 0 ; i < units.size(); i ++)
        {
            units.get(i).lifeProcess();
        }
    }

    public String getSymbol(int id )
    {
        return units.get(id).symbolGui();
    }
}
