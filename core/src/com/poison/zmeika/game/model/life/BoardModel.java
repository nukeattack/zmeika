package com.poison.zmeika.game.model.life;

import com.poison.zmeika.game.controller.utils.PoolController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 12/3/2015.
 */
public class BoardModel {
    public int width;
    public int height;
    public CellModel[] [] board;
    public List<CellModel> cells;

    public BoardModel(int width, int height){
        board = new CellModel[width][height];
        cells = new LinkedList<CellModel>();
        this.width = width;
        this.height = height;
    }

    public void addCell(CellModel cell){
        board[cell.position.x][cell.position.y] = cell;
        cells.add(cell);
    }

    public CellModel getCell(int x, int y){
        return board[x][y];
    }

    public void removeCell(int x, int y){
        CellModel cell = board[x][y];
        if(cell != null){
            cells.remove(board[x][y]);
        }
        PoolController.cellModels.release(cell);
        board[x][y] = null;
    }

//    public List<CellModel> getNeighbours(int x, int y){
//        int startX = x > 0 ? x - 1 : x;
//        int startY = y > 0 ? y - 1 : y;
//        int endX = x < width - 1 ? x + 1 : x;
//        int endY = y < height -1 ? y + 1 : y;
//        List<CellModel> result = new LinkedList<CellModel>();
//        for(int i = startX; i <= endX; i++){
//            for(int j = startY; i <= endY; j++){
//                if(!(i == x && j == y)){
//                    if(board[x][y] != null){
//                        result.add(board[x][y]);
//                    }
//                }
//            }
//        }
//        return result;
//    }

    public List<CellModel> getCells() {
        return cells;
    }
}
