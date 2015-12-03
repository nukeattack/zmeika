package com.poison.zmeika.game.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 12/3/2015.
 */
public class BoardModel {
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
    private CellModel[] [] board;

    public BoardModel(int width, int height){
        board = new CellModel[width][height];
        this.width = width;
        this.height = height;
    }

    public void addCell(CellModel cell){
        board[cell.getPos().getX()][cell.getPos().getY()] = cell;
    }

    public CellModel getCell(int x, int y){
        return board[x][y];
    }

    public void removeCell(int x, int y){
        board[x][y] = null;
    }

    public List<CellModel> getNeighbours(int x, int y){
        int startX = x > 0 ? x - 1 : x;
        int startY = y > 0 ? y - 1 : y;
        int endX = x < width - 1 ? x + 1 : x;
        int endY = y < height -1 ? y + 1 : y;
        List<CellModel> result = new LinkedList<CellModel>();
        for(int i = startX; i <= endX; i++){
            for(int j = startY; i <= endY; j++){
                if(!(i == x && j == y)){
                    if(board[x][y] != null){
                        result.add(board[x][y]);
                    }
                }
            }
        }
        return result;
    }
}
