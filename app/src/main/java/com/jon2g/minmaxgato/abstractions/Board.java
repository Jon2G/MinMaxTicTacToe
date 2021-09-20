package com.jon2g.minmaxgato.abstractions;

import androidx.annotation.NonNull;

import com.jon2g.minmaxgato.enums.Player;

import java.util.ArrayList;

public class Board implements Cloneable {
    public static final int  BoardSize= 9;
    private Box[] Boxes;
    public Board(){
        ClearBoard();
    }
    private int GetIndex(int row,int col){
      return  row*3+col;
    }
    public Box Get(int Index) {
        return Boxes[Index];
    }
    public Box Get(int row,int col){
        return Get(GetIndex(row,col));
    }
    public Board Set(int Index, Player player){
        Get(Index).player=player;
        return  this;
    }
    public void Set(int row,int col,Player player){
        Set(GetIndex(row,col),player);
    }
    private void  ClearBoard(){
        Boxes=new Box[BoardSize];
        for (int i=0;i<BoardSize;i++){
            Boxes[i]=new Box();
        }
    }
    @NonNull
    @Override
    protected Object clone(){
        Board cloned=new Board();
        for(int i=0;i<BoardSize;i++){
         cloned.Set(i,Get(i).player);
        }
        return cloned;
    }
    public Board Clone(){
        return (Board) clone();
    }
    public boolean IsFull()
    {
       return FreeBoxes().length==0;
    }
    public Integer[] FreeBoxes()
    {
        ArrayList<Integer> free=new ArrayList<>();
        for(int i=0;i<BoardSize;i++){
            if(Get(i).player==Player.NONE) {
               free.add(i);
            }
        }
        return free.toArray(new Integer[free.size()]);
    }

    public Player HasWinner(){
        Player player;
        //Filas
        for(int c=0;c<3;c++){
            player=Get(c,0).player;
            if(player==Get(c,1).player&&player==Get(c,2).player) {
                return player;
            }
        }
        //Columnas
        for(int r=0;r<3;r++){
            player=Get(0,r).player;
            if(player==Get(1,r).player&&player==Get(2,r).player) {
                return player;
            }
        }
        // / diagonal
        player=Get(1,1).player;//centro
        if(player==Get(0,0).player&&player==Get(2,2).player){
            return player;
        }
        // \ diagonal
        player=Get(1,1).player;//centro
        if(player==Get(2,0).player&&player==Get(0,2).player){
            return player;
        }
        return Player.NONE;
    }
}
