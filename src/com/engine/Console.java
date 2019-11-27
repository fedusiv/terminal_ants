package com.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {


    private static Console singleton = null;
    private  Console()
    {

    }

    public synchronized static Console getInstance() {
        if(singleton == null) singleton = new Console();
        return singleton;
    }
    public int  initTerrainSize() throws IOException {
        int size;
        System.out.println("Enter size of Terrain square");
        java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(in.readLine());
        System.out.println("Size of Terrain square is " + size);
        return size;
    }

    public void clean_console()
    {
        System.out.print("\033[H");
        System.out.print("\033[2J");
    }

    public void draw(Terrain terrain)
    {
        System.out.print("\033[H");
        drawTerrain(terrain);
        drawEnterField();
        System.out.print('\n');

    }

    private void drawTerrain(Terrain terrain)
    {
        for ( int i = 0; i < terrain.matrix_size; i++ ) {
            System.out.print('\n');
            for (int j = 0; j < terrain.matrix_size; j++) {
                String symbol = terrain.cell(i,j).symbol();
                System.out.print( symbol + " " );
            }
        }
        System.out.print('\n');

    }
    private void drawEnterField()
    {
        System.out.print('\n');
        System.out.print(" > ... ");

    }

}
