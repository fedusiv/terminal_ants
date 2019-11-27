package com.engine;

import com.enums.CellType;

public class Cell {

    CellType type;
    int order_num;      // only for marking
    final int id;

    public  Cell (int id )
    {
        this.id = id;
        type = CellType.EMPTY;
    }

    // output symbol
    public String symbol ()
    {
        String symbol="?";
        switch (type)
        {
            case MARKING:
                symbol = Integer.toString(order_num);
                break;
            case EMPTY:
                symbol = ".";
                break;
            case SPACE:
                symbol = " ";
                break;
        }
        return symbol;
    }

}
