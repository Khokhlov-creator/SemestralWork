package org.example.entities;

import java.awt.*;

//will implement enemies
public class Enemy extends Entity{
    public Enemy(int x, int y, int width, int height, int maxHealth) {
        super(x, y, width, height, maxHealth);
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public void render(Graphics g) {
        return;
    }
}
