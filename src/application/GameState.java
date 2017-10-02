package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameState {
    private HashMap<Integer, Point> points;
    private PlayerColor currentPlayer;
    private HashMap<PlayerColor, Player> players;
    private Random rdm;
    private ArrayList<Integer> rolls;

    public GameState(){
        points = new HashMap<Integer, Point>();
        currentPlayer = PlayerColor.BLANK;

        players = new HashMap<PlayerColor, Player>();

        players.put(PlayerColor.BLACK, new Player(PlayerColor.BLACK));
        players.put(PlayerColor.RED, new Player(PlayerColor.RED));

        rolls = new ArrayList<Integer>();
        rdm = new Random();

        initBoard();

        // TODO start the game, roll for first player, ...
        // for Testing:
        currentPlayer = PlayerColor.BLACK;
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

    private void initBoard() {
        // instantiate Points
        for (int i = 1; i <= 24; i++){ points.put(i, new Point()); }

        // setting up board
        points.get(1).set(PlayerColor.RED, 2);
        points.get(6).set(PlayerColor.BLACK, 5);
        points.get(8).set(PlayerColor.BLACK, 3);
        points.get(12).set(PlayerColor.RED, 5);

        points.get(13).set(PlayerColor.BLACK, 5);
        points.get(17).set(PlayerColor.RED, 3);
        points.get(19).set(PlayerColor.RED, 5);
        points.get(24).set(PlayerColor.BLACK, 2);
    }


    public ArrayList<Turn> getPossibleTurns(){
        //TODO get possible Turns (Lists of 1-4 single moves)
        return null;
    }

    // does not check if move is possible
    // executes a single move (one dice roll)
    private void move(Move m) throws IllegalMoveException{

        // TODO deal with hit checkers, out Checkers ( 1 > start/end > 24)
        if(m.getStart() > 24 || m.getEnd() > 24 || m.getStart() < 1 || m.getEnd() < 1) throw new IllegalMoveException();

        Point start = points.get(m.getStart());
        Point end = points.get(m.getEnd());

        if(start.getOwner() == PlayerColor.BLANK){
            throw new IllegalMoveException();
        } else if(start.getOwner() == end.getOwner()){
            // start/end has same owner
            start.decCheckers();
            end.incCheckers();

        } else if(end.getOwner() == PlayerColor.BLANK){
            // end is BLANK, set new owner
            start.decCheckers();
            end.incCheckers();
            end.setOwner(start.getOwner());

        } else if (end.getOwner() != PlayerColor.BLANK && end.getCheckers() == 1){
            // only if opponent has exactly one checker here
            // count of checkers remains the same
            start.decCheckers();
            // increase the hitCheckers count
            players.get(end.getOwner()).incHit();
            end.setOwner(start.getOwner());

        } else {
            throw new IllegalMoveException();
        }
    }

    //TODO executed by UI; Params: List of Turns
    // execute turn/s, roll dice, set new currentPlayer
    public void doTurn(Turn t){
        for(Move m :t.getMoves()){
            try {
                move(m);
            } catch (IllegalMoveException e) {
                System.out.print("Illegal Move from" + m.getStart() + " to " + m.getEnd());
            }
        }
        // end turn, next player
        roll();

        if(currentPlayer == PlayerColor.RED){
            currentPlayer = PlayerColor.BLACK;
        } else {
            currentPlayer = PlayerColor.RED;
        }
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public String toString() {
        String str = players.get(PlayerColor.BLACK).toString() + "\n" + players.get(PlayerColor.RED).toString() + "\n";
        for(HashMap.Entry<Integer, Point> entry : points.entrySet()){
            str += entry.getKey() + "[" + entry.getValue().toString() + "] ";
        }
        return str + "\n";
    }

    public class IllegalMoveException extends Throwable { }
}
