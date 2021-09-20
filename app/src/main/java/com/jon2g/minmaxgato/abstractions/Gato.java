package com.jon2g.minmaxgato.abstractions;

import com.jon2g.minmaxgato.enums.AIResult;
import com.jon2g.minmaxgato.enums.Player;

import java.util.Random;

public class Gato {

   private static Random random = new Random();
    public com.jon2g.minmaxgato.abstractions.Board Board;
    public Player CurrentPlayer;
    public final Player Human;
    private final AI AI;
    public Gato(){
        Human=RandomPlayer();
        CurrentPlayer=Human;
        AI=new AI(Human.Rival());
        Board=new Board();
    }
    public AIResult MarkBox(int r, int c, Player player){
        AIResult result=AIResult.DONE;
        Box box=Board.Get(r,c);
        if(box.player!=Player.NONE) {
           return AIResult.DONE;
        }
        box.player = player;
        CurrentPlayer=CurrentPlayer.Rival();
        if(CurrentPlayer==AI.player){
            result= AI.MakeMove(this.Board);
         CurrentPlayer=CurrentPlayer.Rival();
        }
        return result;
    }
    private Player RandomPlayer(){
        int p=random.nextInt(2 - 1 + 1) + 1;
       return Player.values()[p];
    }
}
