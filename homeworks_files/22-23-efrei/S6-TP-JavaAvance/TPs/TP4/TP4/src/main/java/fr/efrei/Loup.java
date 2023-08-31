package fr.efrei;

public class Loup {

    private Direction direction;

    public enum Direction {
        NORD, SUD, EST, OUEST
    }

    public Loup(){
        this.direction = Direction.NORD;
    }

    public void tourner(){
        switch (direction){
            case NORD :
                direction = Direction.EST;
                break;
            case EST :
                direction = Direction.SUD;
                break;
            case SUD :
                direction = Direction.OUEST;
                break;
            case OUEST :
                direction = Direction.NORD;
                break;
        }
    }

    public Direction getDirection() {
        return direction;
    }

}
