package application;

import java.util.*;

import static application.PlayerColor.BLACK;
import static application.PlayerColor.RED;

public class GameState {
    //private HashMap<Integer, Point> points;
    private Board board;
    private PlayerColor currentPlayer;
    private Random rdm;
    private ArrayList<Integer> rolls;

    public GameState(){
        currentPlayer = PlayerColor.BLANK;

        rolls = new ArrayList<Integer>();
        rdm = new Random();

        board = new Board();

        // TODO start the game, roll for first player, ...
        // for Testing: BLACK starts
        currentPlayer = BLACK;
        roll();
    }

    // clears rolls, rolls new
    private void roll(){
        Integer r1 = rdm.nextInt(6) + 1;
        Integer r2 = rdm.nextInt(6) + 1;

        rolls.clear();

        if(r1 == r2){
            rolls.add(r1);
            rolls.add(r1);
            rolls.add(r1);
            rolls.add(r1);
        } else {
            rolls.add(r1);
            rolls.add(r2);
        }
    }



    //TODO get possible Turns (Lists of 1-4 single moves) for current player and roll
    public ArrayList<Turn> getPossibleTurns(){


        /*if (currentPlayer == RED && board.getRedHitPoint().getCheckers() > 0) {
            if (!start.equals(board.getRedHitPoint())) {
                throw new IllegalMoveException();
            }
        } else if (currentPlayer == BLACK && board.getBlackHitPoint().getCheckers() > 0) {
            if (!start.equals(board.getRedHitPoint())) {
                throw new IllegalMoveException();
            }
        } else*/
        return null;
    }

    //TODO tests if Turn is possible
    private boolean isValideTurn(Turn t){
        return true;
    }

    // does not check if move is possible
    // executes a single move (one dice roll)
    // 0 represents startpoint of red
    // 25 represents startpoint of black
    // -1 means to bear off board
    private void move(Move m) throws IllegalMoveException{

        // TODO deal with hit checkers, out Checkers ( 1 > start/end > 24)
        // if(m.getStart() > 24 || m.getEnd() > 24 || m.getStart() < 1 || m.getEnd() < 1) throw new IllegalMoveException();

        Point start = board.getPoints().get(m.getStart());
        Point end = board.getPoints().get(m.getEnd());

        if (m.getEnd() == -1) {
            start.decCheckers();
            board.bearOff(currentPlayer);
        } else if(start.getOwner() == PlayerColor.BLANK){
            throw new IllegalMoveException();
        } else if(start.getOwner() == end.getOwner()){
            // start/end has same owner
            start.decCheckers();
            end.incCheckers();

        } else if(end.getOwner() == PlayerColor.BLANK){
            // end is BLANK, set new owner
            end.setOwner(start.getOwner());
            start.decCheckers();
            end.incCheckers();

        } else if (end.getOwner() != PlayerColor.BLANK && end.getCheckers() == 1){
            // only if opponent has exactly one checker here
            // count of checkers remains the same
            start.decCheckers();

            //move hit checker to out-point
            if (currentPlayer == RED) board.getRedHitPoint().incCheckers();
            if (currentPlayer == BLACK) board.getBlackHitPoint().incCheckers();

            end.setOwner(start.getOwner());

        } else {
            throw new IllegalMoveException();
        }
    }

    // execute turn/s, roll dice, set new currentPlayer (can be empty, if no possible moves exist)
    // called by GUI
    public void doTurn(Turn t){
        for(Move m : t.getMoves()){
                try {
                    move(m);
                } catch (IllegalMoveException e) {
                    System.out.println("Illegal Move from " + m.getStart() + " to " + m.getEnd());
                }
        }
       changePlayer();

        roll();
    }

    private void changePlayer(){
        // end turn, next player
        if(currentPlayer == RED){
            currentPlayer = BLACK;
        } else {
            currentPlayer = RED;
        }
    }

    // interface for GUI components
    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }
    public Board getBoard(){return board; }

    public String toString() {
        String str = "";
        for(HashMap.Entry<Integer, Point> entry : board.getPoints().entrySet()){
            str += entry.getKey() + "[" + entry.getValue().toString() + "] ";
        }
        return str + "\n";
    }

    public class IllegalMoveException extends Throwable { }
}
