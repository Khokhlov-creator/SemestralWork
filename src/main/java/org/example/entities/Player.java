package org.example.entities;

import java.awt.*;
import org.example.utils.Constants;


public class Player extends Entity {
    private int velocityX; // Horizontal velocity
    private int velocityY; // Vertical velocity
    private double rotation;


    public Player(int x, int y, int width, int height, int maxHealth) {
        super(x, y, width, height, maxHealth);
        this.velocityX = 0;
        this.velocityY = 0;
        this.rotation = 0;
    }


    @Override
    public void update() {  // Update player's position based on velocity

        x += velocityX;
        y += velocityY;
    }

    public void rotateTowards(int targetX, int targetY) {
        // Calculate the angle between the player and the target position
        double angle = Math.atan2(targetY - (y + modelHeight / 2), targetX - (x + modelWidth / 2));

        // Convert the angle from radians to degrees
        double angleDegrees = Math.toDegrees(angle);

        // Set the rotation angle for the player
        rotation = angleDegrees;
    }

    @Override
    public void render(Graphics g) {  // Render the player sprite or graphics

        g.setColor(Color.BLUE);
        g.fillRect(x, y, modelWidth, modelHeight);
    }

    public void move(int dx, int dy) {          //Movement of player (won't allow to go out of borders)
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        } else if (x + modelWidth > Constants.SCREEN_WIDTH) {
            x = Constants.SCREEN_WIDTH - modelWidth;
        }
        if (y < 0) {
            y = 0;
        } else if (y + modelHeight > Constants.SCREEN_HEIGHT) {
            y = Constants.SCREEN_HEIGHT - modelHeight;
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}

