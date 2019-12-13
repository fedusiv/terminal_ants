package com.engine;

import com.cmd.Cmd;
import com.enums.CellType;
import com.units.Plant;
import com.units.Unit;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class Terrain {

    Console console;
    public final int terrain_size;
    public final int matrix_size;
    private Cell[][] matrix;



    private static Terrain singleton = null;
    public synchronized static Terrain getInstance() throws IOException {
        if(singleton == null) singleton = new Terrain();
        return singleton;
    }
    private Terrain() throws IOException {
        console = Console.getInstance();
        terrain_size =  console.initTerrainSize();
        matrix_size = terrain_size + 2;
        createMatrix();
    }




    private void createMatrix()
    {
        matrix = new Cell[matrix_size][matrix_size];
        
        for ( int i = 0; i < matrix_size; i++ ) {
            for (int j = 0; j < matrix_size; j++) {
                matrix[i][j] = new Cell(i * matrix_size + j);

                if ( i == 0)
                {
                    matrix[i][j].order_num = j - 1;
                    matrix[i][j].type = CellType.MARKING;
                }
                if ( j == 0)
                {
                    matrix[i][j].order_num = i - 1;
                    matrix[i][j].type = CellType.MARKING;
                }
                if ( i == 1 && j != 0)
                {
                    matrix[i][j].type = CellType.SPACE;
                }
                if ( j == 1 && i != 0)
                {
                    matrix[i][j].type = CellType.SPACE;
                }
            }
        }
        matrix[0][0].type = CellType.SPACE;
    }

        public Cell cell(int i, int j)
        {
            return matrix[i][j];
        }

     public void addUnitToTerrain(Cmd cmd, int id)
     {
         matrix[cmd.point[0] + 2][cmd.point[1] + 2].setUnitId(id);
         matrix[cmd.point[0] + 2][cmd.point[1] + 2].type = CellType.UNIT;
     }
     //Check if that cell is busy by another unit
     public Boolean checkTerrainForUnit(Cmd cmd)
     {
         if (  matrix[cmd.point[0] + 2][cmd.point[1] + 2].has_unit)
         {
             return false;
         }
         return true;
     }


}
