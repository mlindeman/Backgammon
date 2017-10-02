package application;

public class Player {
    private Integer checkersHit;
    private Integer checkersOut;
    private PlayerColor color;

    public Player(PlayerColor c){
        color = c;
        checkersHit = 0;
        checkersOut = 0;
    }

    public void incHit(){
        this.checkersHit++;
    }

    public void decHit(){
        this.checkersHit--;
    }

    public Integer getCheckersHit(){
        return checkersHit;
    }

    public void incOut(){
        checkersOut++;
    }

    public Integer getCheckersOut(){
        return checkersOut;
    }

    public String toString(){
        return "Player " + color + "[Hit:" + checkersHit + " | Out:" + checkersOut + "]";
    }


}
