package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.*;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;

    public WorldScreen() {
        world = new World();

        bro = new Calabash(new Color(204, 0, 0), 1, world);

        world.put(bro, 0, 0);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if(bro.getX() == World.WIDTH-1 && bro.getY() == World.HEIGHT-1) {
            return this;
        }

        if(key.getKeyCode() == KeyEvent.VK_DOWN) {
            int newX = bro.getX();
            int newY = bro.getY() + 1;
            if(newY < World.HEIGHT && world.get(newX, newY).getClass() == Floor.class) {
                Thing t = world.get(newX, newY);
                world.put(t, bro.getX(), bro.getY());
                bro.moveTo(newX, newY);
            }
        }
        else if(key.getKeyCode() == KeyEvent.VK_UP) {
            int newX = bro.getX();
            int newY = bro.getY() - 1;
            if(newY >= 0 && world.get(newX, newY).getClass() == Floor.class) {
                Thing t = world.get(newX, newY);
                world.put(t, bro.getX(), bro.getY());
                bro.moveTo(newX, newY);
            }
        }
        else if(key.getKeyCode() == KeyEvent.VK_LEFT) {
            int newX = bro.getX() - 1;
            int newY = bro.getY();
            if(newX >= 0 && world.get(newX, newY).getClass() == Floor.class) {
                Thing t = world.get(newX, newY);
                world.put(t, bro.getX(), bro.getY());
                bro.moveTo(newX, newY);
            }
        }
        else if(key.getKeyCode() == KeyEvent.VK_RIGHT) {
            int newX = bro.getX() + 1;
            int newY = bro.getY();
            if(newX < World.WIDTH && world.get(newX, newY).getClass() == Floor.class) {
                Thing t = world.get(newX, newY);
                world.put(t, bro.getX(), bro.getY());
                bro.moveTo(newX, newY);
            }
        }

        return this;
    }

}
