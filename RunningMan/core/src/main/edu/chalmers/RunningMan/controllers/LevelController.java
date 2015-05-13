package edu.chalmers.RunningMan.controllers;

import com.badlogic.gdx.Gdx;
import edu.chalmers.RunningMan.entities.Bullet;
import edu.chalmers.RunningMan.entities.Level;
import edu.chalmers.RunningMan.entities.Player;
import edu.chalmers.RunningMan.views.LevelView;

import java.util.List;

import java.util.List;

/**
 * A class to represent the Controller of a level
 * @author Jesper Olsson
 */
public class LevelController implements IEntityController {
    private Level level;
    private List<Bullet> bullets;
    private Player player;

    public LevelController(Level level, List<Bullet> bullets){
        this.level = level;
        this.bullets = bullets;
        this.player = player;
    }

    /**
     *
     * @param deltaTime
     */

    public void update(float deltaTime){
        for(int i = 0; i < bullets.size(); i ++ ){
            if((bullets.get(i).isOutOfBounds())){
                bullets.remove(i);
            }
        }
        level.checkCollisions(bullets);
    }



}
