package edu.chalmers.RunningMan.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chalmers.RunningMan.entities.Time;


/**
 * Created by Jesper on 5/14/2015.
 */
public class TimeView extends HudFont {
    private Time time;
    private CharSequence stringTime;

    public TimeView(Time time){
        this.time = time;
        generateFont();
    }

    public void draw(Batch batch, float deltaTime){
        time.update(deltaTime);
        stringTime ="Time: "+ ((int)time.getMaxTime() - time.getTimeInteger()) ;
        batch.begin();
        font.draw(batch, stringTime, 100, Gdx.graphics.getHeight() - 20);
        batch.end();
    }
}
