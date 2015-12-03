package com.poison.zmeika.game.controller;

import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.game.model.BoardModel;
import com.poison.zmeika.game.model.CellModel;
import com.poison.zmeika.game.view.BoardView;
import com.poison.zmeika.game.view.CellView;

import java.util.List;

/**
 * Created by Stanislav_Rogovskyi on 12/3/2015.
 */
public class BoardController {
    private BoardModel boardModel;
    private BoardView boardView;
    private GameObject rootObject;
    private int width = 20;
    private int height = 20;
    private float timePassed = 0.0f;
    private float stepPeriod = 0.3f;

    private boolean [][]removeBoard;
    private boolean [][]createBoard;
    private int [][] neighborsCount;

    public BoardController(){

    }

    public void setRootObject(GameObject rootObject){
        this.rootObject = rootObject;
    }

    public void init(){
        boardModel = new BoardModel(width, height);
        removeBoard = new boolean[width][height];
        createBoard = new boolean[width][height];
        neighborsCount = new int[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                neighborsCount[i][j] = 0;
            }
        }
        boardView = new BoardView();
        for(int i = 0; i < 20; i++){
            createCell((int)(Math.random()*width), (int)(Math.random()*height));
        }
        rootObject.addChild(boardView);
    }

    public void update(float dt){
        timePassed += dt;
        if(timePassed > stepPeriod){
            while(timePassed > stepPeriod){
                rootObject.log("Step");
                processStep();
                timePassed = timePassed - stepPeriod;
            }
        }
    }


    private void processStep(){
        clearBoards();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(boardModel.getCell(i, j) == null){
                    if(neighborsCount[i][j] == 3){
                        createBoard[i][j] = true;
                    }
                }else{
                    if(neighborsCount[i][j] == 3 || neighborsCount[i][j] == 2){
                        removeBoard[i][j] = true;
                    }
                }
            }
        }
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(removeBoard[i][j]){
                    removeCell(i, j);
                }
            }
        }
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(createBoard[i][j]){
                    createCell(i, j);
                }
            }
        }
    }
    private void clearBoards(){
        for(int i =0; i < width; i++){
            for(int j = 0; j < height; j++){
                removeBoard[i][j] = false;
                createBoard[i][j] = false;
            }
        }
    }

    private void updateNeighbors(int x, int y, boolean onCreate){
        int startX = x > 0 ? x - 1 : x;
        int startY = y > 0 ? y - 1 : y;
        int endX = x < width - 1 ? x + 1 : x;
        int endY = y < height -1 ? y + 1 : y;
        for(int i = startX; i <= endX; i++){
            for(int j = startY; j <= endY; j++){
                if(!(i == x && j == y)){
                    if(onCreate){
                        neighborsCount[i][j]++;
                    }else{
                        neighborsCount[i][j]--;
                    }
                }
            }
        }
    }

    public void createCell(int x, int y){
        if(boardModel.getCell(x, y) == null){
            rootObject.log("Add cell ", x , " ", y);
            CellModel cellModel = new CellModel(x,y);
            CellView cellView = new CellView(cellModel);
            boardView.addChild(cellView);
            boardModel.addCell(cellModel);
            updateNeighbors(x, y, true);
        }
    }

    public void removeCell(int x, int y){
        rootObject.log("Remove cell");
        CellModel model = boardModel.getCell(x, y);
        if(model != null){
            for(CellView view : boardView.getCells()){
                if(view.getModel() == model){
                    boardView.removeChild(view);
                }
            }
            boardModel.removeCell(x, y);
        }
        updateNeighbors(x, y, false);
    }
}
