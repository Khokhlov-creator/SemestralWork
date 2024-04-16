package org.example;

import org.example.entities.Player;
import org.example.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JPanel {
    private Player player;
    private boolean[][] walls;
    private int objectX ;
    private int objectY ;

    public Game(){
        setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true); // Set the panel focusable to receive key events

        loadMapFromFile("src/main/resources/levels/map.txt");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                movePlayer(keyCode);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                objectX = e.getX();
                objectY = e.getY();
                repaint();
            }
        });

    }
    private void loadMapFromFile(String filePath){
        try (Scanner scanner = new Scanner(new File(filePath))){
            int height = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.isEmpty()){
                    continue;
                }
                int width = line.length();
                if (height == 0){
                    walls = new boolean[width][width];
                }
                for (int x=0; x<width; x++){
                    char symb = line.charAt(x);
                    if (symb == 'P'){               //Finding player on loaded map and puts player to that position
                        player = new Player(x*Constants.CELL_SIZE,
                                height * Constants.CELL_SIZE, Constants.CELL_SIZE,
                                Constants.CELL_SIZE, Constants.PLAYER_MAX_HEALTH);
                    } else if (symb == '#'){        //Wall sign
                        walls[x][height] = true;
                    }
                }           //TODO: More symbols to indicate enemies e.t.c.
                height++;
            }
        } catch (IOException e){
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if(player!=null){
            player.render(g2d);
            player.rotateTowards(objectX, objectY);
        }

        for (int x = 0; x<walls.length; x++){
            for (int y=0; y<walls[x].length; y++){
                if(walls[x][y]){
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(x*Constants.CELL_SIZE, y*Constants.CELL_SIZE, Constants.CELL_SIZE, Constants.CELL_SIZE);     //painting walls
                }
            }
        }
        if (player != null) {

            g2d.setColor(Color.RED);
            g2d.drawLine(player.getX() + Constants.CELL_SIZE / 2, player.getY(), objectX, objectY);
        }
    }

    private void movePlayer(int keyPressed){        //method to move player with KeyEvents
        int dx = 0;
        int dy = 0;
        switch (keyPressed){
            case KeyEvent.VK_W:
                dy = -Constants.PLAYER_SPEED;
                break;
            case KeyEvent.VK_S:
                dy = Constants.PLAYER_SPEED;
                break;
            case KeyEvent.VK_A:
                dx = -Constants.PLAYER_SPEED;
                break;
            case KeyEvent.VK_D:
                dx = Constants.PLAYER_SPEED;
                break;
        }
        if(player!=null){
            player.move(dx, dy);
            repaint();
        }
    }
}
