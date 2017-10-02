package application;


public class Point {
    private PlayerColor owner;
    private Integer checkers;

    public Point(){
        owner = PlayerColor.BLANK;
        checkers = 0;
    }

    public Integer getCheckers() {
        return checkers;
    }

    public void setCheckers(Integer checkers) {
        this.checkers = checkers;
    }

    public void incCheckers(){
        checkers++;
    }
    public void decCheckers(){
        checkers--;
        if(checkers == 0) this.owner = PlayerColor.BLANK;
    }


    public PlayerColor getOwner() {
        return owner;
    }

    public void setOwner(PlayerColor owner) {
        this.owner = owner;
    }

    public void set(PlayerColor c, Integer i){
        this.owner = c;
        this.checkers = i;
    }

    public String toString(){
        if(owner != PlayerColor.BLANK) {
            return owner.toString() + ":" + checkers.toString();
        } else {
            return "";
        }
    }

}
