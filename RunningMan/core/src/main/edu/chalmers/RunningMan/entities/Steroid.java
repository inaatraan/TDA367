package edu.chalmers.RunningMan.entities;

/**
 * A class to represent the power up steroid
 * @author  Jesper Olsson
 */

public class Steroid extends AbstractPowerUp {

    private static final int MAX_TIME = 10;
    private Time time;

    public Steroid(Position position, Size size){
        super(size, position);
    }

    @Override
    public void acceptVisitor(IVisitor visitor)
    {
        if(!isPickedUp() && visitor instanceof Player) {
            visitor.visit(this);
            setPickedUp(true);
            time = new Time();
        }
    }

    public boolean isTimeUp(){
        return getTime().getTimeInteger() >= MAX_TIME;
    }

    public Time getTime(){
        return time;
    }
}
