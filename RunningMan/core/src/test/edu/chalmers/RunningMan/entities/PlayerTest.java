package edu.chalmers.RunningMan.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * @author Jesper Olsson
 */
public class PlayerTest extends Assert{
    private Player player;
    private Position position = new Position(50f, 20f);
    private final Size size = new Size(10f, 10f);
    private Weapon weapon;
    private static final float DELTATIME = 2f;
    private int killCount;

    @Before
    public void setUp(){
        killCount = 0;
        player = new Player(position, size, 100 );
        //Needs to be done to set hasLandedFirstTime true
        final float groundPosY = position.getY() - 10f;
        final float groundPosX = position.getX();
        final Ground ground = new Ground(new Position(groundPosX, groundPosY), size);
        player.visit(ground);
    }
    @Test
    public void testGetPosition(){
        assertEquals(position, player.getPosition());
    }

    @Test
    public void testGetSize(){
        assertEquals(size, player.getSize());
    }
    @Test
    public void testSetX(){
        position.setX(10f);
        player.setX(position.getX());
        assertEquals(position.getX(), player.getPosition().getX(), 0);
    }
    @Test
    public void testSetY(){
        position.setY(15f);
        player.setY(position.getY());
        assertEquals(position.getY(), player.getPosition().getY(), 0);
    }
    @Test
    public void testGetHitbox(){
        final Rectangle rec = new Rectangle((int)position.getX(), (int)position.getY(),
                (int)size.getWidth(), (int)size.getHeight());
        assertEquals(rec, player.getHitbox());
    }
    @Test
    public void testSetPosition(){
        position = new Position(40f, 40f);
        player.setPosition(position);
        assertEquals(position, player.getPosition());
    }

    @Test
    public void testSetVelocityX(){
        final float velocityY = 2f;
        player.setVelocityY(velocityY);
        assertEquals(velocityY, player.getVelocityY(), 0);
    }

    @Test
    public void testSetVelocityY(){
        final float velocityX = 3f;
        player.setVelocityX(velocityX);
        assertEquals(velocityX, Math.abs(player.getVelocityX()), 0);
    }

    @Test
    public void testGetScore(){

    }

    @Test
    public void testMoveLeftVelocity(){
        player.moveLeft(DELTATIME);
        assertTrue(player.getVelocityX() < 0);
    }

    @Test
    public void testMoveLeftPosition(){
        final float posX = player.getPosition().getX();
        player.moveLeft(DELTATIME);
        assertTrue(player.getPosition().getX() < posX);
    }

    @Test
    public void testMoveLeftPlayerState(){
        player.moveLeft(DELTATIME);
        assertTrue(player.getPlayerState() == PlayerState.MOVING_LEFT);
    }

    @Test
    public void testMoveRightVelocity(){
        player.moveRight(DELTATIME);
        assertTrue(player.getVelocityX() > 0);
    }

    @Test
    public void testMoveRightPosition(){
        final float posX = player.getPosition().getX();
        player.moveRight(DELTATIME);
        assertTrue(player.getPosition().getX() > posX);
    }

    @Test
    public void testMoveRightPlayerState(){
        player.moveRight(DELTATIME);
        assertTrue(player.getPlayerState() == PlayerState.MOVING_RIGHT);
    }

    @Test
    public void testJumpVelocity(){
        final float velocityY = player.getVelocityY();
        player.setOnGround(true);
        player.jump(DELTATIME);
        assertTrue(player.getVelocityY() > velocityY);
    }

    @Test
    public void testApplyForce(){
        final Gravity gravity = new Gravity(-800f);
        final float velocityY = player.getVelocityY();
        final float newVelocityY = gravity.getNewVelocity(velocityY, DELTATIME);
        player.applyForce(DELTATIME);
        assertEquals(newVelocityY, player.getVelocityY(), 0.01f);
    }

    @Test
    public void testSteroidPickedUp(){
        final Steroid steroid = new Steroid(position, size);
        steroid.acceptVisitor(player);
        assertTrue(steroid.isPickedUp());
    }

    @Test
    public void testVisitSteroidVelocity(){
        final Steroid steroid = new Steroid(position, size);
        final float velocityX = Math.abs(player.getVelocityX());
        player.visit(steroid);
        assertTrue(Math.abs(player.getVelocityX()) > velocityX);
    }

    @Test
    public void testVisistEnemy(){
        final Enemy enemy = new Enemy(position, size, 10);
        player.visit(enemy);
        assertTrue(player.isDead());
    }

    @Test
    public void testFinishLevel(){
        final Helicopter helicopter = new Helicopter(position, size);
        player.visit(helicopter);
        assertTrue(player.hasFinishedLevel());
    }

    @Test
    public void testPlayerMovingWhenFinishedLevel(){
        final Helicopter helicopter = new Helicopter(new Position
                (position.getX() + 10, position.getY()), size);
        float initialX = position.getX();
        helicopter.acceptVisitor(player);
        player.visit(helicopter);
        float newX = player.getPosition().getX();
        assertTrue(newX > initialX);
    }

}