package com.engine;

import com.enums.CellType;

import java.io.IOException;

import static java.lang.String.format;

public class Cell {

    CellType type;
    int order_num;      // only for marking
    final int id;
    public int unit_id; // id, which unit take place here
    private UnitBase unitBase;
    public  Cell (int id ) {
        this.id = id;
        type = CellType.EMPTY;
        unitBase = UnitBase.getInstance();
    }

    // output symbol
    public String symbol ()
    {
        String symbol="?";
        switch (type)
        {
            case MARKING:
                symbol = format("%02d", order_num);
                break;
            case EMPTY:
                symbol = " .";
                break;
            case SPACE:
                symbol = " ";
                break;
            case UNIT:
                symbol = unitBase.getSymbol(unit_id);
                break;

        }

        return symbol;
    }

}
