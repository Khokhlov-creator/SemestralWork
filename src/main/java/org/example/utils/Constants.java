package org.example.utils;
import java.awt.*;          //Constants used in classes
public class Constants {
    // Screen dimensions
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int CELL_SIZE = 20;

    // Player properties
    public static final int PLAYER_WIDTH = 5;
    public static final int PLAYER_HEIGHT = 5;
    public static final int PLAYER_SPEED = 2;
    public static final int PLAYER_MAX_HEALTH = 6;


    // Game title
    public static final String GAME_TITLE = "S.T.A.L.K.E.R. crawler";

    // Prevent instantiation
    private Constants() {
        throw new AssertionError();
    }
}