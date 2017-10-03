package application;

import java.util.HashMap;

public class Board {
    private HashMap<Integer, Point> points;
    private Integer redOutCheckers;
    private Integer blackOutCheckers;

    public Board(){
        points = new HashMap<Integer, Point>();
        this.redOutCheckers = 0;
        this.blackOutCheckers = 0;
        init();
    }

    private void init() {
        // instantiate Points
        for (int i = 0; i <= 25; i++){ points.put(i, new Point()); }

        // setting up board
        points.get(0).set(PlayerColor.RED, 0);
        points.get(25).set(PlayerColor.BLACK, 0);

        points.get(1).set(PlayerColor.RED, 2);
        points.get(6).set(PlayerColor.BLACK, 5);
        points.get(8).set(PlayerColor.BLACK, 3);
        points.get(12).set(PlayerColor.RED, 5);

        points.get(13).set(PlayerColor.BLACK, 5);
        points.get(17).set(PlayerColor.RED, 3);
        points.get(19).set(PlayerColor.RED, 5);
        points.get(24).set(PlayerColor.BLACK, 2);
    }

    public HashMap<Integer, Point> getPoints() {
        return points;
    }

    public Point getBlackHitPoint() {
        return points.get(25);
    }

    public Point getRedHitPoint() {
        return points.get(0);
    }

    public boolean isPlayerAbleToPutOut(PlayerColor pc) {
        int counter = 0;
        if (pc == PlayerColor.RED) {
            for (int i = 19; i <= 24; i++) {
                counter += points.get(i).getCheckers();
            }
            counter += redOutCheckers;
        } else {
            for (int i = 1; i <= 6; i++) {
                counter += points.get(i).getCheckers();
            }
            counter += blackOutCheckers;
        }
        return counter < 15;
    }

    public void bearOff(PlayerColor pc) {
        if (pc == PlayerColor.RED) {
            redOutCheckers++;
        } else {
            blackOutCheckers++;
        }
    }

    public void setPoints(HashMap<Integer, Point> points) {
        this.points = points;
    }



}
