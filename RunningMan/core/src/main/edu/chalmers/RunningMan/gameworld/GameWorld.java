package edu.chalmers.RunningMan.gameworld;

import edu.chalmers.RunningMan.controllers.*;
import edu.chalmers.RunningMan.model.*;
import edu.chalmers.RunningMan.model.level.Level;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Enemy;
import edu.chalmers.RunningMan.model.level.mapobjects.livingentities.objects.Player;
import edu.chalmers.RunningMan.utils.map.MapHandler;
import edu.chalmers.RunningMan.utils.map.MapHandlerException;
import edu.chalmers.RunningMan.model.Timer;
import edu.chalmers.RunningMan.audio.AudioController;
import edu.chalmers.RunningMan.views.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Created by JohanTobin on 2015-04-22.
 */

public class GameWorld implements PropertyChangeListener {

    private Player player;
    private Level level;
    private List<IEntityController> controllers;
    private MapHandler mapHandler;
    private List<AbstractPhysicalObject> mapObjects;
    private LevelView levelView;
    private Factory factory;
    private AudioController audioController;
    private HudView hudView;
    private PropertyChangeSupport pcs;
    private Timer timerSinceDeath;
    private HelicopterController helicopterController;
    private String levelName;
    private Timer loadTimer;
    private List<Enemy> enemies;
    private LevelController levelController;
    private PlayerController playerController;

    private static final float DEATH_ANIMATION_TIME = 1.15f;

    public GameWorld(String levelName) {
        this.levelName = levelName;
        startLevel();
    }

    private void startLevel(){
        loadTimer = new Timer(4);
        loadTimer.start();
        loadLevel();
        controllers = factory.getControllers();
        pcs = new PropertyChangeSupport(this);
        timerSinceDeath = new Timer(DEATH_ANIMATION_TIME);
        helicopterController = factory.getHelicopterController();
    }

    private final void loadLevel() {
        try {
            mapHandler = new MapHandler(levelName);
            mapObjects = mapHandler.getPhysicalObjectsList();
            enemies = mapHandler.getEnemies();
            factory = new Factory(mapObjects, enemies, levelName);
            audioController = factory.getAudioController();
            player = factory.getPlayer();
            player.addPropertyChangeListener(this);
            playerController = factory.getPlayerController();
            level = factory.getLevel();
            levelView = factory.getLevelView();
            levelController = factory.getLevelController();
            level.addPropertyChangeListener(this);
            player.addPropertyChangeListener(level);
            hudView = new HudView(level);
            audioController.playMusic();
        } catch (MapHandlerException e) {
            System.out.println("loadLevel in GameWorld");
        }
    }

    public void update(float deltaTime) {
        loadTimer.update(deltaTime);
        if(loadTimer.isTimeUp()) {
            if (!player.isDead() && !player.hasFinishedLevel()) {
                updateControllers(deltaTime);
            } else {
                timerSinceDeath.start();
                timerSinceDeath.update(deltaTime);
                updateRemainingControllers(deltaTime);
                if (timerSinceDeath.isTimeUp()) {
                    audioController.stopMusic();
                    if (player.isDead()) {
                        pcs.firePropertyChange("restartLevel", null, null);
                    } else {
                        setHighScores();
                        pcs.firePropertyChange("showHighScore", null, null);

                    }
                    timerSinceDeath.resetTime();
                }
            }
            levelView.draw();
            hudView.draw();
        }
    }

    private void updateControllers(float deltaTime){
        for (IEntityController controller : controllers) {
            controller.update(deltaTime);
        }
    }

    /**
     * The controllers that should be updated after
     * the level is finished/player is dead/time is up
     * @param deltaTime the time difference
     */
    private void updateRemainingControllers(float deltaTime){
        helicopterController.update(deltaTime);
        playerController.update(deltaTime);
        levelController.update(deltaTime);
    }

    private void setHighScores(){
        level.setPlayerScore();
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt.getPropertyName(), null, null);
    }
}
