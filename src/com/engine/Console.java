package com.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static Console singleton = null;
    private String last_cmd = "";
    private  Console()
    {

    }

    public synchronized static Console getInstance() {
        if(singleton == null) singleton = new Console();
        return singleton;
    }

    public String readCommand() throws IOException {
        String cmd = "";
        java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        cmd = in.readLine();
        return cmd;
    }

    public int  initTerrainSize() throws IOException {
        int size;
        System.out.println("Enter size of Terrain square");
        java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(in.readLine());
        if ( size > 97 )
        {
            System.out.println("Maximum size 97 ");
            size = 97;
        }
        System.out.println("Size of Terrain square is " + size);
        return size;
    }

    public void draw(Terrain terrain, int step) throws IOException {
        clean_console();
        System.out.print("\033[H");
        draw_step(step);
        drawTerrain(terrain);
        drawEnterField();
    }

    public void clean_console()
    {
        System.out.print("\033[H");
        System.out.print("\033[2J");
    }
    private void draw_step(int step)
    {
        System.out.print('\n');
        System.out.println("Step: " + step);

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
        System.out.println(" > ... " + last_cmd);
        System.out.print("> ");

    }
    public void setLastCmd(String string)
    {
        last_cmd = string;
    }


}
