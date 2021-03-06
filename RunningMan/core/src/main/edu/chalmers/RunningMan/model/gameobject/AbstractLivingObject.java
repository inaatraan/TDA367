package edu.chalmers.RunningMan.model.gameobject;

import edu.chalmers.RunningMan.model.ISize;
import edu.chalmers.RunningMan.model.Position;

/**
 * Abstract class for Living Objects
 * @author Jesper Olsson
 */
public abstract class AbstractLivingObject extends AbstractPhysicalObject implements IVisitor {

    private int hp;
    private final int maxHp;
    protected boolean isDead;

    public AbstractLivingObject(ISize size, Position position, int maxHp) {
        super(size, position);
        this.maxHp = maxHp;
        this.hp = maxHp;
        isDead = false;
    }

    public void setNewX(float delta, float velocity){

        setX(getPosition().getX() + velocity*delta);
    }

    public int getHp(){
        return this.hp;
    }

    public boolean isDead(){
        return this.isDead;
    }

    public void setHp(int newHp){
        if(newHp <= 0){
            this.hp = 0;
            this.isDead = true;
        }else if(newHp > this.maxHp){
            this.hp = maxHp;
        }else{
            this.hp = newHp;
        }
    }

    public void takeDamage(int damage){
        setHp(this.hp - damage);
    }
}
