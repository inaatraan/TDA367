package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;

/**
 * Created by Armand on 2015-05-14.
 */
public class BulletTest extends Assert {
/*
    Kom ih�g att �ndra i enemy s� att r�tt enum anv�nds
    Testa positionen
    Testa farten
    testa setX och setY
    Om bullet r�r sig �t ett viss h�ll ska player ha en viss state
    Det ska inte g� att skjuta oftate �n weapons delay
*/
    private LivingState livingState;
    private Player player;
    private Position position;
    private Bullet bullet;
    private Size size;
    private Weapon weapon;
    private static final float DELTATIME = 5f;

    @Before
    public void setUp(){
        size = new Size(1,1);
        position = new Position(10,10);
        player = new Player(position,size,100);
        weapon = new Weapon(player);
    }

    public void testGetBulletSpeed(){
        bullet = new Bullet(size,player.getPosition(),player.getLivingState());
        assertFalse(400f == bullet.getVelocity());
    }

    public void testMoveBulletRight(){
        player.moveRight(DELTATIME);
        bullet = new Bullet(size,player.getPosition(),player.getLivingState());
        float pos = bullet.getPosition().getX();
        bullet.moveBullet(DELTATIME);
        assertTrue(bullet.getPosition().getX()>  pos);
    }

    public void testIsOutOfBounds(){

    }

    public void testAcceptVisitor()  {

    }

}