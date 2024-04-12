package org.example.entities;

import java.awt.*;

public abstract class Entity {
    protected int x;    //Position of Entity
    protected int y;    //Position of Entity
    protected int modelWidth;   //Model size of entity
    protected int modelHeight;  //Model size of entity
    protected int health;       //Health of Entity
    protected int maxHealth;    //Maximum health of entity
    protected boolean active;  //If entity is active or not


    public Entity(int x, int y, int width, int height, int maxHealth) {
        this.x = x;
        this.y = y;
        this.modelWidth = width;
        this.modelHeight = height;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.active = true;
    }

    public abstract void update();  //Updating entity state
    public abstract void render(Graphics g);    //Rendering entity

    public boolean intersection(Entity nEntity){    //Collision between entities
            return x<nEntity.x + nEntity.modelWidth && x+modelWidth > nEntity.modelWidth && y< nEntity.y + nEntity.modelHeight && y + modelHeight > nEntity.y;
    }

    public void damageTaken(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            active = false;
        }
    }
    public void healthRestored(int heal){
        health += heal;
        if (health > maxHealth)
            health = maxHealth;
    }

}