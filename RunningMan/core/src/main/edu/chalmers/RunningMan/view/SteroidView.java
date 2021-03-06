package edu.chalmers.RunningMan.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chalmers.RunningMan.model.gameobject.Steroid;

/**
 * The view for the steroid
 */
public class SteroidView extends Actor {
    private final Steroid steroid;
    private Texture texture;

    public SteroidView(Steroid steroid){
        this.steroid = steroid;
        try {
            texture = new Texture(Gdx.files.internal("spruta.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Batch batch, float deltaTime){
        if(!steroid.isPickedUp()) {
            batch.begin();
            batch.draw(texture, steroid.getPosition().getX(), steroid.getPosition().getY(), steroid.getSize().getWidth(), steroid.getSize().getHeight());
            batch.end();
        }
    }

}
