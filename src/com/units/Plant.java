package com.units;

import com.enums.PlantLifeStagesEnum;

public class Plant extends Unit{

    protected int  growth_time = 3;
    protected PlantLifeStagesEnum life_stage;
    public Plant(int id)
    {
        super(id);
        life_time = 10;
        life_stage = PlantLifeStagesEnum.SPROUT;
    }
    @Override
    public String symbolGui()
    {
        String symbol = null;
        switch (life_stage)
        {
            case SPROUT:
                symbol = " p";
                break;
            case PLANT:
                symbol = " \u001b[32mP\u001b[0m";
                break;
            case DEAD:
                symbol = " \u001b[30mp\u001b[0m";
                break;
            case REPRODUCTION:
                symbol = " \u001b[33mP\u001b[0m";
                break;

        }
        return symbol;
    }
    @Override
    public void lifeProcess()
    {
        life_count++;
        if ( life_count == growth_time)
        {
            life_stage = PlantLifeStagesEnum.PLANT;
        }
        if ( life_count > life_time)
        {
            life_stage = PlantLifeStagesEnum.DEAD;
        }
    }
}
