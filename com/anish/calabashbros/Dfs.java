package com.anish.calabashbros;

public class Dfs {
    private int[][] maze;
    private String plan = "";
    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] visited;

    public void load(int[][] maze) {
        this.maze = maze;
        visited = new boolean[maze.length][maze[0].length];
    }

    public void start(int x,int y) {
        visited[x][y] = true;
        plan += "" + x + "," + y + "\n";
        for(int k = 0; k < 4; ++k) {
            int x1 = x + directions[k][0];
            int y1 = y + directions[k][1];
            if(x1 >= 0 && x1 < maze.length && y1 >= 0 && y1 < maze[0].length && maze[x1][y1] == 1 && !visited[x1][y1]) {
                start(x1, y1);
                plan += "" + x + "," + y + "\n";
            }
        }
    }

    public String getPlan() {
        return plan;
    }
}