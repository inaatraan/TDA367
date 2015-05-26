package edu.chalmers.RunningMan.utils.input;

/**
 * A class to handle the input from the keyboard
 */
public class Input {

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 4;
    public static final int LEFTBTN = 0;
    public static final int RIGHTBTN = 1;
    public static final int JUMPBTN = 2;
    public static final int SHOOTBTN = 3;

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for(int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    // set key which is pressed
    public static void setKey(int i, boolean b) {
        keys[i] = b;
    }

    // return true if key is down
    public static boolean isDown(int i) {
        return keys[i];
    }

    // return true if key is pressed
    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }
}
