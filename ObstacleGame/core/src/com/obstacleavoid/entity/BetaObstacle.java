package com.obstacleavoid.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.obstacleavoid.config.GameConfig;

public class BetaObstacle extends GameObjectBase{
    private float ySpeed = GameConfig.BETA_OBSTACLE_SPEED;
    private boolean hit;

    public  BetaObstacle() {
        super(GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    public void update() {
        setY(getY() - ySpeed);
    }

    public boolean isPlayerColliding(Player player) {
        Circle playerBounds = player.getBounds();
        // check if playerBounds overlap obstacle bounds
        boolean overlaps = Intersector.overlaps(playerBounds, getBounds());

        hit = overlaps;

        return overlaps;
    }

    public boolean isNotHit() {
        return !hit;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }


}
