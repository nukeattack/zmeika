package com.poison.zmeika.game.mvc.model.logic;

import java.util.LinkedList;
import java.util.List;

public class Board {
    public int width;
    public int height;
    public Cell[] [] board;
    public List<Cell> cells;
    public int [][] neighborsCount;
    private int maxX;
    private int maxY;
    public int liveCellsCount = 0;
    public int deadCellsCount = 0;


    public Board(int width, int height) {
        neighborsCount = new int[width][height];
        board = new Cell[width][height];
        cells = new LinkedList<Cell>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                neighborsCount[i][j] = 0;
                board[i][j] = new Cell().setPosition(i,j).setAlive(false);
                cells.add(board[i][j]);
            }
        }
        this.width = width;
        this.height = height;
        deadCellsCount = width * height;
        this.maxX = width - 1;
        this.maxY = height - 1;
    }

    public Cell getCell(int x, int y){
        return board[x][y];
    }

    public Cell setAlive(int x, int y, boolean alive){
        if(alive){
            liveCellsCount ++;
            deadCellsCount --;
        }else{
            deadCellsCount ++;
            liveCellsCount --;
        }
        x = getRealX(x);
        y = getRealY(y);
        Cell result = board[x][y];
        result.isAlive = alive;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if(!(i == 0 && j == 0)){
                    int realX = getRealX(x + i);
                    int realY = getRealY(y + j);
                    if(alive){
                        neighborsCount[realX][realY]++;
                    }else{
                        neighborsCount[realX][realY]--;
                    }
                }
            }
        }
        return result;
    }

    private int getRealX(int x) {
        if(x < 0){
            return width + x;
        }
        if(x > maxX){
            return x - width;
        }
        return x;
    }

    private int getRealY(int y) {
        if(y < 0){
            return height + y;
        }
        if(y > maxY){
            return y - height;
        }
        return y;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
