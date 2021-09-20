package com.jon2g.minmaxgato.abstractions;


import com.jon2g.minmaxgato.enums.AIResult;
import com.jon2g.minmaxgato.enums.Player;

public class AI {
    public final Player player;
    public AI(Player AIPlayer){
        this.player=AIPlayer;
    }

    public AIResult MakeMove(Board board){
        Prediction best_move= MinMax(board,player);
        if(best_move.position>=0) {
            board.Set(best_move.position,player);
        }
        Player winner=board.HasWinner();
        if(winner!=Player.NONE){
            return winner==this.player ? AIResult.I_WIN:AIResult.I_FAIL;
        }
        if (board.IsFull()){
            return AIResult.TIE;
        }
        return AIResult.DONE;
    }

    public Prediction MinMax(Board board,Player player) {
        Integer[] free_boxes_locations = board.FreeBoxes();
        int free_boxes = free_boxes_locations.length;
        Player Maximize = this.player;
        Player rival = player.Rival();
        boolean isMaxPlayer = (rival == Maximize);
        Player winner = board.HasWinner();
        if (winner == rival) {
            return new Prediction(winner, free_boxes * (isMaxPlayer ? -1 : 1));
        }
        if (board.IsFull()) {
            return new Prediction(Player.NONE, 0);
        }

        Prediction best_move;
        if (player==Maximize) {
            best_move = new Prediction(player,  Integer.MAX_VALUE);
        } else {
            best_move = new Prediction(player,  Integer.MIN_VALUE);
        }

        for (int i = 0; i < free_boxes; i++) {

            int index = free_boxes_locations[i].intValue();

            Board imaginary_board = board.Clone();
            imaginary_board.Set(index, player);
            Prediction fake_move = MinMax(imaginary_board, rival);
            fake_move.position=index;

            if (isMaxPlayer) {
                if (fake_move.score > best_move.score) {
                    best_move = fake_move;
                }
            } else {
                if (fake_move.score < best_move.score) {
                    best_move = fake_move;
                }
            }
        }
        return best_move;
    }

}
