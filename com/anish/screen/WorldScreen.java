package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.*;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;
    String[] paths;

    public WorldScreen() {
        world = new World();

        bro = new Calabash(new Color(204, 0, 0), 1, world);

        world.put(bro, 0, 0);

        Dfs dfs = new Dfs();
        dfs.load(world.getMaze());
        dfs.start(0, 0);

        String plan = dfs.getPlan();
        paths = plan.split("\n");
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    private int i = 1;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if(i < paths.length) {
            String[] couple = paths[i].split(",");
            int newX = Integer.parseInt(couple[0]);
            int newY = Integer.parseInt(couple[1]);
            Thing t = world.get(newX, newY);
            world.put(t, bro.getX(), bro.getY());
            bro.moveTo(newX, newY);
            i++;
        }

        return this;
    }

}
