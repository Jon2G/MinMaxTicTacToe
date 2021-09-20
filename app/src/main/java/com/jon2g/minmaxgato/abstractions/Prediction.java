package com.jon2g.minmaxgato.abstractions;
import com.jon2g.minmaxgato.enums.Player;

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
