package org.example.entities;

import org.example.utils.Constants;

import java.awt.*;

public class Bullet extends Entity{
    private int damage;
    private int dirX;
    private int dirY;
    public Bullet(int x, int y, int width, int height, int damage, int dirX, int dirY){
        super(x, y, width, height, 1);
        this.damage = damage;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    @Override
    public void update() {
        x+= 5*dirX; //Moves the bullet
        y+= 5*dirY;

        if(x<0 || y<0 || x > Constants.SCREEN_WIDTH || y > Constants.SCREEN_HEIGHT){
            active = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, modelWidth, modelHeight);
    }
}
