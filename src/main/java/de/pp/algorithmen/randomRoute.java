package de.pp.algorithmen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class randomRoute {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public void prepareImage() throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
        drawMaze((Graphics2D) image.getGraphics(), 100, 100, 150, 150);
        ImageIO.write(image, "png", new File("out.png"));
    }

    public void drawMaze(Graphics2D graphics2D, int startX, int startY, int destX, int destY) {
        // TODO draw a random maze from start(x,y) to dest(x,y)
        Random r = new Random();
        var path = new boolean[WIDTH][HEIGHT];
        path[startX][startY] = true;
        int x = startX;
        int y = startY;
        graphics2D.setPaint(Color.red);
        for (int i = 0; i < 10; i++) {
            var array = walk(path, x, y);
            x = array[0];
            y = array[1];
        }
        drawLine(path, graphics2D);
    }

    /**
     * Walks one step in a random direction that is not already visited.
     *
     * @param path The Path where every step you made is stored
     * @param x    The current X coordinate of the algorithmus
     * @param y    The current Y coordinate of the algorithmus
     * @return The new coordinates of the algorithmus
     */
    public int[] walk(boolean[][] path, int x, int y) {
        Random r = new Random();
        while (true) {
            int xTry = x;
            int yTry = y;
            //Select one direction randomly
            switch (r.nextInt(4)) {
                case 0 -> xTry++;
                case 1 -> xTry--;
                case 2 -> yTry++;
                case 3 -> yTry--;
            }
            //Check if the direction was already visited
            if (!path[xTry][yTry] && xTry < WIDTH && yTry < HEIGHT) {
                x = xTry;
                y = yTry;
                path[x][y] = true;
                return new int[]{x, y};
            }
            //If you cant go in any direction you should start backtracking
            if (path[x + 1][y] && path[x - 1][y] && path[x][y + 1] && path[x][y - 1]) {
                //start backtracking
                /*
                Aufgabe: Aus einer Sackgasse wieder herausfinden.
                Möglichkeit: Backtracking verwenden.
                            Falls der Algorithmus in einer Sackgasse ist oder alle wege schon getestet:
                            Bis zur letzten Abzweigung zurückgehen
                            Wieder oben anfangen.
                            ELSE
                            einen zufälligen Weg auswählen.
                 */
            }
        }
    }

    /**
     * Draws the given Path
     *
     * @param path       The given Path
     * @param graphics2D The Image
     */
    public void drawLine(boolean[][] path, Graphics2D graphics2D) {
        for (int i = 0; i < path.length; i++) {
            for (int i1 = 0; i1 < path[i].length; i1++) {
                if (path[i][i1]) {
                    graphics2D.drawLine(i, i1, i, i1);
                }
            }
        }
    }
}