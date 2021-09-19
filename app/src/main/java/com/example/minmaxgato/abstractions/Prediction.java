package com.example.minmaxgato.abstractions;
import com.example.minmaxgato.enums.Player;

import java.util.ArrayList;

public class Prediction
{
    public final int score;
    public int position;
    public final Player player;

    public Prediction(Player player,int score)
    {
        this.player=player;
        this.score=score;
        this.position=-1;
    }



}
