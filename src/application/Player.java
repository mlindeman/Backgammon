package application;

public class Player {
    private PlayerColor color;

    public Player(PlayerColor c){
        color = c;
    }

    public String toString(){
        return "Player " + color;
    }


}
