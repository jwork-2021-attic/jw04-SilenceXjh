package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.monsters.SelectSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.RandomArray;
import com.anish.monsters.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[][] mons;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        mons = new Monster[16][16];

        RandomArray ra = new RandomArray(256);

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                int rank = ra.get(i * 16 + j);
                if(rank <= 43) {
                    mons[i][j] = new Monster(new Color(252, (rank - 1) * 6, 0), rank, world);
                }
                else if(rank <= 85) {
                    mons[i][j] = new Monster(new Color(510 - 6 * rank, 252, 0), rank, world);
                }
                else if(rank <= 127) {
                    mons[i][j] = new Monster(new Color(0, 252, (rank - 85) * 6), rank, world);
                }
                else if(rank <= 169) {
                    mons[i][j] = new Monster(new Color(0, 1014 - 6 * rank, 252), rank, world);
                }
                else if(rank <= 211) {
                    mons[i][j] = new Monster(new Color((rank - 169) * 6, 0, 252), rank, world);
                }
                else {
                    mons[i][j] = new Monster(new Color(252, (rank - 211) * 5, 252), rank, world);
                }
            }
        }

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                world.put(mons[i][j], 10 + 2 * j, 10 + 2 * i);
            }
        }

        SelectSorter<Monster> b = new SelectSorter<>();
        b.load(mons);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        getMonByRank(mons, Integer.parseInt(couple[0])).swap(getMonByRank(mons, Integer.parseInt(couple[1])));
    }

    private Monster getMonByRank(Monster[][] monsters, int rank) {
        for(int i = 0; i < monsters.length; ++i) {
            for(int j = 0; j < monsters[0].length; ++j) {
                if(monsters[i][j].getRank() == rank) {
                    return monsters[i][j];
                }
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int k = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (k < this.sortSteps.length) {
            this.execute(mons, sortSteps[k]);
            k++;
        }

        return this;
    }

}
