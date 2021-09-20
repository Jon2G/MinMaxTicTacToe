package com.jon2g.minmaxgato.enums;

public enum Player {
    NONE,O,X;

    public Player Rival() {
        switch (this){
            case O:
                return X;
            case X:
                return O;
            default:
                throw new IllegalArgumentException();
        }
    }
}
