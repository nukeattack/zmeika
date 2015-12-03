package com.poison.zmeika.game.model;

/**
 * Created by Stas on 12/3/2015.
 */
public class GameBoard {
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int height;
    private LiveCell [] [] board;

    public GameBoard(int width, int height){
        board = new LiveCell[width][height];
        this.width = width;
        this.height = height;
    }

    public void addCell(LiveCell cell){
        board[cell.getPos().getX()][cell.getPos().getY()] = cell;
    }

    public LiveCell getCell(int x, int y){
        return board[x][y];
    }

    public void removeCell(int x, int y){
        board[x][y] = null;
    }
}
