
package application;

import java.util.ArrayList;

public class Turn {
    private ArrayList<Move> moves; // ordered List

    public Turn(){
        moves = new ArrayList<Move>();
    }

    public void addMove(Move m){
        moves.add(m);
    }

    public void addMove(Integer start, Integer end){
        moves.add(new Move(start,end));
    }

    public ArrayList<Move> getMoves(){
        return moves;
    }

}
