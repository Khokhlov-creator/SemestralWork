package org.example.level;

//tmp map generator (taken from internet, implemented soon)

import org.example.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class LevelGenerator extends JFrame {

    private final char[][] map;
    private final JLabel[][] labels;

    public LevelGenerator(int width, int height) {
        int CELL_SIZE = 20;
        int WIDTH = CELL_SIZE * width;
        int HEIGHT = CELL_SIZE * height;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        map = new char[width][height];

        labels = new JLabel[width][height];

        JPanel panel = new JPanel(new GridLayout(width, height));
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> saveMapToFile());

        // Inicializace mapy a přidání JLabel prvků do panelu
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = '.';
                labels[i][j] = new JLabel(String.valueOf(map[i][j]));
                labels[i][j].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[i][j].addMouseListener(new MapMouseListener(i, j));
                panel.add(labels[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Třída pro zachytávání událostí myši na jednotlivých buňkách mapy
    private class MapMouseListener extends MouseAdapter {
        private final int row;
        private final int col;

        public MapMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                handleLeftClick();
            } else if (SwingUtilities.isRightMouseButton(e)) {
                handleRightClick();
            }
            labels[row][col].setText(String.valueOf(map[row][col]));
        }

        private void handleLeftClick() {
            switch (map[row][col]) {
                case '.':
                    map[row][col] = '#';
                    break;
                case '#':
                    map[row][col] = 'E';
                    break;
                case 'E':
                    map[row][col] = 'P';
                    break;
                default:
                    break;
            }
        }

        private void handleRightClick() {
            switch (map[row][col]) {
                case '#':
                    map[row][col] = '.';
                    break;
                case 'E':
                    map[row][col] = '#';
                    break;
                case 'P':
                    map[row][col] = 'E';
                    break;
                default:
                    break;
            }
        }
    }

    private void saveMapToFile() {
        try (FileWriter writer = new FileWriter("src/main/resources/levels/map.txt")) {
            for (char[] row : map) {
                writer.write(row);
                writer.write('\n'); // Add a new line after each row
            }
            System.out.println("Map saved to " + "src/main/resources/levels/map.txt");
        } catch (IOException e) {
            System.err.println("Error saving map to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LevelGenerator gui = new LevelGenerator(Constants.SCREEN_WIDTH/Constants.CELL_SIZE, Constants.SCREEN_HEIGHT/Constants.CELL_SIZE);
            gui.setVisible(true);
        });
    }
}
