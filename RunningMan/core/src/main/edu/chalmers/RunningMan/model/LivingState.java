package edu.chalmers.RunningMan.model;

/**
 * Enum for representing the different states a living
 * entity can be in
 */
public enum LivingState {

    FACING_RIGHT(1),
    FACING_LEFT(-1),
    MOVING_RIGHT(1),
    MOVING_LEFT(-1),
    JUMPING_RIGHT(1),
    JUMPING_LEFT(-1),
    STANDING(0);

    private final int xDirection; // -1 moving backwords, 1 moving forward

    LivingState(final int xDirection){
        this.xDirection = xDirection;
    }

}
