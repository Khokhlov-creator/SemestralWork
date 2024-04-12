package org.example.entities;

import java.awt.*;
import org.example.utils.Constants;


public class Player extends Entity {
    private int velocityX; // Horizontal velocity
    private int velocityY; // Vertical velocity


    public Player(int x, int y, int width, int height, int maxHealth) {
        super(x, y, width, height, maxHealth);
        this.velocityX = 0;
        this.velocityY = 0;
    }


    @Override
    public void update() {  // Update player's position based on velocity

        x += velocityX;
        y += velocityY;
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
}

