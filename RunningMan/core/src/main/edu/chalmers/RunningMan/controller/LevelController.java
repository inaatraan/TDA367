package edu.chalmers.RunningMan.controller;

import edu.chalmers.RunningMan.model.gameobject.Bullet;
import edu.chalmers.RunningMan.model.gameobject.Level;

import java.util.List;

/**
 * A class to represent the Controller of a level
 * @author Jesper Olsson
 */
public class LevelController implements IEntityController {
    private final Level level;
    private final List<Bullet> bullets;

    public LevelController(Level level, List<Bullet> bullets){
        this.level = level;
        this.bullets = bullets;
    }


    public void update(float deltaTime){
        for(int i = 0; i < bullets.size(); i ++ ){
            if((bullets.get(i).isOutOfBounds())){
                bullets.remove(i);
            }
        }
        level.checkCollisions(bullets);
        level.checkTime();
    }



}
